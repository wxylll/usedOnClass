package bookstore.cart.domain;

import java.math.BigDecimal;

import bookstore.book.domain.Book;

/*
 * ���ﳵ��Ŀ��
 */
public class CartItem {

	private Book book; //��Ʒ
	private int count; //����
	
	/*
	 * С�Ʒ���
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
