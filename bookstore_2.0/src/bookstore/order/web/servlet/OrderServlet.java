package bookstore.order.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.cart.domain.Cart;
import bookstore.cart.domain.CartItem;
import bookstore.order.domain.Order;
import bookstore.order.domain.OrderItem;
import bookstore.order.service.OrderException;
import bookstore.order.service.OrderService;
import bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

@WebServlet(name="OrderServlet",urlPatterns="/OrderServlet")
public class OrderServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
	
	/*
	 * 添加订单
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 从session中得到cart
		 * 使用cart生成order对象
		 * 使用service添加order到数据库
		 * 保存order到request，转发到结算页面
		 */
		Cart cart = (Cart) request.getSession().getAttribute("cart"); //获取购物车
		//将cart装换成order
		Order order = new Order();
		order.setOid(CommonUtils.uuid()); //设置订单编号
		order.setOrdertime(new Date()); //设置下单时间
		order.setState(1); //设置订单状态为1，表示未付款
		User user = (User)request.getSession().getAttribute("session_user");
		order.setOwner(user); //设置订单所有者
		order.setTotal(cart.getTotal()); //设置订单合计，从cart中获得
		
		/*
		 * 创建订单集合
		 */
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		//遍历cart中的所有cartItem，创建orderItem并添加到orderItemList中
		for(CartItem cartItem : cart.getCartItems()) {
			//创建订单条目
			OrderItem oi = new OrderItem();
			
			oi.setIid(CommonUtils.uuid()); //订单条目ID
			oi.setCount(cartItem.getCount()); //设置条目的数量
			oi.setBook(cartItem.getBook()); //设置条目的图书
			oi.setSubtotal(cartItem.getSubtotal()); //设置条目的小计
			oi.setOrder(order); //设置条目所属订单
			
			orderItemList.add(oi); //将条目添加到集合中
		}
		//把所有订单条目添加到订单中
		order.setOrderItemList(orderItemList);
		//调用orderservice添加订单
		orderService.add(order);
		//清空购物车
		cart.clear();
		//保存order到request，并转发到支付界面
		request.setAttribute("order", order);
		return "f:/jsps/order/desc.jsp";
		
	}

	/*
	 * 我的订单
	 */
	public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("session_user");
		List<Order> orderList = orderService.myOrders(user.getUid());
		//对订单按成交时间排序
		Collections.sort(orderList, new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				return o1.getOrdertime().compareTo(o2.getOrdertime());
			}
		});
		request.setAttribute("orderList", orderList);
		return "f:/jsps/order/list.jsp";
		
	}
	
	/*
	 * 加载订单
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("order", orderService.load(request.getParameter("oid")));
		return "f:/jsps/order/desc.jsp";
	}
	
	/*
	 * 确认收货
	 */
	public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oid = request.getParameter("oid");
		try {
			orderService.confirm(oid);
			request.setAttribute("msg", "已确认收货，谢谢您的支持！");
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/jsps/msg.jsp";
		}
		return "f:/jsps/msg.jsp";
	}
	
	/*
	 * 支付(用不了)
	 */
	public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取收货地址
		String address = request.getParameter("address");
		String oid = request.getParameter("oid");
		orderService.addAddress(oid,address);
		//加载配置文件
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		props.load(input);
		
		//十三参数
		String p0_Cmd = "buy";
		String p1_MerId = props.getProperty("p1_MerId");
		String p2_Order = oid;
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = props.getProperty("p8_Url");
		String p9_SAF = "";
		String pa_MP = "";
		String pd_FrpId = request.getParameter("pd_FrpId");
		String pr_NeedResponse = "1";
		
		//获得keyvalue计算hmac
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		
		//连接易宝网址和13+1个参数
		StringBuilder url = new StringBuilder(props.getProperty("url"));
		url.append("?p0_Cmd=").append(p0_Cmd);
		url.append("&p1_MerId=").append(p1_MerId);
		url.append("&p2_Order=").append(p2_Order);
		url.append("&p3_Amt=").append(p3_Amt);
		url.append("&p4_Cur=").append(p4_Cur);
		url.append("&p5_Pid=").append(p5_Pid);
		url.append("&p6_Pcat=").append(p6_Pcat);
		url.append("&p7_Pdesc=").append(p7_Pdesc);
		url.append("&p8_Url=").append(p8_Url);
		url.append("&p9_SAF=").append(p9_SAF);
		url.append("&pa_MP=").append(pa_MP);
		url.append("&pd_FrpId=").append(pd_FrpId);
		url.append("&pr_NeedResponse=").append(pr_NeedResponse);
		url.append("&hmac=").append(hmac);
		
		//重定向到易宝
		//response.sendRedirect(url.toString());

		//假装成功
		String path = "f:/OrderServlet?method=back&p2_Order=" + oid;
		return path;
	}
	
	/*
	 * 支付成功后的操作
	 * 修改订单状态
	 */
	public String back(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			orderService.back(request.getParameter("p2_Order"));
			request.setAttribute("msg", "支付成功！");
			return "f:/jsps/msg.jsp";
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/jsps/msg.jsp";
		}
	}
	
}
