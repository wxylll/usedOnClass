package bookstore.category.service;

import java.util.List;

import bookstore.book.dao.BookDao;
import bookstore.category.dao.CategoryDao;
import bookstore.category.domain.Category;

public class CategoryService {

	private CategoryDao categoryDao = new CategoryDao();
	private BookDao bookDao = new BookDao();
	
	/*
	 * �������з���
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	/*
	 * ��ӷ���
	 */
	public void add(Category category) {
		categoryDao.add(category);
	}

	/*
	 * ɾ������
	 */
	public void delete(String cid) throws CategoryException {
		//��ȡ�÷����µ�ͼ��ı���
		int count = bookDao.findByCid(cid);
		//���÷�������ͼ�飬�׳��쳣
		if(count>0) throw new CategoryException("ɾ��ʧ�ܣ��÷����»���ͼ��!");
		//�����ݿ�ɾ���÷���
		categoryDao.delete(cid);
	}

	/*
	 * ���ط���
	 */
	public Category load(String cid) {
		return categoryDao.load(cid);
	}

	/*
	 * �޸ķ���
	 */
	public void edit(Category category) {
		categoryDao.edit(category);
	}
	
}
