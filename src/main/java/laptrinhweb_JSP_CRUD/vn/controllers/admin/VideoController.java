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
import laptrinhweb_JSP_CRUD.vn.entity.Video;
import laptrinhweb_JSP_CRUD.vn.services.IVideoService;
import laptrinhweb_JSP_CRUD.vn.services.impl.VideoService;
import laptrinhweb_JSP_CRUD.vn.until.Constant;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/insert", "/admin/video/add", "/admin/video/update",
		"/admin/video/edit", "/admin/video/delete" })
public class VideoController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	IVideoService videoService = new VideoService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI();
		if (url.contains("/admin/videos")) {
			int id = Integer.parseInt(req.getParameter("id"));
			List<Video> list = videoService.findByIdCategory(id);
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video/video-list.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/add")) {
			// else if (url.contains("add")){
			req.getRequestDispatcher("/views/admin/video/video-add.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/edit")) {
			String id = req.getParameter("videoid");
			Video video = videoService.findById(id);
			req.setAttribute("video", video);
			req.getRequestDispatcher("/views/admin/video/video-edit.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/delete")) {
			String videoid = req.getParameter("videoid");
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			try {
				videoService.delete(videoid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos?id="+categoryid);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("/admin/video/insert")) {
			// lay du lieu tu form
			String title = req.getParameter("title");
			int active = Integer.parseInt(req.getParameter("status"));
			String description = req.getParameter("description");
			int view = Integer.parseInt(req.getParameter("views"));
			String videoid = req.getParameter("videoid");

			Category category = new Category();
			int id = Integer.parseInt(req.getParameter("categoryid"));
			category.setCategoryid(id);

			// dua du lieu vao model
			Video video = new Video();
			video.setTitle(title);
			video.setActive(active);
			video.setDescription(description);
			video.setViews(view);
			video.setVideoId(videoid);
			video.setCategory(category);

			// Upload Images
			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// rename file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload file
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					video.setPoster(fname);
					// } else if (images != null) {
					// category.setImages(images);
				} else {
					video.setPoster("avatar.png");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			videoService.insert(video);
			// tra ve view
			resp.sendRedirect(req.getContextPath() + "/admin/videos?id=" + id);
		} else if (url.contains("/admin/video/update")) {
			// lay du lieu tu form
			String title = req.getParameter("title");
			int active = Integer.parseInt(req.getParameter("status"));
			String description = req.getParameter("description");
			int view = Integer.parseInt(req.getParameter("views"));
			String videoid = req.getParameter("videoid");

			Category category = new Category();
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			category.setCategoryid(categoryid);

			// dua du lieu vao model
			Video video = new Video();
			video.setTitle(title);
			video.setActive(active);
			video.setDescription(description);
			video.setViews(view);
			video.setVideoId(videoid);
			video.setCategory(category);

			//lưu hình cũ 
			Video videoold = videoService.findById(videoid);
			String fileold = videoold.getPoster();
			
			// Upload Images
			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// rename file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload file
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					video.setPoster(fname);
					// } else if (images != null) {
					// category.setImages(images);
				} else {
					video.setPoster(fileold);
					//video.setPoster("avatar.png");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			videoService.update(video);
			// tra ve view
			resp.sendRedirect(req.getContextPath() + "/admin/videos?id=" + categoryid);
		}
	}
}
