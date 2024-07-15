package co.camping.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import co.camping.vo.CampingVO;

public class AppTests {

	// 기능
	public static Connection getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "jsp";
		String pass = "jsp";
		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("오라클 드라이버 없음.");
			e.printStackTrace();
//			return null;
		}
		return conn;

	}// end of getConn.

	public static void main(String[] args) {
		// 1) Oracle JDBC Driver 자바라이브러리.
		// 2) Connection 객체.
		Scanner scn = new Scanner(System.in);
		System.out.println("#예약자이름 입력 > ");
		String cpname = scn.nextLine();
		System.out.println("#캠핑장자리(1~10번) 입력 > ");
		String cpno = scn.nextLine();
		System.out.println("#예약날짜 입력 > ");
		String cpdate = scn.nextLine();
		System.out.println("#요청사항 입력 > ");
		String cpetc = scn.nextLine();

		CampingVO camp = new CampingVO();
		camp.setResNo(cpno);
		camp.setResName(cpname);
		camp.setResDate(cpdate);
		camp.setResId(cpetc);
	}

	// 입력기능
	public static void addCamping(String resId, String resNo, String resName, String resDate, String resEtc) {
		Connection conn = getConn();
		String sql = "insert into tbl_res (res_no, res_name, res_date, res_etc)";
//		sql += "values('1', '김감치', '2024-08.17', '없음')";
		sql += "values('" + resId + "', '" + resNo + "', '" + resName + "', '" + resDate + "','" + resEtc + "')";
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete => 처리된 건수.
			System.out.println("처리된 건수는 " + r + "건.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 수정기능.
	// update tbl_student
	// set std_name = '변경값'
	// ,std_phone = '변경값'
	// where std_no = '조회값'
	public static void modCamping(String resName, String resDate, String resNo, String resEtc) {
		Connection conn = getConn();
		String sql = "update tbl_res set res_id = '" + resNo + "', res_date = '" + resDate + "', res_etc = '"
				+ "' where std_no ='" + resName + "'";
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete => 처리된 건수.
			System.out.println("처리된 건수는 " + r + "건.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 삭제기능.
	public static void removeCamping(String resId ) {
				Connection conn = getConn();
				String sql = "delete from student where res_id = '" + resId + "'";
				try {
					Statement stmt = conn.createStatement();
					int r = stmt.executeUpdate(sql); // insert, update, delete => 처리된 건수.
					System.out.println("처리된 건수는 " + r + "건.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}

	// 조회기능
	public static void search() {
		// 조회기능.

		try {
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tbl_res");
			// [객체1, 객체2, 객체3]
			while (rs.next()) {
				String resDate = rs.getString("birth_date");
				resDate = resDate != null ? resDate : "";
				System.out.println(rs.getString("std_no") + ", "//
						+ rs.getString("std_name") + ", "//
						+ rs.getString("std_phone") + ", "//
						+ resDate.substring(0, 10)//
				);
			}
			System.out.println("end of data.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 조회기능 끝

	}
}
