package bookstore.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet(name="BookServlet",urlPatterns="/BookServlet")
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService();
	
	/*
	 * 查找所有图书
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("bookList", bookService.findAll());
		return "f:/jsps/book/list.jsp";
		
	}
	
	/*
	 * 按分类查找图书
	 */
	public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("bookList", bookService.findByCategory(request.getParameter("cid")));
		return "f:/jsps/book/list.jsp";
		
	}
	
	/*
	 * 加载图书
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("book", bookService.load(request.getParameter("bid")));
		return "f:/jsps/book/desc.jsp";
		
	}
	
}
