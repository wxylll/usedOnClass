package bookstore.book.service;

import java.util.List;

import bookstore.book.dao.BookDao;
import bookstore.book.domain.Book;

public class BookService {
	
	private BookDao bookDao = new BookDao();
	
	/*
	 * ��������ͼ��
	 */
	public List<Book> findAll() {
		return bookDao.findAll();
	}
	
	/*
	 * ���������ͼ��
	 */
	public List<Book> findByCategory(String cid) {
		return bookDao.findByCategory(cid);
	}
	
	/*
	 * ����ͼ��
	 */
	public Book load(String bid) {
		return bookDao.findByBid(bid);
	}

	/*
	 * ���ͼ��
	 */
	public void add(Book book) {
		bookDao.add(book);
	}
	
	/*
	 * ɾ��ͼ��
	 */
	public void delete(String bid) {
		bookDao.delete(bid);
	}

	/*
	 * �༭ͼ��
	 */
	public void edit(Book book) {
		bookDao.edit(book);
	}

}
