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
	public boolean addNewAccount(String username, String password) throws SQLException{
		Connection conn = new DBMySQLUntil().getDatabaseConnection();
		String sqlQuery = "INSERT INTO accounts(username, password) VALUES(?, ?)";
		PreparedStatement pr = conn.prepareStatement(sqlQuery);
		pr.setString(1, username);
		pr.setString(2, password);
		int rowsInserted = pr.executeUpdate();
		if (rowsInserted > 0) {
			return true;
		}
		return false;
	}
	public boolean checkUsernameExist(String username) throws SQLException {
		Connection conn = new DBMySQLUntil().getDatabaseConnection();
		String sqlQuery = "SELECT * FROM accounts WHERE username=?";
		PreparedStatement pr = conn.prepareStatement(sqlQuery);
		pr.setString(1, username);
		ResultSet rs = pr.executeQuery();
		return rs.next();
	}
	public boolean updateAccount(String username, String password) throws SQLException{
		Connection conn = new DBMySQLUntil().getDatabaseConnection();
		String sqlQuery = "UPDATE accounts SET password=? WHERE username=?";
		PreparedStatement pr = conn.prepareStatement(sqlQuery);
		pr.setString(1, password);
		pr.setString(2, username);
		return pr.executeUpdate() > 0;
	}
}
