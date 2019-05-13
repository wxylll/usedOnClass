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
 * user持久层
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	/*
	 * 注册
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 封装表单数据
		 * 补全uid,code
		 * 创建一个map用于封装错误信息
		 * 对username，password，email进行校验,将错误信息保存至map中
		 * 判断map是否为空，若不为空回到注册界面，回显信息并输出错误信息
		 * 无误后调用userservice的regist方法向数据库插入数据
		 * 上一步成功后发送邮件进行激活
		 */
		User form = CommonUtils.toBean(request.getParameterMap(),User.class);
		form.setUid(CommonUtils.uuid());
		form.setCode(CommonUtils.uuid()+CommonUtils.uuid());
		form.setScore(0);
		
		Map<String, String> errors = new HashMap<String,String>();
		
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		} else if(username.length() < 3 || username.length() > 10) {
			errors.put("username", "用户名长度必须在3~10之间！");
		}
		
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		} else if(password.length() < 3 || password.length() > 10) {
			errors.put("password", "密码长度必须在3~10之间！");
		}
		
		String email = form.getEmail();
		if(email == null || email.trim().isEmpty()) {
			errors.put("email", "邮箱不能为空！");
		} else if(!email.matches("\\w+@163\\.\\w+")) {
			errors.put("email", "邮箱格式错误！目前只支持163邮箱！");
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
		String host = props.getProperty("host"); //主机
		String uname = props.getProperty("uname"); //用户名
		String pwd = props.getProperty("pwd"); //密码
		String from = props.getProperty("from"); //发件人
		String to = form.getEmail(); //收件人
		String subject = props.getProperty("subject"); //标题
		String content = props.getProperty("content"); //邮件内容
		content = MessageFormat.format(content, form.getCode()); //替换{0}
		
		Session session = MailUtils.createSession(host, uname, pwd); //获取session
		Mail mail = new Mail(from,to,subject,content); //创建邮件对象
		try {
			MailUtils.send(session, mail); //发邮件
		} catch (MessagingException e) {
		}
		
		request.setAttribute("msg", "注册成功！请前往邮箱进行激活！");
		return "f:/jsps/msg.jsp";
		
	}
	
	/*
	 * 激活
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String code = request.getParameter("code");
		try {
			userService.active(code);
		} catch (UserException e) {
			request.setAttribute("msg",e.getMessage());
			return "f:jsps/msg.jsp";
		}
		request.setAttribute("msg","激活成功！您现在可以登陆了！");
		return "f:/jsps/msg.jsp";
		
	}
	
	/*
	 * 登录
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 封装表单数据
		 * 创建一个map用于封装错误信息
		 * 对username，password进行校验,将错误信息保存至map中
		 * 判断map是否为空，若不为空回到注册界面，回显信息并输出错误信息
		 * 无误后调用userservice的login方法与数据库数据查询比对
		 * 无误后将用户信息保存到session中
		 */
		User form = CommonUtils.toBean(request.getParameterMap(),User.class);
		Map<String, String> errors = new HashMap<String,String>();
		
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		}
		
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		}
		
		if(errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/jsps/user/login.jsp";
		}
		
		try {
			User user = userService.login(form);
			request.getSession().setAttribute("session_user", user);
			//登陆成功，给用户添加一个购物车
			request.getSession().setAttribute("cart", new Cart());
			return "f:jsps/main.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/login.jsp";
		}
		
	}

	/*
	 * 退出
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 销毁session
		 */
		request.getSession().invalidate(); 
		return "f:/jsps/main.jsp";
	}
	
}
