package Controller;

import java.io.IOException;

import DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet{
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
			if (this.userDAO.addNewAccount(username, password)) {
				req.setAttribute("message", "Account registration successful!");
			} else {
				req.setAttribute("message", "Account already exists!");
			}
			req.getRequestDispatcher("sign_up.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
