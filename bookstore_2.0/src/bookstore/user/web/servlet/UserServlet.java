package bookstore.user.web.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.cart.domain.Cart;
import bookstore.user.domain.User;
import bookstore.user.service.UserException;
import bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * user�־ò�
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	/*
	 * ע��
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * ��װ������
		 * ��ȫuid,code
		 * ����һ��map���ڷ�װ������Ϣ
		 * ��username��password��email����У��,��������Ϣ������map��
		 * �ж�map�Ƿ�Ϊ�գ�����Ϊ�ջص�ע����棬������Ϣ�����������Ϣ
		 * ��������userservice��regist���������ݿ��������
		 * ��һ���ɹ������ʼ����м���
		 */
		User form = CommonUtils.toBean(request.getParameterMap(),User.class);
		form.setUid(CommonUtils.uuid());
		form.setCode(CommonUtils.uuid()+CommonUtils.uuid());
		form.setScore(0);
		
		Map<String, String> errors = new HashMap<String,String>();
		
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()) {
			errors.put("username", "�û�������Ϊ�գ�");
		} else if(username.length() < 3 || username.length() > 10) {
			errors.put("username", "�û������ȱ�����3~10֮�䣡");
		}
		
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "���벻��Ϊ�գ�");
		} else if(password.length() < 3 || password.length() > 10) {
			errors.put("password", "���볤�ȱ�����3~10֮�䣡");
		}
		
		String email = form.getEmail();
		if(email == null || email.trim().isEmpty()) {
			errors.put("email", "���䲻��Ϊ�գ�");
		} else if(!email.matches("\\w+@163\\.\\w+")) {
			errors.put("email", "�����ʽ����Ŀǰֻ֧��163���䣡");
		}
		
		if(errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		
		try {
			userService.regist(form);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		String host = props.getProperty("host"); //����
		String uname = props.getProperty("uname"); //�û���
		String pwd = props.getProperty("pwd"); //����
		String from = props.getProperty("from"); //������
		String to = form.getEmail(); //�ռ���
		String subject = props.getProperty("subject"); //����
		String content = props.getProperty("content"); //�ʼ�����
		content = MessageFormat.format(content, form.getCode()); //�滻{0}
		
		Session session = MailUtils.createSession(host, uname, pwd); //��ȡsession
		Mail mail = new Mail(from,to,subject,content); //�����ʼ�����
		try {
			MailUtils.send(session, mail); //���ʼ�
		} catch (MessagingException e) {
		}
		
		request.setAttribute("msg", "ע��ɹ�����ǰ��������м��");
		return "f:/jsps/msg.jsp";
		
	}
	
	/*
	 * ����
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String code = request.getParameter("code");
		try {
			userService.active(code);
		} catch (UserException e) {
			request.setAttribute("msg",e.getMessage());
			return "f:jsps/msg.jsp";
		}
		request.setAttribute("msg","����ɹ��������ڿ��Ե�½�ˣ�");
		return "f:/jsps/msg.jsp";
		
	}
	
	/*
	 * ��¼
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * ��װ������
		 * ����һ��map���ڷ�װ������Ϣ
		 * ��username��password����У��,��������Ϣ������map��
		 * �ж�map�Ƿ�Ϊ�գ�����Ϊ�ջص�ע����棬������Ϣ�����������Ϣ
		 * ��������userservice��login���������ݿ����ݲ�ѯ�ȶ�
		 * ������û���Ϣ���浽session��
		 */
		User form = CommonUtils.toBean(request.getParameterMap(),User.class);
		Map<String, String> errors = new HashMap<String,String>();
		
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()) {
			errors.put("username", "�û�������Ϊ�գ�");
		}
		
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "���벻��Ϊ�գ�");
		}
		
		if(errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/jsps/user/login.jsp";
		}
		
		try {
			User user = userService.login(form);
			request.getSession().setAttribute("session_user", user);
			//��½�ɹ������û����һ�����ﳵ
			request.getSession().setAttribute("cart", new Cart());
			return "f:jsps/main.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/login.jsp";
		}
		
	}

	/*
	 * �˳�
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * ����session
		 */
		request.getSession().invalidate(); 
		return "f:/jsps/main.jsp";
	}
	
}
