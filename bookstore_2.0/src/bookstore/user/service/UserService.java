package bookstore.user.service;

import bookstore.user.dao.UserDao;
import bookstore.user.domain.User;

/*
 * userҵ���
 */

public class UserService {
 
	private UserDao userDao = new UserDao();
	
	/*
	 * ע�Ṧ��
	 */
	public void regist(User form) throws UserException {
		/*
		 * У���û���
		 * У������
		 * ���������û������ݿ�
		 */
		User user = userDao.findByUsername(form.getUsername());
		if(user!=null) throw new UserException("�û����ѱ�ע�ᣡ");
		
		user = userDao.findByEmail(form.getEmail());
		if(user!=null) throw new UserException("�����ѱ�ע�ᣡ");
		
		userDao.add(form);
	}
	
	/*
	 * �����
	 */
	public void active(String code) throws UserException {
		/*
		 * ͨ��code��ѯ�û�
		 * ������Ϊnull���򼤻�����Ч
		 * ��stateΪfalse����Ϊtrue,������ʾ�Ѽ���
		 */
		User user = userDao.findByCode(code);
		if(user==null) {
			throw new UserException("����ʧ�ܣ���������Ч��");
		}else if(!user.isState()) {
			userDao.updateState(user.getUid(), true);
		}else {
			throw new UserException("�û��Ѽ����ǰ����¼��");
		}
	}
	
	/*
	 * ��¼����
	 */
	public User login(User form) throws UserException {
		/*
		 * �����ύ���û��������ݿ����
		 * ������Ϊnull�����ѯ����password���ύ�Ĳ���
		 * ����δ����״̬���׳��쳣
		 */
		User user = userDao.findByUsername(form.getUsername());
		
		if(user == null) {
			throw new UserException("�û������������");
		}else if(!user.getPassword().trim().equals(form.getPassword().trim())) {
			throw new UserException("�û������������");
		}else if(!user.isState()) {
			throw new UserException("�û�δ���");
		}
		
		return user;
		
	}

	/*
	 *���ӻ���
	 */
	public void addScore(String uid, int score) {
		userDao.updateScore(uid,score);
	}

	/*
	 *�۳�����
	 */
	public void deductScore(String uid, int score) {
		userDao.updateScore(uid,-score);
	}
	
}
