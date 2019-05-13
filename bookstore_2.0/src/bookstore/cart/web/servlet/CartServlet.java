package bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.book.domain.Book;
import bookstore.book.service.BookService;
import bookstore.cart.domain.Cart;
import bookstore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

/**
 *购物车
 */
@WebServlet(name="CartServlet",urlPatterns="/CartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * 增加购物条目
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 获取购物车
		 * 得到条目
		 * 添加条目
		 */
		
		//获取购物车
		Cart cart =  (Cart) request.getSession().getAttribute("cart"); 
		//获取书和购买数量
		String bid = request.getParameter("bid");
		Book book = new BookService().load(bid);
		int count = Integer.parseInt(request.getParameter("count"));
		//创建条目
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		//增加条目到购物车
		cart.add(cartItem);
		
		return "f:/jsps/cart/list.jsp";
		
	}

	/*
	 * 清空购物条目
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cart cart =  (Cart) request.getSession().getAttribute("cart"); 
		cart.clear();
		return "f:/jsps/cart/list.jsp";
		
	}
	
	/*
	 * 删除购物条目
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cart cart =  (Cart) request.getSession().getAttribute("cart"); 
		cart.delete(request.getParameter("bid"));
		return "f:/jsps/cart/list.jsp";
	}

}
