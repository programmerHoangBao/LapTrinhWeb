package Controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;

import DAO.UserDAO;
import Model.UserModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAO();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			UserModel userModel = new UserModel();
			userModel = this.userDAO.getUserModel(username, password);
			if (userModel != null) {
				//req.getSession().setAttribute("user.username", userModel.getUsername());
				req.setAttribute("username", username);
				req.getRequestDispatcher("login_success.jsp").forward(req, resp);

			} else {
				req.setAttribute("errorMessage", "Username or Password is incorrect!");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
