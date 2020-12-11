import java.util.Scanner;

public class Loop2 {

	public static void main(String[] args) {
		// 10,9,8,7,6,5,4,3,2,1을 출력하시오
		System.out.print("10~1을 출력 하시오: ");
		int num=10;
//		while(num>=1) {
//			System.out.print(num+", ");
//			num--;
//		}
		for( ; num>=1; num--) {
			if(num < 10) {
				System.out.print(",");
			}
			System.out.print(num);
		}
		System.out.println();
		System.out.println("=====================================");
		//10,9,8,7,6
		//5,4,3,2,1을 출력하시오
		for(num=10; num>=1; num--) {
			if(num%5!=0) {
				System.out.print(",");
			} else {
				System.out.println();
			}
			System.out.print(num);
		}

		System.out.println();
		//3단의 결과: 3 6 9 12 15 18 21 24 27
		//2의 배수는 출력안함 continue예약어 활용
		int dan =3;
		for(int i=1; i<=9; i++) {
//			if(dan*i % 2 != 0) {
//				System.out.print(dan*i);
//				System.out.print(' ');
//			}
			if(dan*i%2==0) {
				continue;
			}
			System.out.print(dan*i);
			System.out.print(' ');
//			if(i%2==1) {
//				System.out.print(dan*i);
//				System.out.print(' ');
//			}

		}

		
		//3단~7단까지의 구구단 결과를 출력하시오
		//결과가 50이상인 경우는 출력안함
		//결과가 100이상인 경우는 반복빠져나오기
		//3 6 9 12 15 18 21 25 27
		//4 8 12 16 2824 28 32 36
		//5 ~~
		//6 ~~
		//7 ~~
		System.out.println();
		System.out.println("====================");
		dan=1;
		out:for(dan=3; dan<=14; dan++) {
			for(int i=1; i<=9; i++) {
//				if(dan*i>=50) {
//					continue;
//				}
				if(dan*i>=100) {
					break out; //label설정안하면 12*9하고 빠져나와야하는데 안나오고 다음에 13단 반복문으로 진행된다..그러므로 out지정
				}
				System.out.print(dan*i);
				System.out.print(' ');
//				if(dan*i<50) {
//					System.out.print(dan*i);
//					System.out.print(' ');
//				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("====================");
		//1
		//2 3
		//4 5 6
		//7 8 9 10
		num=1;
		int i; //행
		for(i=0; i<4; i++) {
			for(int j=0; j<=i; j++) {
				System.out.print(num);
				num++;
			}
			System.out.println();
		}
//		num=1;
//		int row=0;//행
//		while(row<4) {
//			int col=0;//열
//			while(col<=row) {
//				System.out.print(num);
//				System.out.print(' ');
//				col++;
//				num++;
//			}
//			System.out.println();
//			row++;
//		}

		// 사용자로부터 출생년도 입력받기
		System.out.println("***사용자로부터 출생년도 입력받기***");
		System.out.println("[0이면 원숭이, 1이면 닭, 2이면 개, 3이면 돼지 4-쥐, 5-소, 6-호랑이, 7-토끼, 8-용, 9-뱀, 10-말, 11-양]");
	
		Scanner sc = new Scanner(System.in);
		while(true) {// birthYear!=0은 birthYear를 선언도 하지 않았는데 비교할수가 없다..그러므로 true로 주고시작..
			System.out.print("출생년도를 입력하시오[종료하려면 0을 입력]:");
			int birthYear = sc.nextInt();
			if(birthYear == 0) {
				break;
			}
	
			// 년도에 해당 동물이름을 출력하시오
			// 년도를 12로 나눈 나머지값이 0이면 원숭이, 1이면 닭, 2이면 개, 3이면 돼지
			// 4-쥐, 5-소, 6-호랑이, 7-토끼, 8-용, 9-뱀, 10-말, 11-양
	
			for (i = 0; i < 12; i++) {
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
		}
	}
}


