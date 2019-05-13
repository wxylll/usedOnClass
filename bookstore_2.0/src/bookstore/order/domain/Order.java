package bookstore.order.domain;

import java.util.Date;
import java.util.List;

import bookstore.user.domain.User;

public class Order {
	
	private String oid; 
	private Date ordertime; //�µ�ʱ��
	private double total; //�ϼ�
	private int state; //����״̬��1δ���� 2�Ѹ���δ���� 3�ѷ���δȷ���ջ� 4���׳ɹ�
	private User owner; //����������
	private String address; //�ջ���ַ
	private float discount; //�ۿ�
	private List<OrderItem> orderItemList; //��ǰ������������Ŀ

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
