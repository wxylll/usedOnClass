package bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * ���ﳵ��
 */
public class Cart {

	private Map<String,CartItem> map = new LinkedHashMap<String, CartItem>();
	
	/*
	 * ����ϼ�
	 */
	public double getTotal() {
		
		BigDecimal total = new BigDecimal("0");
		for(CartItem cartItem : map.values()) {
			BigDecimal subtotal = new BigDecimal("" + cartItem.getSubtotal());
			total = total.add(subtotal);
		}
		return total.doubleValue();
		
	}
	
	/*
	 * �����Ŀ
	 */
	public void add(CartItem cartItem) {
		
		/*
		 * �ж��Ƿ��Ѵ��ڸ���Ŀ
		 * �����ڣ���ȡ������Ŀ������������Ϊԭ��������������������Ȼ����map
		 * �������ڣ���ֱ�Ӵ���map
		 */
		String bid = cartItem.getBook().getBid();
		
		if(map.containsKey(bid)) {
			CartItem _cartItem = map.get(bid);
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		}else {
			map.put(bid, cartItem);
		}
	}
	
	/*
	 * ���������Ŀ
	 */
	public void clear() {
		map.clear();
	}
	
	/*
	 * ɾ��ָ����Ŀ
	 */
	public void delete(String bid) {
		map.remove(bid);
	}
	
	/*
	 * ��ȡ������Ŀ
	 */
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
}
