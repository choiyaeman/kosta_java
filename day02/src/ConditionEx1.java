public class ConditionEx1 {

	public static void main(String[] args) {
		char emoji1 = 9996;//가위
		char emoji2 = 9994;//바위
		char emoji3 = 9995;//보
		
		System.out.println("가위바위보게임");
		System.out.print("가위"+emoji1 + ":1, 바위" + emoji2 + ":2, 보" + emoji3+":3을 입력하세요");
		java.util.Scanner sc = 
				new java.util.Scanner(System.in);
		int user = sc.nextInt();
		int computer = (int)(Math.random()*3)+1;
		System.out.print("당신은 ");
		System.out.print(user == 1?emoji1:user == 2?emoji2:user == 3?emoji3:"잘못입력하셨습니다");
				
		System.out.print(". 컴퓨터는 ");
		System.out.println(computer == 1?emoji1:computer == 2?emoji2:emoji3);
		
		if(user == computer) {
			System.out.println("비겼습니다.");
//		}else if((user==1 && computer==3) 
//				||(user==2 && computer==1)
//				||(user==3 && computer==2)) {
//			System.out.println("이겼습니다.");
		}else if((user - computer%3) == 1) {
			System.out.println("이겼습니다.");
		}else {
			System.out.println("졌습니다.");
		}
	}
}