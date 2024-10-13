package laptrinhweb_JSP_CRUD.vn.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import laptrinhweb_JSP_CRUD.vn.entity.Category;
import laptrinhweb_JSP_CRUD.vn.services.ICategoryService;
import laptrinhweb_JSP_CRUD.vn.services.impl.CategoryService;
import laptrinhweb_JSP_CRUD.vn.until.Constant;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search" })
public class CategoryController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI();
		if (url.contains("/admin/categories")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category/category-list.jsp").forward(req, resp);
		} else if (url.contains("/admin/category/add")) {
			// else if (url.contains("add")){
			req.getRequestDispatcher("/views/admin/category/category-add.jsp").forward(req, resp);
		} else if (url.contains("/admin/category/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = cateService.findById(id);
			req.setAttribute("cate", category);

			req.getRequestDispatcher("/views/admin/category/category-edit.jsp").forward(req, resp);
		} else if (url.contains("/admin/category/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				cateService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		
		if (url.contains("/admin/category/insert")) {
			Category category = new Category();
			// lay du lieu tu form
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			// dua du lieu vao model
			category.setCategoryname(categoryname);
			category.setStatus(status);

			// Upload Images
			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// rename file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload file
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					category.setImages(fname);
					// } else if (images != null) {
					// category.setImages(images);
				} else {
					category.setImages("avatar.png");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			cateService.insert(category);
			// tra ve view
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("/admin/category/update")) {
			// lay du lieu tu form
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			// dua du lieu vao model
			Category category = new Category();
			category.setCategoryid(categoryid);
			category.setCategoryname(categoryname);
			category.setStatus(status);

			// lưu hình cũ
			Category cateold = cateService.findById(categoryid);
			String fileold = cateold.getImages();
			// Upload Images
			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// rename file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload file
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					category.setImages(fname);
				} else {
					category.setImages(fileold);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			cateService.update(category);
			// tra ve view
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}
