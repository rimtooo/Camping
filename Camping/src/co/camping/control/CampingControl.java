package co.camping.control;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import co.camping.CalendarExe;
import co.camping.dao.CampingDao;
import co.camping.vo.AreaVO;
import co.camping.vo.CampingVO;

// 사용자입력을 가이드, 처리된 결과 출력

public class CampingControl {

	Scanner scn = new Scanner(System.in);
	CampingDao cpdao = new CampingDao();
	CampingVO rvo = new CampingVO();

	public void run() {
		boolean isTrue = true;
		
		System.out.println();
		System.out.println("                             [ 예담캠핑장 ]                                ");
		System.out.println();

		while (isTrue) {
			System.out.println("========================================================================");
			System.out.println("1.캠핑자리정보 | 2.예약가능일 확인 | 3.예약조회 | 4.예약 | 5.예약수정 | 6.예약취소 | 7.종료");
			System.out.println("========================================================================");
			System.out.print("선택 > ");
			int menu = Integer.parseInt(scn.nextLine());

			switch (menu) {
			case 1: // 캠핑자리정보
				System.out.println();
				System.out.println();
				campingList();
				System.out.println();
				System.out.println("**각 캠핑자리마다 하루에 1팀씩만 받습니다.");
				System.out.println("**자전거, 킥보드 등은 출입금지입니다.");
				System.out.println("**화목(장작), LP가스, 석유, 인화성물질 등은 반입금지입니다.");
				System.out.println("**예약 최대인원을 확인해주세요. 최대인원이 넘어가면 예약이 취소됩니다.");
				System.out.println();
				break;
			case 2: // 예약가능일 확인
				System.out.print("확인하고자 하는 예약월입력 >> ");
				int month = Integer.parseInt(scn.nextLine());
				System.out.println();
				System.out.println("             (2)예약가능일 확인 : " + month + "월");
				System.out.println();
				calendarResList(month);
				System.out.println();
				System.out.println("**표시가 된 날짜는 예약이 있는 날짜입니다.");
				System.out.println();
				break;
			case 3: // 예약조회
				System.out.println();
				resList();
				System.out.println();
				break;
			case 4: // 예약
				addCamping();
				System.out.println();
				break;
			case 5: // 예약수정
				cpdao.modCamping(rvo);
				break;
			case 6: // 예약취소
				cpdao.removeCamping(rvo);
				System.out.println();
				break;
			case 7: // 종료
				System.out.println("*************************** 다음에 또 이용해주세요. ***************************");
				isTrue = false;

			}

		}
	}// end of run().

	// 등록기능.

	void addCamping() {
		Scanner scn = new Scanner(System.in);
		System.out.println();
		System.out.println("                  (4)예약                  ");
		System.out.println();
		System.out.print("#예약자 이름을 입력하세요. > ");
		String resname = scn.nextLine();
		System.out.print("#캠핑장번호(1~10번)를 입력하세요. > ");
		String resno = scn.nextLine();
		System.out.print("#예약날짜를 입력하세요. ex)2024-08-17 > ");
		String resdate = scn.nextLine();
		System.out.println("요청사항가능 => 텐트대여/모닥불/카트요청/바베큐 등");
		System.out.print("#요청사항을 입력하세요. > ");
		String resetc = scn.nextLine();

		CampingVO rvo = new CampingVO();

		String amount = "";
		switch (resno) {
		case "1":
		case "2":
		case "3":
			amount = "50000원";
			break;
		case "4":
		case "5":
		case "6":
		case "7":
			amount = "100000원";
			break;
		case "8":
		case "9":
		case "10":
			amount = "200000원";
			break;
		default:
			System.out.println("**해당 자리번호에 대한 금액 정보가 없습니다. 확인 후 다시 예약해주세요.**");
			return;
		}

		rvo.setResName(resname);
		rvo.setResNo(resno + "번");
		rvo.setResDate(resdate);
		rvo.setResEtc(resetc);
		rvo.setResPrice(amount);

		CampingVO res = new CampingVO();

		// 입력기능 호출.
		if (cpdao.insertCamping(rvo)) {
			System.out.println();
			System.out.println("**예약이 완료되었습니다.**");
			System.out.println("**금액을 확인한 후 #농협)444-4444-4444-44#으로 입금부탁드립니다.");
			System.out.println("**예약자와 입금자의 이름이 다를 경우 연락부탁드립니다. #010-4444-4444#");

		} else {
			System.out.println("**예약이 정상적으로 처리되지 않았습니다. 다시 예약해주세요.**");
		}
	}

	// 목록 출력 기능.
	AreaVO cvo = new AreaVO();

	void campingList() {
		List<AreaVO> camping = cpdao.sselectList();
		System.out.println("           (1)캠핑자리정보           ");
		System.out.println("=================================");
		System.out.println("  자리번호  |   최대인원   |    가격     ");
		System.out.println("=================================");
		for (AreaVO svo : camping) {
			System.out.println(svo.briefShow());
			System.out.println("---------------------------------");
		}

		// 목록 출력 기능.
		CampingVO cvo = new CampingVO();
	}

	void resList() {
		List<CampingVO> res = cpdao.selectList();
		System.out.println("                         (3)예약조회                          ");
		System.out.println("============================================================");
		System.out.println("  자리번호 |     날짜     |    금액    |    예약자    |   요청사항    ");
		System.out.println("============================================================");
		for (CampingVO svo : res) {
			System.out.println(svo.briefShow());
			System.out.println("------------------------------------------------------------");
		}
	}

	// 예약목록 달력 출력...호출
	void calendarResList(int month) {

		List<Integer> reserveDays = cpdao.reserveDate(month);

		Calendar cal = Calendar.getInstance();
		cal.set(2024, month - 1, 1);
		int startDay = cal.get(Calendar.DAY_OF_WEEK);
		int lastDate = cal.getActualMaximum(Calendar.DATE);

		String[] days = { "Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat" };

		// 요일출력하기.
		for (String day : days) {
			System.out.print(String.format("%6s", day));
		}
		System.out.println();
		// 빈날짜 출력하기.
		for (int i = 1; i < startDay; i++) {
			System.out.print(String.format("%6s", ""));
		}

		// 날짜 출력하기.
		for (int d = 1; d <= lastDate; d++) {
			if (reserveDays.indexOf(d) == -1) {
				System.out.print(String.format("%6d", d));
			} else {
				System.out.print(String.format("%6s", "(" + d + ")"));
			}
			if ((d + startDay - 1) % 7 == 0) {
				System.out.println();
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		CampingControl cc = new CampingControl();
		cc.addCamping();
		cc.resList();
	}

}