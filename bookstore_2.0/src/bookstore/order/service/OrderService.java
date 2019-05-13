package bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import bookstore.order.dao.OrderDao;
import bookstore.order.domain.Order;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	
	private OrderDao orderDao = new OrderDao();
	
	/*
	 * 添加订单
	 * 处理事务
	 */
	public void add(Order order) {
		try {
			//开启事务
			JdbcUtils.beginTransaction();
			
			orderDao.addOrder(order); //插入订单
			orderDao.addOrderItemList(order.getOrderItemList()); //插入订单中的所有条目
			
			//提交事务
			JdbcUtils.commitTransaction();
		}catch(Exception e) {
			//回滚事务
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}

	/*
	 * 获取我的订单
	 */
	public List<Order> myOrders(String uid) {
		return orderDao.findByUid(uid);
	}

	/*
	 * 加载订单
	 */
	public Order load(String oid) {
		return orderDao.findByOid(oid);
	}

	/*
	 * 确认收货
	 */
	public void confirm(String oid) throws OrderException {
		//获取当前订单状态
		int state = orderDao.getStateByOid(oid);
		//状态不为3既抛出异常
		if(state!=3) throw new OrderException("订单确认失败！订单状态错误！");
		//修改订单状态为4，表示交易成功
		orderDao.updateState(oid,4);
	}

	/*
	 * 添加收货地址
	 */
	public void addAddress(String oid,String address) {
		orderDao.updateAddress(oid,address);
	}

	/*
	 * 查询所有订单
	 */
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	/*
	 * 按交易状态查询订单
	 */
	public List<Order> findByState(String state) {
		return orderDao.findByState(state);
	}

	/*
	 * 发货
	 */
	public void send(String oid) throws OrderException {
		//获取当前订单状态
		int state = orderDao.getStateByOid(oid);
		//状态不为2既抛出异常
		if(state!=2) throw new OrderException("发货失败，非法操作！");
		//修改订单状态为3，表示已发货
		orderDao.updateState(oid,3);
	}

	/*
	 * 支付成功后调用
	 */
	public void back(String oid) throws OrderException {
		int state = orderDao.getStateByOid(oid);
		if(state != 1) throw new OrderException("操作失败！");
		orderDao.updateState(oid, 2);
	}
}
