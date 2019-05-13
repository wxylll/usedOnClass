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
	 * ��Ӷ���
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * ��session�еõ�cart
		 * ʹ��cart����order����
		 * ʹ��service���order�����ݿ�
		 * ����order��request��ת��������ҳ��
		 */
		Cart cart = (Cart) request.getSession().getAttribute("cart"); //��ȡ���ﳵ
		//��cartװ����order
		Order order = new Order();
		order.setOid(CommonUtils.uuid()); //���ö������
		order.setOrdertime(new Date()); //�����µ�ʱ��
		order.setState(1); //���ö���״̬Ϊ1����ʾδ����
		User user = (User)request.getSession().getAttribute("session_user");
		order.setOwner(user); //���ö���������
		order.setTotal(cart.getTotal()); //���ö����ϼƣ���cart�л��
		
		/*
		 * ������������
		 */
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		//����cart�е�����cartItem������orderItem����ӵ�orderItemList��
		for(CartItem cartItem : cart.getCartItems()) {
			//����������Ŀ
			OrderItem oi = new OrderItem();
			
			oi.setIid(CommonUtils.uuid()); //������ĿID
			oi.setCount(cartItem.getCount()); //������Ŀ������
			oi.setBook(cartItem.getBook()); //������Ŀ��ͼ��
			oi.setSubtotal(cartItem.getSubtotal()); //������Ŀ��С��
			oi.setOrder(order); //������Ŀ��������
			
			orderItemList.add(oi); //����Ŀ��ӵ�������
		}
		//�����ж�����Ŀ��ӵ�������
		order.setOrderItemList(orderItemList);
		//����orderservice��Ӷ���
		orderService.add(order);
		//��չ��ﳵ
		cart.clear();
		//����order��request����ת����֧������
		request.setAttribute("order", order);
		return "f:/jsps/order/desc.jsp";
		
	}

	/*
	 * �ҵĶ���
	 */
	public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("session_user");
		List<Order> orderList = orderService.myOrders(user.getUid());
		//�Զ������ɽ�ʱ������
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
	 * ���ض���
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("order", orderService.load(request.getParameter("oid")));
		return "f:/jsps/order/desc.jsp";
	}
	
	/*
	 * ȷ���ջ�
	 */
	public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oid = request.getParameter("oid");
		try {
			orderService.confirm(oid);
			request.setAttribute("msg", "��ȷ���ջ���лл����֧�֣�");
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/jsps/msg.jsp";
		}
		return "f:/jsps/msg.jsp";
	}
	
	/*
	 * ֧��(�ò���)
	 */
	public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�ջ���ַ
		String address = request.getParameter("address");
		String oid = request.getParameter("oid");
		orderService.addAddress(oid,address);
		//���������ļ�
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		props.load(input);
		
		//ʮ������
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
		
		//���keyvalue����hmac
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		
		//�����ױ���ַ��13+1������
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
		
		//�ض����ױ�
		//response.sendRedirect(url.toString());

		//��װ�ɹ�
		String path = "f:/OrderServlet?method=back&p2_Order=" + oid;
		return path;
	}
	
	/*
	 * ֧���ɹ���Ĳ���
	 * �޸Ķ���״̬
	 */
	public String back(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			orderService.back(request.getParameter("p2_Order"));
			request.setAttribute("msg", "֧���ɹ���");
			return "f:/jsps/msg.jsp";
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/jsps/msg.jsp";
		}
	}
	
}
