package co.camping.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.camping.vo.AreaVO;
import co.camping.vo.CampingVO;

public class CampingDao extends Dao {
	// 등록기능.
	public boolean insertCamping(CampingVO rvo) {
		String sql = "insert into tbl_res (res_id, res_name, res_no, res_date, res_etc, res_price) ";
		sql += "values(res_seq.nextval, ?, ?, ?, ?, ?) ";
		conn = getConn();
		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, rvo.getResName());
			psmt.setString(2, rvo.getResNo());
			psmt.setString(3, rvo.getResDate());
			psmt.setString(4, rvo.getResEtc());
			psmt.setString(5, rvo.getResPrice());

			int r = psmt.executeUpdate(); // 쿼리실행.
			if (r == 1) {
				return true; // 정상처리.
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 월정보 -> 날짜반환
	public List<Integer> reserveDate(int month) {
		String sql = "select to_char(res_date, 'dd')"//
				+ " from tbl_res"//
				+ " where to_char(res_date, 'mm') = ?";
		// 7 -> 07, 10 ->10
		String val = "0" + month;
		val = val.substring(val.length() - 2);
		List<Integer> list = new ArrayList<>();
		conn = getConn();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, val);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Integer mm = rs.getInt(1);
				list.add(mm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	

	// 목록반환기능.
	public List<CampingVO> selectList() {
//		String sql = "Select * from tbl_res order by res_id";
		String sql = "select res_id,"
				+ "        res_no,"
				+ "        res_price,"
				+ "        res_name,"
				+ "        res_etc,"
				+ " to_char(res_date, 'yyyy-mm-dd') as res_date"
				+ " from tbl_res "
				+ " order by res_id";
				
		List<CampingVO> list = new ArrayList<>();

		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CampingVO rvo = new CampingVO();
				rvo.setResId(rs.getString("res_id"));
				rvo.setResNo(rs.getString("res_no"));
				rvo.setResDate(rs.getString("res_date"));
				rvo.setResPrice(rs.getString("res_price"));
				rvo.setResName(rs.getString("res_name"));
				rvo.setResEtc(rs.getString("res_etc"));

				list.add(rvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	// 목록반환기능.
	public List<AreaVO> sselectList() {
		String sql = "Select * from tbl_area order by to_number(substr(cp_no,1,length(cp_no)-1))";
		List<AreaVO> list = new ArrayList<>();

		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				AreaVO cvo = new AreaVO();
				cvo.setCpNo(rs.getString("cp_no"));
				cvo.setCpPrice(rs.getString("cp_price"));
				cvo.setCpPerson(rs.getString("cp_person"));
				list.add(cvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 수정기능.
	public void modCamping(CampingVO rvo) {
		Scanner scn = new Scanner(System.in);
		System.out.println();
		System.out.println("             (5)예약수정             ");
		System.out.println();
		System.out.print("#예약자명을 입력해주세요 > ");
		String resname2 = scn.nextLine();
		System.out.print("#변경할 예약날짜를 입력해주세요 > ");
		String resdate2 = scn.nextLine();
		System.out.print("#변경할 캠핑장자리(1~10번)를 입력해주세요 > ");
		String resno2 = scn.nextLine();
		System.out.print("#요청사항을 입력하세요. > ");
		String resetc2 = scn.nextLine();
		
		Connection conn = getConn();
		String sql = "update tbl_res ";
		sql += "set      res_date = nvl('" + resdate2 + "', res_date)";
		sql += "        ,res_no = nvl('" + resno2 + "번" + "', res_no)";
		sql += "        ,res_etc = nvl('" + resetc2 + "', res_etc)";
		sql += "  where res_name = '" + resname2 + "'";

		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete => 처리된 건수.
			System.out.println("**예약 수정이 완료되었습니다.**");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 삭제기능
	public void removeCamping(CampingVO rvo) {
		Connection conn = getConn();
		Scanner scn = new Scanner(System.in);
		System.out.println("             (6)예약취소             ");
		System.out.println();
		System.out.print("#예약취소를 위한 예약자명을 입력해주세요 > ");
		String resname3 = scn.nextLine();
		String sql = "delete from tbl_res where res_name = '" + resname3 + "'";
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete => 처리된 건수.
			System.out.println("**예약이 취소되었습니다.**");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
