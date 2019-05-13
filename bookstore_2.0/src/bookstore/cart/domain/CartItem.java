package bookstore.cart.domain;

import java.math.BigDecimal;

import bookstore.book.domain.Book;

/*
 * 购物车条目类
 */
public class CartItem {

	private Book book; //商品
	private int count; //数量
	
	/*
	 * 小计方法
	 */
	public double getSubtotal() {
		
		BigDecimal price = new BigDecimal(book.getPrice() + "");
		BigDecimal _count = new BigDecimal(count + "");
		return price.multiply(_count).doubleValue();
		
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return count + book.getBname();
	}
	
}
