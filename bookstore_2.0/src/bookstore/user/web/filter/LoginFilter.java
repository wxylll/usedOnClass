package bookstore.user.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import bookstore.user.domain.User;

/**
 * ǰ̨��¼������
 */
@WebFilter(filterName="LoginFilter",
		urlPatterns = { 
				"/jsps/cart/*", 
				"/jsps/order/*"
		}, 
		servletNames = { 
				"CartServlet", 
				"OrderServlet"
		})
public class LoginFilter implements Filter {

	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * ��session��ȡ�û���Ϣ
		 * �������û���Ϣ�������
		 * ���򣬱��������Ϣ��ת����login.jsp
		 */
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User user = (User) httpRequest.getSession().getAttribute("session_user");
		if(user != null) {
			chain.doFilter(request, response);
		}else {
			httpRequest.setAttribute("msg", "����û�е�¼��");
			httpRequest.setAttribute("isForward", "true");
			httpRequest.getRequestDispatcher("/jsps/user/login.jsp").forward(httpRequest, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
