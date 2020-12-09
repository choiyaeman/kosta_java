import java.util.Scanner;

public class ConditionEx1 {

	public static void main(String[] args) {
		// 가위바위보게임
		// 컴퓨터가 낸 값(Math.random()활용)과
		// 사용자가 낸 값(Scanner의 nextInt()활용)을 비교하여
		// 사용자 입장에서 결과를 출력한다
 		// 예를 들면 사용자가 이기면 "이겼습니다" 지면 "졌습니다" 비기면 "비겼습니                    다".로 출력하시오
		int computer = (int) (Math.random() * 3 + 1);
		System.out.print("사용자는 가위:1, 바위:2, 보:3 중 입력하시오:");
		Scanner sc = new Scanner(System.in);
		int user = sc.nextInt();
		
		System.out.println("컴퓨터가 낸 값: " + computer);
		if (user==computer) {
				System.out.println("비겼습니다.");
			}
		if(user==1) {
			if(computer == 2) {
				System.out.println("사용자가 졌습니다.");
			} else if(computer == 3){
				System.out.println("사용자가 이겼습니다.");
			} 
		}
		if(user==2) {
			if(computer == 3) {
				System.out.println("사용자가 졌습니다.");
			} else if(computer == 1){
				System.out.println("사용자가 이겼습니다.");
			} 
		}
		if(user==3) {
			if(computer == 1) {
				System.out.println("사용자가 졌습니다.");
			} else if(computer == 2){
				System.out.println("사용자가 이겼습니다.");
			} 
		}
	}
}
