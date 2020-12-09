import java.util.Scanner;

public class Condition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num;
		num = 12345;
		if (num > 1000) {
			System.out.println(num + "는 1000보다 큰 수 입니다");
		}

		int kor = 97;
		int math = 29;
		int eng = 70;
		// 총점이 50점 이상이면 "합격"을 출력하시오
		int sum = kor + math + eng;
		if (sum >= 50) {
			System.out.println(sum + "점 합격 입니다.");
		}
		// 평균이 50점 이상이면 "합격"을 출력하시오
		int avg = sum / 3;
		if (avg >= 50) {
			System.out.println(avg + "점 합격 입니다.");
		}
		// 평균이 50.6점 이상이면 "합격"을 출력하시오
		double avg1 = sum / 3.0;
		if (avg1 >= 50.6) {
			System.out.println("평균" + avg1 + "점 합격 입니다.");
			if (avg1 >= 90) {
				System.out.println("A등급");
			} else if (avg1 >= 80) {
				System.out.println("B등급");
			} else if (avg1 > 70) {
				System.out.println("C등급");
			} else {
				System.out.println("D등급");
			}
		} else {
			System.out.println("평균" + avg1 + "불합격 입니다.");
		}
		
		//int year = 2020;
		System.out.print("연도를 입력하시오:");
		
		Scanner sc =new Scanner(System.in);
		int year = sc.nextInt();
		
		if(year%12 == 4) {
			System.out.println("쥐띠해 입니다.");
		} else if (year%12==5) {
			System.out.println("소띠해 입니다.");
		} else if (year%12==6) {
			System.out.println("호랑이띠해 입니다.");
		}
		
	}

}
