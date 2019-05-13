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
 *���ﳵ
 */
@WebServlet(name="CartServlet",urlPatterns="/CartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * ���ӹ�����Ŀ
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * ��ȡ���ﳵ
		 * �õ���Ŀ
		 * �����Ŀ
		 */
		
		//��ȡ���ﳵ
		Cart cart =  (Cart) request.getSession().getAttribute("cart"); 
		//��ȡ��͹�������
		String bid = request.getParameter("bid");
		Book book = new BookService().load(bid);
		int count = Integer.parseInt(request.getParameter("count"));
		//������Ŀ
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		//������Ŀ�����ﳵ
		cart.add(cartItem);
		
		return "f:/jsps/cart/list.jsp";
		
	}

	/*
	 * ��չ�����Ŀ
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cart cart =  (Cart) request.getSession().getAttribute("cart"); 
		cart.clear();
		return "f:/jsps/cart/list.jsp";
		
	}
	
	/*
	 * ɾ��������Ŀ
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cart cart =  (Cart) request.getSession().getAttribute("cart"); 
		cart.delete(request.getParameter("bid"));
		return "f:/jsps/cart/list.jsp";
	}

}
