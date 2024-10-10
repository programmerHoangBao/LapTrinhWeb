package Until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBMySQLUntil {
	private static String USERNAME = "root";
	private static String PASSWORD = "123456789";
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/demo_login_sign_up";

	public static Connection getDatabaseConnection() throws SQLException {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	public static void main(String[] args) {
		try {
			new DBMySQLUntil();
			System.out.println(DBMySQLUntil.getDatabaseConnection());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
