package bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import bookstore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

/*
 * user持久层
 */

public class UserDao {

	private QueryRunner qr = new TxQueryRunner();
	
	/*
	 * 通过用户名查找用户
	 */
	public User findByUsername(String username) {
		try{
			String sql = "select * from tb_user where username = ?";
			return qr.query(sql, new BeanHandler<User>(User.class),username);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 通过邮箱查找用户
	 */
	public User findByEmail(String email) {
		try{
			String sql = "select * from tb_user where email = ?";
			return qr.query(sql, new BeanHandler<User>(User.class),email);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 通过code查找用户
	 */
	public User findByCode(String code) {
		try{
			String sql = "select * from tb_user where code = ?";
			return qr.query(sql, new BeanHandler<User>(User.class),code);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 插入用户
	 */
	public void add(User user) {
		try{
			String sql = "insert into tb_user values(?,?,?,?,?,?,?)";
			Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),user.getEmail(),user.getCode(),user.isState(),user.getScore()};
			qr.update(sql,params);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 更新state
	 */
	public void updateState(String uid,boolean state) {
		try{
			String sql = "update tb_user set state = ? where uid = ?";
			Object[] params = {state,uid};
			qr.update(sql,params);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 *更新积分
	 */
	public void updateScore(String uid, int score) {
		try {
			String sql = "update tb_user set score += ? where uid = ?";
			Object[] params = {score,uid};
			qr.update(sql,params);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
