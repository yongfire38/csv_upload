package upload;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		 Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_TEST?characterEncoding=UTF-8&serverTimezone=UTC", "root", "weak");

		return connection;
	}

	public static void main(String[] args) {
		try {
			getConnection();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
