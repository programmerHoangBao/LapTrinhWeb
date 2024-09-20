package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.UserModel;
import Until.DBMySQLUntil;

public class UserDAO {
	public UserModel getUserModel(String username, String password) throws SQLException{
		Connection conn = new DBMySQLUntil().getDatabaseConnection();
		String sqlQuery = "SELECT * FROM accounts WHERE username = ? AND password = ?";
		PreparedStatement pr = conn.prepareStatement(sqlQuery);
		pr.setString(1, username);
		pr.setString(2, password);
		ResultSet rs = pr.executeQuery();
		
		if (rs.next()) {
			UserModel userModel = new UserModel();
			userModel.setId(rs.getInt("id"));
			userModel.setUsername(rs.getString("username"));
			userModel.setPassword(rs.getString("password"));
			return userModel;
		}
		return null;
	}
}
