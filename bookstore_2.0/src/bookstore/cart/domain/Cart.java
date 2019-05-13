package bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * 购物车类
 */
public class Cart {

	private Map<String,CartItem> map = new LinkedHashMap<String, CartItem>();
	
	/*
	 * 计算合计
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
	 * 添加条目
	 */
	public void add(CartItem cartItem) {
		
		/*
		 * 判断是否已存在该条目
		 * 若存在，则取出该条目，设置其数量为原来数量加上新增数量，然后存回map
		 * 若不存在，则直接存入map
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
	 * 清除所有条目
	 */
	public void clear() {
		map.clear();
	}
	
	/*
	 * 删除指定条目
	 */
	public void delete(String bid) {
		map.remove(bid);
	}
	
	/*
	 * 获取所有条目
	 */
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
}
