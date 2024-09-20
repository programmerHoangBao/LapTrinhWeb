package Controller;

import java.io.IOException;

import DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ForgotPasswordServlet extends HttpServlet{
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
			if (this.userDAO.checkUsernameExist(username)) {
				this.userDAO.updateAccount(username, password);
				req.setAttribute("messageColor", "green");
				req.setAttribute("message", "Password has been changed successfully!");
			} else {
				req.setAttribute("messageColor", "red");
				req.setAttribute("message", "Username does not exist!");
			}
			req.getRequestDispatcher("forgot_password.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
