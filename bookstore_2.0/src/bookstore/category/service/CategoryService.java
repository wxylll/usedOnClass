package bookstore.category.service;

import java.util.List;

import bookstore.book.dao.BookDao;
import bookstore.category.dao.CategoryDao;
import bookstore.category.domain.Category;

public class CategoryService {

	private CategoryDao categoryDao = new CategoryDao();
	private BookDao bookDao = new BookDao();
	
	/*
	 * 查找所有分类
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	/*
	 * 添加分类
	 */
	public void add(Category category) {
		categoryDao.add(category);
	}

	/*
	 * 删除分类
	 */
	public void delete(String cid) throws CategoryException {
		//获取该分类下的图书的本数
		int count = bookDao.findByCid(cid);
		//若该分类下有图书，抛出异常
		if(count>0) throw new CategoryException("删除失败！该分类下还有图书!");
		//从数据库删除该分类
		categoryDao.delete(cid);
	}

	/*
	 * 加载分类
	 */
	public Category load(String cid) {
		return categoryDao.load(cid);
	}

	/*
	 * 修改分类
	 */
	public void edit(Category category) {
		categoryDao.edit(category);
	}
	
}
