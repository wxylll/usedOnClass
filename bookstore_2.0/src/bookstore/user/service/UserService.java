package bookstore.user.service;

import bookstore.user.dao.UserDao;
import bookstore.user.domain.User;

/*
 * user业务层
 */

public class UserService {
 
	private UserDao userDao = new UserDao();
	
	/*
	 * 注册功能
	 */
	public void regist(User form) throws UserException {
		/*
		 * 校验用户名
		 * 校验邮箱
		 * 无误后插入用户到数据库
		 */
		User user = userDao.findByUsername(form.getUsername());
		if(user!=null) throw new UserException("用户名已被注册！");
		
		user = userDao.findByEmail(form.getEmail());
		if(user!=null) throw new UserException("邮箱已被注册！");
		
		userDao.add(form);
	}
	
	/*
	 * 激活功能
	 */
	public void active(String code) throws UserException {
		/*
		 * 通过code查询用户
		 * 若返回为null，则激活码无效
		 * 若state为false则置为true,否则提示已激活
		 */
		User user = userDao.findByCode(code);
		if(user==null) {
			throw new UserException("激活失败，激活码无效！");
		}else if(!user.isState()) {
			userDao.updateState(user.getUid(), true);
		}else {
			throw new UserException("用户已激活！请前往登录！");
		}
	}
	
	/*
	 * 登录功能
	 */
	public User login(User form) throws UserException {
		/*
		 * 根据提交的用户名到数据库查找
		 * 若返回为null，或查询到的password与提交的不符
		 * 或处于未激活状态则抛出异常
		 */
		User user = userDao.findByUsername(form.getUsername());
		
		if(user == null) {
			throw new UserException("用户名或密码错误！");
		}else if(!user.getPassword().trim().equals(form.getPassword().trim())) {
			throw new UserException("用户名或密码错误！");
		}else if(!user.isState()) {
			throw new UserException("用户未激活！");
		}
		
		return user;
		
	}

	/*
	 *增加积分
	 */
	public void addScore(String uid, int score) {
		userDao.updateScore(uid,score);
	}

	/*
	 *扣除积分
	 */
	public void deductScore(String uid, int score) {
		userDao.updateScore(uid,-score);
	}
	
}
