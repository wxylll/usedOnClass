package bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import bookstore.order.dao.OrderDao;
import bookstore.order.domain.Order;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	
	private OrderDao orderDao = new OrderDao();
	
	/*
	 * ��Ӷ���
	 * ��������
	 */
	public void add(Order order) {
		try {
			//��������
			JdbcUtils.beginTransaction();
			
			orderDao.addOrder(order); //���붩��
			orderDao.addOrderItemList(order.getOrderItemList()); //���붩���е�������Ŀ
			
			//�ύ����
			JdbcUtils.commitTransaction();
		}catch(Exception e) {
			//�ع�����
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}

	/*
	 * ��ȡ�ҵĶ���
	 */
	public List<Order> myOrders(String uid) {
		return orderDao.findByUid(uid);
	}

	/*
	 * ���ض���
	 */
	public Order load(String oid) {
		return orderDao.findByOid(oid);
	}

	/*
	 * ȷ���ջ�
	 */
	public void confirm(String oid) throws OrderException {
		//��ȡ��ǰ����״̬
		int state = orderDao.getStateByOid(oid);
		//״̬��Ϊ3���׳��쳣
		if(state!=3) throw new OrderException("����ȷ��ʧ�ܣ�����״̬����");
		//�޸Ķ���״̬Ϊ4����ʾ���׳ɹ�
		orderDao.updateState(oid,4);
	}

	/*
	 * ����ջ���ַ
	 */
	public void addAddress(String oid,String address) {
		orderDao.updateAddress(oid,address);
	}

	/*
	 * ��ѯ���ж���
	 */
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	/*
	 * ������״̬��ѯ����
	 */
	public List<Order> findByState(String state) {
		return orderDao.findByState(state);
	}

	/*
	 * ����
	 */
	public void send(String oid) throws OrderException {
		//��ȡ��ǰ����״̬
		int state = orderDao.getStateByOid(oid);
		//״̬��Ϊ2���׳��쳣
		if(state!=2) throw new OrderException("����ʧ�ܣ��Ƿ�������");
		//�޸Ķ���״̬Ϊ3����ʾ�ѷ���
		orderDao.updateState(oid,3);
	}

	/*
	 * ֧���ɹ������
	 */
	public void back(String oid) throws OrderException {
		int state = orderDao.getStateByOid(oid);
		if(state != 1) throw new OrderException("����ʧ�ܣ�");
		orderDao.updateState(oid, 2);
	}
}
