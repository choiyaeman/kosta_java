
public class ConditionEx2 {
	public static void main(String[] args) {
		//if에서 사용하는 조건식의 비교연산자가 ==이라면
		//switch구문으로 사용될 수 있다.
		java.util.Calendar now;
		now = java.util.Calendar.getInstance();
		
		int year = now.get(java.util.Calendar.YEAR);
		int month = now.get(java.util.Calendar.MONTH);
		int date = now.get(java.util.Calendar.DATE);
		System.out.println(year +"년 " + (month+1) +"월 " + date+"일");
		//1~3월까지 "1분기"
		//4~6     "2분기"
		//7~9     "3분기"
		//10~12   "4분기"출력하시오
		//switch(표현식) 표현식자료형은 byte, short, char, int
		//                        String
		switch(month/3) { 
		case 1:
		case 2:
		case 3:
			System.out.println("1분기");
			break;
		case 4:
		case 5:
		case 6:
			System.out.println("2분기");
			break;
		case 7:
		case 8:
		case 9:
			System.out.println("3분기");
			break;
		case 10:
		case 11:
		case 12:
			System.out.println("4분기");
			break;
		}
		
		int ampm = now.get(java.util.Calendar.AM_PM);
		System.out.println(ampm);
		if(ampm == 0) {
			System.out.println("오전");
		}else {
			System.out.println("오후");
		}
		
		switch(ampm) {
		case 0:
			System.out.println("오전");
			break;
		default:
			System.out.println("오후");
		}
		
		
		
		
		// TODO 
		//사용자로부터 출생년도 입력받기
		System.out.println("출생년도를 입력하세요:");

		java.util.Scanner sc = new java.util.Scanner(System.in);
		int birthYear = sc.nextInt();
		
		//TODO 년도에 해당 동물이름을 출력하시오
		//년도를 12로 나눈 나머지값이 0이면 원숭이, 1이면 닭, 2이면 개, 3이면 돼지
		//4-쥐, 5-소, 6-호랑이, 7-토끼, 8-용, 9-뱀,10-말, 11-양       
		int mod = birthYear % 12;
		
		if(mod == 0) {
			System.out.println("원숭이");
		}else if(mod == 1) {
			System.out.println("닭");
		}else if(mod == 2) {
			System.out.println("개");
		}else if(mod == 3) {
			System.out.println("돼지");
		}else if(mod == 4) {
			System.out.println("쥐");
		}else if(mod == 5) {
			System.out.println("소");
		}else if(mod == 6) {
			System.out.println("호랑이");
		}else if(mod == 7) {
			System.out.println("토끼");
		}else if(mod == 8) {
			System.out.println("용");
		}else if(mod == 9) {
			System.out.println("뱀");
		}else if(mod == 10) {
			System.out.println("말");
		}else if(mod == 11) {
			System.out.println("양");
		}
		
		switch(mod) {
		case 0:
			System.out.println("원숭이");
			break;
		case 1:
			System.out.println("닭");
			break;
		case 2:
			System.out.println("개");
			break;
		case 3:
			System.out.println("돼지");
			break;
		case 4:
			System.out.println("쥐");
			break;
		case 5:
			System.out.println("소");
			break;
		case 6:
			System.out.println("호랑이");
			break;
		case 7:
			System.out.println("토끼");
			break;
		case 8:
			System.out.println("용");
			break;
		case 9:
			System.out.println("뱀");
			break;
		case 10:
			System.out.println("말");
			break;
		case 11:
			System.out.println("양");
			break;
		}
	}
}
