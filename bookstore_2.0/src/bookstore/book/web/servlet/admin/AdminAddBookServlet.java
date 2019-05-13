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
		
		//创建工厂
		DiskFileItemFactory factory = new DiskFileItemFactory(100*1024,new File("F:/f/temp"));
		//得到解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		//设置单个文件大小为100kb
		sfu.setFileSizeMax(100*1024);
		//用解析器解析request对象
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);
			/*
			 * 把fileItemList中的数据封装到Book对象中
			 */
			Map<String,String> map = new HashMap<String,String>();
			for(FileItem item : fileItemList) {
				String value = item.getString("UTF-8");
				//校验是否有空字段
				if(value.trim().equals("") || value == null) {
					request.setAttribute("msg", "请完善所有图书信息！");
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
			 * 保存上传的文件
			 */
			//得到保存目录
			String savepath = this.getServletContext().getRealPath("/book_img");
			//校验是否有图片文件
			if(fileItemList.get(1) == null) {
				request.setAttribute("msg", "请完善所有图书信息！");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
			}
			//得到文件名称,并加上uuid避免文件名重复
			String filename = CommonUtils.uuid() + "_" + fileItemList.get(1).getName();
			//使用目录和文件名创建目标文件
			File destFile = new File(savepath,filename);
			//保存上传文件到目标位置
			fileItemList.get(1).write(destFile);
			//设置book的image
			book.setImage("book_img/" + filename);
			
			/*
			 * 讲book存入数据库
			 */
			bookService.add(book);
			//返回图书列表
			request.getRequestDispatcher("/admin/AdminBookServlet?method=findAll").forward(request, response);
		} catch (Exception e) {
			if(e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "图片大小超出100kb！");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
			}
		}
	}

}
