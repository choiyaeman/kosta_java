import java.util.Scanner;

public class Loop {
	public static void main(String[] args) {
//		System.out.println("hello");
//		System.out.println("hello");
//		System.out.println("hello");
//		System.out.println("hello");
//		System.out.println("hello");

		// while, for, do~while
		System.out.println("================while문=====================");
		int cnt = 5; // 반복횟수
		int i = 0;
		while (i < cnt) { // 조건이 참이면 블록수행
			System.out.println(i + ">>hello");
			i++;
		}
		System.out.println("================for문=====================");
		for(cnt=5, i=0; i<cnt; i++) {
			System.out.println(i + ">>hello");
		}
		
		System.out.println("=============================================");
		// 1,2,3,4,5,6,7,8,9,10을 출력하시오
		System.out.println("1~10을 출력하시오");
//		int num=1;
//		while(num<11) {
//			System.out.print(num+" ");
//			num++;
//		}	
		cnt = 10;
		//i = 0;
		i=1;
//		while (i < cnt) {
//			++i;
//			System.out.print(i + " ");
//		}
		
		for(cnt=10, i=1; i<=cnt; i++) {
			System.out.print(i+" ");
		}
		// 1,3,5,7,9,11,13,15,17,19 홀수 10개를 출력하시오
		System.out.println();
		System.out.println("1~19 홀수만 출력하시오");
		System.out.println("================while문=====================");
		cnt = 10;// 반복횟수
		i = 0;
		int num = 1;// 출력할 숫자값
		while (i < cnt) {
			System.out.print(num + " ");
			num += 2;
			i++;
		}
		System.out.println();
		System.out.println("================for문=====================");
		num=1;
		for(cnt=10, i=0; i<cnt; num+=2, i++) {
			System.out.print(num+" ");
		}
		
		/*
		cnt=10;
		i=0;
		for(int num=1;) {
			
		}*/

		System.out.println();
		// 1+2+3+4+5값을 출력하시오
		System.out.println("================while문=====================");
		System.out.print("1+2+3+4+5값을 출력:");
		int sum = 0; // 합
		num=1;
		while(num<=5) {
			sum +=num;
			num++;
		}
		System.out.print(sum);
		
		System.out.println();
		System.out.println("================for문=====================");
		
		sum=0;
		num=1;
		for(; num<=5;  num++) {
			sum += num;
		}
		System.out.println(sum);
		//1~10사이의 홀수합을 구하시오 1+3+5+7+9
		System.out.print("1~10사이의 홀수합:");
		num=1;
		sum=0;
		while(num<=10) {
			sum +=num;
			num+=2;
		}
		System.out.print(sum);
		System.out.println();
		System.out.println("================for문=====================");
		for(; num<=10; num +=2) {
			sum +=num;
		}
		System.out.print(sum);
		
		System.out.println();
		System.out.println("==========================================");
		// 1~10사이의 숫자중 홀수합과 짝수합을 구하시오
		int oddSum = 0; //홀수형
		int evenSum = 0; //짝수형
		num = 1;
		while(num<=10) {
			if(num%2==1) {
				oddSum += num;	
			} else {
				evenSum += num;	
			}
			num++;
		}
		System.out.println("홀수합:"+oddSum+"짝수합:"+evenSum);
		
		// 소수 : 1보다 크고 1과 자기 자신만을 약수로 가진다.
		//해당숫자가 소수인경우 "소수입니다"
		//소수가 아닌 경우 "소수가 아닙니다"로 출력하시오
		int primeNum = 5;
		//2로 나누어 떨어지는가 확인
		//3로
		//4
		//위의 조건이 아닌 경우가 소수이다
		num=2;
		boolean isPrime = true; // 소수이다.
		while(num<primeNum) {
			if(primeNum%num == 0) {
				isPrime = false; // 소수가 아니다
			}
			num++;
		}
		
		if(isPrime) {
			System.out.println("소수입니다.");
		} else {
			System.out.println("소수가 아닙니다.");
		}
		
//		Scanner sc = new Scanner(System.in);
//		System.out.print("소수판별 입력:");
//		int primeNum = sc.nextInt();
//		System.out.println("입력된 숫자:"+primeNum);
//		int max= primeNum-1;
//		int start=2;
//		
//		while(start<=max) {
//			if(primeNum%start==0) {
//				System.out.println("입력된 숫자"+primeNum+"는 소수가 아닙니다.");
//				System.exit(0);
//			} else {
//				start+=1;
//			}
//			System.out.println("입력된 숫자"+primeNum+"는 소수입니다.");
//		}

		
//		sum += num; //sum=sum+num; //=0+1
//		num++; //2
//		sum += num; //sum=sum+num; //=(0+1)+2
//		num++; //3
//		sum += num; //sum=sum+num; //=(0+1+2)+3
//		num++; //4
//		sum += num; //sum=sum+num; //=(0+1+2)+3
//		num++; //5
//		sum += num; //sum=sum+num; //=(0+1+2)+3
		System.out.println("==============while();====================");
		//숫자1부터의 합이 100미만인 최대숫자를 출력하시오
		sum=0;
		num=1;
		while((sum+num) < 100) {
			sum+=num;
			System.out.println(num+ ":" + sum);
			num++;
		}
		num--;
		System.out.println("최대숫자는" + num);
		
		System.out.println("==============do{}while();====================");
		//do{}while();
		sum = 0;
		num = 0;
		do {
			num++;
			sum += num;
			System.out.println(num+":"+sum);
		} while((sum+num)<100);
		System.out.println("최대숫자는" + num);
		
		
//		int k=0;//최대숫자
//		for(; (sum+num)<100; num++) {
//			if(sum<100) {
//				sum +=num;
//				k=num;
//			}
//			System.out.println("숫자:"+num+"총합:"+sum);
//		}
//		System.out.println("최대숫자:"+k);
//		System.out.println("==================================");
//		num=1;
//		sum=0;
//		k=0;
//		while((sum+num)<100) {
//			sum +=num;
//			k=num;
//			num++;
//		}
//		System.out.println("최대숫자:"+k);
		

	}
	
}
