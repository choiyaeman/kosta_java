public class Loop2 {
	public static void main(String[] args) {
		//TODO 10,9,8,7,6,5,4,3,2,1을 출력하시오
		int num = 10;
//		while(num>=1) {
//			if(num < 10) {
//				System.out.print(",");
//			}
//			System.out.print(num);
//			num--;
//		}
		for( ; num>=1; num--) {
			if(num < 10) {
				System.out.print(",");
			}
			System.out.print(num);
		}		
		
		//TODO 10,9,8,7,6
		//     5,4,3,2,1을 출력하시오
		//hint : 줄바꿈만하기- System.out.println();
		for(num=10; num>=1; num--) {
			if(num%5 != 0) {
				System.out.print(",");
			}else {
				System.out.println();
			}
			System.out.print(num);
		}
		//TODO 3단의 결과 : 3  9  15  21  27
		//     2의배수는 출력안함 continue예약어활용
		System.out.println();
		int dan = 3;
		for(int i=1; i<=9; i++) {
			/*if(dan*i % 2 != 0) {
				System.out.print(dan*i);
				System.out.print(' ');
			}*/
			if(dan*i % 2 == 0) {
				continue;
			}
			System.out.print(dan*i);
			System.out.print(' ');
		}
		
		//TODO 3단~7단까지의 구구단결과를 출력하시오
		//     3 6 9 12 15 18 21 25 27
		//     4 8 12 16 20 24 28 32 36
		//     5 10 15 20 25 30 35 40 45
		//     :
		//     7 14 21   ...          63
		System.out.println();
		for(dan=3; dan<=7; dan++) {
			for(int i=1; i<=9; i++) {
				System.out.print(dan*i);
				System.out.print(' ');
			}
			System.out.println();
		}
		
		//TODO 3단~14단까지의 구구단결과를 출력하시오
		//     결과가 50이상인 경우는 출력안함
		//     결과가 100이상인 경우는 반복빠져나오기
		
		//     3 6 9 12 15 18 21 25 27
		//     4 8 12 16 20 24 28 32 36
		//     5 10 15 20 25 30 35 40 45
		//     :
		//     7 14 21   ...          63
		System.out.println();
		out:for(dan=3; dan<=14; dan++) {
			for(int i=1; i<=9; i++) {
				//if(dan*i>=50) {
				//	continue;
				//}
				if(dan*i>=100) {
					break out;
				}
				System.out.print(dan*i);
				System.out.print(' ');
			}
			System.out.println();
		}
		
		//TODO 1
		//     2 3
		//     4 5 6
		//     7 8 9 10
		//을 출력하시오
		System.out.println();
		num=1;
		int row=0;//행
		while(row<4) {
			int col=0;//열
			while(col<=row) {
				System.out.print(num);
				System.out.print(' ');				
				col++;
				num++;
			}
			System.out.println();
			row++;
		}
		//-------------------------
		//TODO 1
		//     2 3
		//     4 5 6
		//     7 8 9 10
		//을 출력하시오
		num=1;
		int i; //행
		for(i=0; i<4; i++) {
			for(int j=0; j<=i; j++) {
				System.out.print(num);
				num++;
			}
			System.out.println();
		}
		
		/*
		System.out.print(num);
		num++;
		System.out.println();
		
		System.out.print(num);//2
		num++;		
		System.out.print(num);//3
		num++;		
		System.out.println();
				
		System.out.print(num);//4
		num++;		
		System.out.print(num);//5
		num++;		
		System.out.print(num);//6
		num++;
		System.out.println();
		
		
		System.out.print(num);//7
		num++;
		System.out.print(num);//8
		num++;
		System.out.print(num);//9
		num++;
		System.out.print(num);//10
		num++;
		System.out.println();
		
		*/
		
		
		
		
		
		
		
		
		
	}
}