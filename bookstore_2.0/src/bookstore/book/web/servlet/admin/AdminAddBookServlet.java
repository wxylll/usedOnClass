package bookstore.book.web.servlet.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bookstore.book.domain.Book;
import bookstore.book.service.BookService;
import bookstore.category.domain.Category;
import bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;

@WebServlet("/admin/AdminAddBookServlet")
public class AdminAddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//��������
		DiskFileItemFactory factory = new DiskFileItemFactory(100*1024,new File("F:/f/temp"));
		//�õ�������
		ServletFileUpload sfu = new ServletFileUpload(factory);
		//���õ����ļ���СΪ100kb
		sfu.setFileSizeMax(100*1024);
		//�ý���������request����
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);
			/*
			 * ��fileItemList�е����ݷ�װ��Book������
			 */
			Map<String,String> map = new HashMap<String,String>();
			for(FileItem item : fileItemList) {
				String value = item.getString("UTF-8");
				//У���Ƿ��п��ֶ�
				if(value.trim().equals("") || value == null) {
					request.setAttribute("msg", "����������ͼ����Ϣ��");
					request.setAttribute("categoryList", categoryService.findAll());
					request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				}
				if(item.isFormField()) {
					map.put(item.getFieldName(), item.getString("UTF-8"));
				}
			}
			Book book = CommonUtils.toBean(map, Book.class);
			book.setBid(CommonUtils.uuid());
			Category category = CommonUtils.toBean(map, Category.class);
			book.setCategory(category);
			/*
			 * �����ϴ����ļ�
			 */
			//�õ�����Ŀ¼
			String savepath = this.getServletContext().getRealPath("/book_img");
			//У���Ƿ���ͼƬ�ļ�
			if(fileItemList.get(1) == null) {
				request.setAttribute("msg", "����������ͼ����Ϣ��");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
			}
			//�õ��ļ�����,������uuid�����ļ����ظ�
			String filename = CommonUtils.uuid() + "_" + fileItemList.get(1).getName();
			//ʹ��Ŀ¼���ļ�������Ŀ���ļ�
			File destFile = new File(savepath,filename);
			//�����ϴ��ļ���Ŀ��λ��
			fileItemList.get(1).write(destFile);
			//����book��image
			book.setImage("book_img/" + filename);
			
			/*
			 * ��book�������ݿ�
			 */
			bookService.add(book);
			//����ͼ���б�
			request.getRequestDispatcher("/admin/AdminBookServlet?method=findAll").forward(request, response);
		} catch (Exception e) {
			if(e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "ͼƬ��С����100kb��");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
			}
		}
	}

}
