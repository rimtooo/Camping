package co.camping.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	Connection conn = null;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	
	public Connection getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "jsp";
		String pass = "jsp";

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("오라클 드라이버 없음.");
			e.printStackTrace();
//			return null;
		}
		return conn;
		
	}//end of getConn.
}
