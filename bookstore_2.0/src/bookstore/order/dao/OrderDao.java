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
	 * 添加订单
	 */
	public void addOrder(Order order) {
		try {
			String sql = "insert into orders values(?,?,?,?,?,?,?)";
			//转换Date
			Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
			Object[] params = {order.getOid(),timestamp,order.getTotal(),
					order.getState(),order.getOwner().getUid(),order.getAddress(),order.getDiscount()};
			qr.update(sql,params);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 添加订单条目
	 */
	public void addOrderItemList(List<OrderItem> orderItemList) {
		try {
			int size = orderItemList.size();
			String sql = "insert into orderitem values(?,?,?,?,?)";
			//将orderItemList转换为二维数组
			Object[][] params = new Object[size][];
			//遍历orderItemList为params中的一维数组赋值
			for(int i = 0; i < size; i++) {
				OrderItem item = orderItemList.get(i);
				params[i] = new Object[]{item.getIid(),item.getCount(),item.getSubtotal(),
						item.getOrder().getOid(),item.getBook().getBid()};
			}
			//执行批处理
			qr.batch(sql, params);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * 按UID查找订单
	 */
	public List<Order> findByUid(String uid) {
		try {
			//得到当前用户的所有订单
			String sql = "select * from orders where uid=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
			//遍历orderList，为order加载所有订单条目
			for(Order order : orderList) {
				loadOrderItems(order); //为order对象添加订单条目
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 查询所有订单
	 */
	public List<Order> findAll() {
		try {
			//得到所有订单
			String sql = "select * from orders";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class));
			//遍历orderList，为order加载所有订单条目
			for(Order order : orderList) {
				loadOrderItems(order); //为order对象添加订单条目
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * 为order对象添加订单条目
	 */
	private void loadOrderItems(Order order) throws SQLException {
		//联合查询两张表:orderitem,book
		String sql = "select * from orderItem i,book b where i.bid=b.bid and oid=?";
		//结果集并未对应javabean，故用maplisthandler
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
		/*
		 * mapList为多个map，每个map对应一行记录
		 * 需用一个map生成OrderItem和Book对象，然后建立两者的关系
		 * 循环遍历每个map，生成两个对象，然后建立关系
		 */
		List<OrderItem> orderItemList = toOrderItemList(mapList);
		order.setOrderItemList(orderItemList);
	}

	/*
	 * 循环遍历每个map，生成两个对象，然后建立关系
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
	 * 把一个map转换成一个OrderItem
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}

	/*
	 * 通过oid查询订单
	 */
	public Order findByOid(String oid) {
		try {
			String sql = "select * from orders where oid=?";
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
			loadOrderItems(order); //为order对象添加订单条目
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * 通过oid获取state
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
	 * 修改订单状态
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
	 * 修改收货地址
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
	 * 查询指定交易状态的订单
	 */
	public List<Order> findByState(String state) {
		try {
			//得到指定交易状态的订单
			String sql = "select * from orders where state=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class),state);
			//遍历orderList，为order加载所有订单条目
			for(Order order : orderList) {
				loadOrderItems(order); //为order对象添加订单条目
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
