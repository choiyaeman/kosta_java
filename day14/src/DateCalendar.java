import java.util.Calendar;
import java.util.Date;

public class DateCalendar {

	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(now);

		//Calendar c = new Calendar();
		Calendar c = Calendar.getInstance();
		Class clazz = c.getClass();
		System.out.println("Calendar객체의 실제 클래스타입은" + clazz.getName());
		
		int ampm = c.get(Calendar.AM_PM);
		if(ampm == Calendar.AM) {
			System.out.println("오전");
		}else {
			System.out.println("오후");
		}
		
		System.out.println(c.get(Calendar.MONTH) + 1); //1월인 경우 0
	
		switch(c.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY: 
			System.out.println("일요일");
			break;
		case Calendar.SATURDAY:
			System.out.println("토요일");
			break;
		default:
			System.out.println("주중~~~");
			break;
		}
		
		Calendar feb = Calendar.getInstance();
		feb.set(2021, 2-1, 1); //21년도 2월 1일
		System.out.println("마지막날짜:" + feb.getActualMaximum(Calendar.DATE));
		
		//-------------------------
		System.out.println("2021년 2월 달력");
		System.out.println("일  월  화  수  목  금  토");
		//TODO 결과로  아래와 같은 달력을 출력하시오 
		/*일  월  화  수  목  금  토
		 *   1  2 3 4  5  6
		 * 7 8  9 10 11 12 13
		 *14 15 16 17 18 19 20
		 *21 22 23 24 25 26 27
		 *28            
		 */
		
		int maxDate = feb.getActualMaximum(Calendar.DATE);
		int cnt=0;
		switch(feb.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SATURDAY://6  
			cnt++;             //cnt=6; break;와 같음
		case Calendar.FRIDAY://5
			cnt++;            //cnt=5; break;와 같음
		case Calendar.THURSDAY://4
			cnt++;
		case Calendar.WEDNESDAY://3
			cnt++;
		case Calendar.TUESDAY://2
			cnt++;
		case Calendar.MONDAY://1
			cnt++;
		//case Calendar.SUNDAY: 0
		}		
		for(int i=0; i<cnt; i++) { //1일 출력하기 전에 공백출력하기
			System.out.print("   ");
		}
		for(int date=1; date<=maxDate; date++, cnt++) {
			if(cnt%7 == 0) {
				System.out.println();
				cnt = 0;
			}
			
			System.out.print(date);
			if(date < 10) {
				System.out.print("  ");
			}else {
				System.out.print(" ");
			}
			
		}
		
		
	}

}
