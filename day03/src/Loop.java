
public class Loop { 
	public static void main(String[] args) {
//		System.out.println("hello");
//		System.out.println("hello");
//		System.out.println("hello");
//		System.out.println("hello");
//		System.out.println("hello");
		
		//while, for, do~while
		int cnt = 5;//반복횟수
		int i = 0;
//		while( i < cnt ) {
//			System.out.println(i +">>hello");
//			i++;
//		}
		
		for(cnt = 5, i = 0; i < cnt; i++) {
			System.out.println(i +">>hello");
		}
		
		//TODO 1,2,3,4,5,6,7,8,9,10을 출력하시오
		/*System.out.println(1);
		System.out.println(2);
		System.out.println(3);
		System.out.println(4);
		System.out.println(5);
		System.out.println(6);
		System.out.println(7);
		System.out.println(8);
		System.out.println(9);
		System.out.println(10);*/
		
//		cnt=10;
//		i=1;
//		while(i <= cnt) {
//			System.out.println(i);
//			i++;
//		}
		for(cnt=10,i=1; i <= cnt; i++) {
			System.out.println(i);
		}
		
		//TODO 1,3,5,7,9,11,13,15,17,19을 출력하시오
//		cnt=10;//반복횟수
//		i=0;
//		int num=1; //출력할 숫자값
//		while(i < cnt) {
//			System.out.println(num);
//			num+=2;
//			i++;
//		}
		
		int num=1;
		for(cnt=10, i=0; i < cnt; num+=2, i++) {
			System.out.println(num);
		}
		
//		cnt=10;
//		i=0;
//		for(int num=1; ; ) {
//			
//		}
		
		/*for(cnt=10, i=0, int num=1; ; ) {//안됨!
		}*/
		
		//TODO 1+2+3+4+5값을 출력하시오
		int sum = 0; //합
		num = 1;
		
//		sum += num; //sum = sum+num; //=0+1
//		num++; //2
//		sum += num; //sum = sum+num; //=(0+1)+2
//		num++; //3
//		sum += num; //sum = sum+num; //=(0+1+2)+3
//		num++; //4
//		sum += num; //sum = sum+num; //=(0+1+2+3)+4
//		num++; //5
//		sum += num; //sum = sum+num; //=(0+1+2+3+4)+5
		
//		while(num <= 5 ) {
//			sum += num;
//			num++;
//		}
		for( ; num<=5; num++) {
			sum += num;
		}
		System.out.println(sum);
		
		//TODO 1~10사이의 홀수합을 구하시오 1+3+5+7+9
		sum=0;
		num=1;
//		while(num <= 10) {
//			sum += num;
//			num += 2;
//		}
		for( ; num<=10; num += 2) {
			sum += num;
		}
		System.out.println(sum);
		
		//TODO 1~10사이의 숫자중 홀수합과 짝수합을 구하시오
		int oddSum = 0; //홀수합
		int evenSum = 0; //짝수합
		num = 1;
		while(num <= 10) {
			if(num%2 == 0) {
				evenSum += num;
			}else {
				oddSum += num;
			}
			num++;
		}
		System.out.println("홀수합:" + oddSum +", 짝수합:" + evenSum);
	
		//TODO 해당숫자가 소수인 경우 "소수입니다", 
		//             소수가 아닌 경우 "소수가 아닙니다"를 출력하시오
		int primeNum = 5; //2로 나누어떨어지는가확인
		                  //3로 나누어떨어지는가확인
		                  //4로 나누어떨어지는가확인
		                  //위의 조건이 아닌 경우가 소수이다
		num=2;
		boolean isPrime = true; //소수이다
		while(num<primeNum) {
			if(primeNum % num == 0) { 
				isPrime = false; //소수가 아니다
			}
			num++;
		}
		
		if(isPrime) { //if(isPrime == true){
			System.out.println("소수입니다");
		}else {
			System.out.println("소수가 아닙니다");
		}
		
		//TODO 숫자1부터의 합이 100미만인 최대숫자를 출력하시오
		//1+2+3+4+5+6+7+8
		sum = 0;		
		num = 1;
		while((sum+num) < 100) {			
			sum += num;
			System.out.println(num + ":" + sum);
			num++;
		}
		num--;
		System.out.println("최대숫자는 " + num);
		
		//do{}while();
		sum = 0;	
		num = 0;
		do {
			num++;
			sum += num;
			System.out.println(num + ":" + sum);			
		}while((sum+(num+1)) < 100);
		System.out.println("최대숫자는 " + num);
	}
}
