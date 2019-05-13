package bookstore.order.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.order.service.OrderException;
import bookstore.order.service.OrderService;
import cn.itcast.servlet.BaseServlet;

@WebServlet(name="admin/AdminOrderServlet",urlPatterns="/admin/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService = new OrderService();

	/*
	 * 查询所有订单
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("orderList", orderService.findAll());
		return "f:/adminjsps/admin/order/list.jsp";
	}

	/*
	 * 获取指定交易状态的订单
	 */
	public String findByState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("orderList", orderService.findByState(request.getParameter("state")));
		return "f:/adminjsps/admin/order/list.jsp";
	}
	
	/*
	 * 发货
	 */
	public String send(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			orderService.send(request.getParameter("oid"));
			request.setAttribute("msg", "发货成功！等待收取！");
			return "f:/adminjsps/msg.jsp";
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/adminjsps/msg.jsp";
		}
	}
	
}
