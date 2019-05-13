package bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bookstore.book.domain.Book;
import bookstore.order.domain.Order;
import bookstore.order.domain.OrderItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	
	private QueryRunner qr = new TxQueryRunner();
	
	/*
	 * ��Ӷ���
	 */
	public void addOrder(Order order) {
		try {
			String sql = "insert into orders values(?,?,?,?,?,?,?)";
			//ת��Date
			Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
			Object[] params = {order.getOid(),timestamp,order.getTotal(),
					order.getState(),order.getOwner().getUid(),order.getAddress(),order.getDiscount()};
			qr.update(sql,params);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * ��Ӷ�����Ŀ
	 */
	public void addOrderItemList(List<OrderItem> orderItemList) {
		try {
			int size = orderItemList.size();
			String sql = "insert into orderitem values(?,?,?,?,?)";
			//��orderItemListת��Ϊ��ά����
			Object[][] params = new Object[size][];
			//����orderItemListΪparams�е�һά���鸳ֵ
			for(int i = 0; i < size; i++) {
				OrderItem item = orderItemList.get(i);
				params[i] = new Object[]{item.getIid(),item.getCount(),item.getSubtotal(),
						item.getOrder().getOid(),item.getBook().getBid()};
			}
			//ִ��������
			qr.batch(sql, params);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * ��UID���Ҷ���
	 */
	public List<Order> findByUid(String uid) {
		try {
			//�õ���ǰ�û������ж���
			String sql = "select * from orders where uid=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
			//����orderList��Ϊorder�������ж�����Ŀ
			for(Order order : orderList) {
				loadOrderItems(order); //Ϊorder������Ӷ�����Ŀ
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * ��ѯ���ж���
	 */
	public List<Order> findAll() {
		try {
			//�õ����ж���
			String sql = "select * from orders";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class));
			//����orderList��Ϊorder�������ж�����Ŀ
			for(Order order : orderList) {
				loadOrderItems(order); //Ϊorder������Ӷ�����Ŀ
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * Ϊorder������Ӷ�����Ŀ
	 */
	private void loadOrderItems(Order order) throws SQLException {
		//���ϲ�ѯ���ű�:orderitem,book
		String sql = "select * from orderItem i,book b where i.bid=b.bid and oid=?";
		//�������δ��Ӧjavabean������maplisthandler
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
		/*
		 * mapListΪ���map��ÿ��map��Ӧһ�м�¼
		 * ����һ��map����OrderItem��Book����Ȼ�������ߵĹ�ϵ
		 * ѭ������ÿ��map��������������Ȼ������ϵ
		 */
		List<OrderItem> orderItemList = toOrderItemList(mapList);
		order.setOrderItemList(orderItemList);
	}

	/*
	 * ѭ������ÿ��map��������������Ȼ������ϵ
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(Map<String,Object> map : mapList) {
			OrderItem item = toOrderItem(map);
			orderItemList.add(item);
		}
		return orderItemList;
	}

	/*
	 * ��һ��mapת����һ��OrderItem
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}

	/*
	 * ͨ��oid��ѯ����
	 */
	public Order findByOid(String oid) {
		try {
			String sql = "select * from orders where oid=?";
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
			loadOrderItems(order); //Ϊorder������Ӷ�����Ŀ
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * ͨ��oid��ȡstate
	 */
	public int getStateByOid(String oid) {
		try {
			String sql = "select state from orders where oid=?";
			return (Integer)qr.query(sql, new ScalarHandler<Object>(), oid);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * �޸Ķ���״̬
	 */
	public void updateState(String oid, int state) {
		try {
			String sql = "update orders set state=? where oid=?";
			qr.update(sql, state,oid);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * �޸��ջ���ַ
	 */
	public void updateAddress(String oid,String address) {
		try {
			String sql = "update orders set address=? where oid=?";
			qr.update(sql, address,oid);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * ��ѯָ������״̬�Ķ���
	 */
	public List<Order> findByState(String state) {
		try {
			//�õ�ָ������״̬�Ķ���
			String sql = "select * from orders where state=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class),state);
			//����orderList��Ϊorder�������ж�����Ŀ
			for(Order order : orderList) {
				loadOrderItems(order); //Ϊorder������Ӷ�����Ŀ
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
