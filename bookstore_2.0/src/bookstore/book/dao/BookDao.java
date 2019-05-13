package bookstore.book.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bookstore.book.domain.Book;
import bookstore.category.domain.Category;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {

	private QueryRunner qr = new TxQueryRunner();
	
	/*
	 * 查找所有图书
	 */
	public List<Book> findAll() {
		try {
			String sql = "select * from book where del=false";
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 按分类查找图书
	 */
	public List<Book> findByCategory(String cid) {
		try {
			String sql = "select * from book where cid = ? and del=false";
			return qr.query(sql, new BeanListHandler<Book>(Book.class), cid);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 按ID查找图书
	 */
	public Book findByBid(String bid) {
		try{
			String sql = "select * from book where bid = ?";
			Map<String,Object> map = qr.query(sql, new MapHandler(),bid);
			Category category = CommonUtils.toBean(map, Category.class);
			Book book = CommonUtils.toBean(map, Book.class);
			book.setCategory(category);
			return book;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * 查找指定分类下的图书数量
	 */
	public int findByCid(String cid) {
		try{
			String sql = "select count(*) from book where cid = ? and del=false";
			Number num = (Number) qr.query(sql,new ScalarHandler<Object>(),cid);
			return num.intValue();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * 添加图书
	 */
	public void add(Book book) {
		try{
			String sql = "insert into book values(?,?,?,?,?,?,?)";
			Object[] params = {book.getBid(),book.getBname(),book.getBriefing(),book.getPrice(),book.getAuthor(),book.getImage(),
					book.getCategory().getCid()};
			qr.update(sql,params);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 删除图书
	 */
	public void delete(String bid) {
		try{
			String sql = "update book set del=true where bid=?";
			qr.update(sql,bid);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * 编辑图书
	 */
	public void edit(Book book) {
		try{
			String sql = "update book set bname=?,briefing=?,price=?,author=?,image=?,cid=? where bid=?";
			Object[] params = {book.getBname(),book.getBriefing(),book.getPrice(),book.getAuthor(),book.getImage(),
					book.getCategory().getCid(),book.getBid()};
			qr.update(sql,params);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
