package co.camping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarExe {
	public static void main(String[] args) {
		int month = 9;
		int year = 2024;

		drawCalendar(year, month);
	}

	public static List<Integer> getReservedDate() {
		int[] data = { 1, 2, 3, 4, 5 };
		List<Integer> list = new ArrayList<>();
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(24);
		return list;
	}

	public static void drawCalendar(int year, int month) {

		List<Integer> reserveDays = getReservedDate();

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
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

	}
}
