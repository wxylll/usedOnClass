package bookstore.book.service;

import java.util.List;

import bookstore.book.dao.BookDao;
import bookstore.book.domain.Book;

public class BookService {
	
	private BookDao bookDao = new BookDao();
	
	/*
	 * 查找所有图书
	 */
	public List<Book> findAll() {
		return bookDao.findAll();
	}
	
	/*
	 * 按分类查找图书
	 */
	public List<Book> findByCategory(String cid) {
		return bookDao.findByCategory(cid);
	}
	
	/*
	 * 加载图书
	 */
	public Book load(String bid) {
		return bookDao.findByBid(bid);
	}

	/*
	 * 添加图书
	 */
	public void add(Book book) {
		bookDao.add(book);
	}
	
	/*
	 * 删除图书
	 */
	public void delete(String bid) {
		bookDao.delete(bid);
	}

	/*
	 * 编辑图书
	 */
	public void edit(Book book) {
		bookDao.edit(book);
	}

}
