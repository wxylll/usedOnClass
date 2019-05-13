package bookstore.user.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

@WebServlet(name="AdminServlet",urlPatterns="/AdminServlet")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * ����Ա��¼
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String admin = request.getParameter("admin");
		String password = request.getParameter("password");
		
		if(admin==null||password==null) {
			request.setAttribute("msg", "��������ȷ�Ĺ���Ա�˺ź����룡");
			return "f:/adminjsps/login.jsp";
		}
		
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("admin.properties");
		props.load(input);
		
		if(admin.trim().equals(props.getProperty("admin").trim()) &&
				password.trim().equals(props.getProperty("password").trim())) {
			//��¼�ɹ�����session�������Ա��Ϣ
			request.getSession().setAttribute("session_admin", props.getProperty("admin"));
			return "f:/adminjsps/admin/main.jsp";
			
		}else {
			
			request.setAttribute("msg", "��������ȷ�Ĺ���Ա�˺ź����룡");
			return "f:/adminjsps/login.jsp";
			
		}
	}

	
	
}
