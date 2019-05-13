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

/**
 * 后台登录过滤器
 */
@WebFilter(
		urlPatterns = { "/adminjsps/admin/*" }, 
		servletNames = { 
				"admin/AdminCategoryServlet", 
				"admin/AdminOrderServlet", 
				"AdminAddBookServlet", 
				"AdminBookServlet"
		})
public class AdminLoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String admin = (String) httpRequest.getSession().getAttribute("session_admin");
		if(admin!=null) {
			chain.doFilter(request, response);
		}else {
			request.setAttribute("msg", "非法访问！");
			httpRequest.getRequestDispatcher("/adminjsps/msg.jsp").forward(httpRequest, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
