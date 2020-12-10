import java.util.Scanner;

public class ConditionEx2 {

	public static void main(String[] args) {
		// if에서 사용하는 조건식의 비교연산자가 == 이라면
		// switch구문으로 사용될 수 있다.
		java.util.Calendar now;
		now = java.util.Calendar.getInstance();

		int year = now.get(java.util.Calendar.YEAR);
		int month = now.get(java.util.Calendar.MONTH);
		int date = now.get(java.util.Calendar.DATE);
		System.out.print(year + "년 " + (month+1) + "월 " + date + "일은 "); // 문자열+숫자=문자가 된다..그러므로 (month+1) 해준다.
		//1~3월까지 1분기
		//4~6월까지 2분기
		//7~9월까지 3분기
		//10~12까지 4분기
		switch (month+1) { //switch(표현식) 표현식자료형은 byte, short, char, int, String
		case 1:
		case 2:
		case 3:
			System.out.print("1분기 입니다.");
			break;
		case 4:
		case 5:
		case 6:
			System.out.print("2분기 입니다.");
			break;
		case 7:
		case 8:
		case 9:
			System.out.print("3분기 입니다.");
			break;
		default :
			System.out.println("4분기 입니다.");
		}
		System.out.println("========================================오전오후 알아보기============================================");
		int ampm = now.get(java.util.Calendar.AM_PM);
		System.out.print(ampm+"이므로 ");
		if (ampm == 0) {
			System.out.println("오전");
		} else {
			System.out.println("오후");
		}

		// switch구문으로 사용
//		switch(ampm) {
//		case 0:
//			System.out.println("오전");
//			break;
//		default:
//			System.out.println("오후");
//		}

		System.out.println("========================================출생년도============================================");
		// 사용자로부터 출생년도 입력받기
		System.out.println("***사용자로부터 출생년도 입력받기***");
		System.out.println("[0이면 원숭이, 1이면 닭, 2이면 개, 3이면 돼지 4-쥐, 5-소, 6-호랑이, 7-토끼, 8-용, 9-뱀, 10-말, 11-양]");
		System.out.print("출생년도를 입력하시오:");

		Scanner sc = new Scanner(System.in);
		int birthYear = sc.nextInt();

		// 년도에 해당 동물이름을 출력하시오
		// 년도를 12로 나눈 나머지값이 0이면 원숭이, 1이면 닭, 2이면 개, 3이면 돼지
		// 4-쥐, 5-소, 6-호랑이, 7-토끼, 8-용, 9-뱀, 10-말, 11-양

		for (int i = 0; i < 12; i++) {
			if (birthYear % 12 == i) {
				switch (i) {
				case 0:
					System.out.println("당신은 원숭이띠입니다.");
					break;
				case 1:
					System.out.println("당신은 닭띠입니다.");
				case 2:
					System.out.println("당신은 개띠입니다.");
					break;
				case 3:
					System.out.println("당신은 돼지띠입니다.");
				case 4:
					System.out.println("당신은 쥐띠입니다.");
					break;
				case 5:
					System.out.println("당신은 소띠입니다.");
				case 6:
					System.out.println("당신은 호랑이띠입니다.");
					break;
				case 7:
					System.out.println("당신은 토기띠입니다.");
				case 8:
					System.out.println("당신은 용띠입니다.");
					break;
				case 9:
					System.out.println("당신은 뱀띠입니다.");
				case 10:
					System.out.println("당신은 밀띠입니다.");
					break;
				case 11:
					System.out.println("당신은 양띠입니다.");
					break;
				}
			}
		}

//		for(int i=0; i<12; i++) {
//			if(birthYear%12==i) {
//				System.out.println("당신은"+i+"띠 입니다.");
//			}
//		}

	}
}



