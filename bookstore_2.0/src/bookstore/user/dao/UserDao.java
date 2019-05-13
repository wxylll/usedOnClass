package bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import bookstore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

/*
 * user�־ò�
 */

public class UserDao {

	private QueryRunner qr = new TxQueryRunner();
	
	/*
	 * ͨ���û��������û�
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
	 * ͨ����������û�
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
	 * ͨ��code�����û�
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
	 * �����û�
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
	 * ����state
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
	 *���»���
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
