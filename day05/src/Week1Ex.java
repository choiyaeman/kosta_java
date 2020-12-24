
public class Week1Ex {
	public static void main(String[] args) {
		//TODO 1. 다음 금액을 최소동전개수로 환전하시오
		//금액이 1243원인 경우 
		//출력결과 500원-2개, 100원-2개, 10원-4개, 1원-3개
		//금액이 150원인 경우
		//출력결과 100원-1개, 50원-1개
		int money=1243;
		int coin500=500;
		int coin100=100;
		int coin50=50;
		int coin10=10;
		int coin1=1;
//		int cnt500 = 1243/coin500;
//		int cnt100 = (1243%coin500)/coin100;
//		int cnt50 = ((1243%coin500)%coin100)/coin50;
//		int cnt10 = (((1243%coin500)%coin100)%coin50)/coin10;
//		int cnt1 =  (((1243%coin500)%coin100)%coin50)%coin10;
		//System.out.println(cnt500 + "," + cnt100 + "," + cnt50 + "," + cnt10);
//		if(cnt500 != 0) {
//			System.out.print(coin500 + "원-" + cnt500);
//		}
//		if(cnt100 != 0) {
//			System.out.print("," + coin100 + "원-" + cnt100);
//		}
//		if(cnt50 != 0) {
//			System.out.print("," + coin50 + "원-" + cnt50);
//		}
//		if(cnt10 != 0) {
//			System.out.print("," + coin10 + "원-" + cnt10);
//		}
//		if(cnt1 != 0) {
//			System.out.print("," + coin1 + "원-" + cnt1);
//		}
		
		int []coinArr = {500, 100, 50, 10, 1};
		for(int i=0; i<coinArr.length; i++) {
			int cnt = money / coinArr[i];
			money = money % coinArr[i];
			if(cnt != 0) {
				if(i>0) {
					System.out.print(",");
				}
				System.out.print(coinArr[i]+"원-" + cnt);
			}
		}
		
		
		
		//TODO 2. 숫자 1,2,3,4,...10까지 for문으로 출력하시오
		System.out.println();
		for(int num=1; num<=10; num++) {
			System.out.print(num+",");
		}
		
		//TODO 3. 알파벳 'A','B','C','D',....'Z'을 출력하시오
		System.out.println();
		for(char c='A'; c<='Z'; c++) {
			if(c>'A') {
				System.out.print(",");
			}
			System.out.print(c);
		}
		
		//TODO 4. 피보나치 수열 10개를 출력하시오
		//    피보나치 수열자료를 찾아보세요
		//    출력결과는 1, 1, 2, 3, 5, 8, 13, 21, 34, 55
		System.out.println();
		int bb,b,c;
		bb = 1;
		b = 0;
		for(int i=0; i<10; i++) {
			c = bb+b;
			bb = b;
			b = c;
			if(i>0) {
				System.out.print(',');
			}
			System.out.print(c);
		}
//		c = bb+b; //1		
//		bb = b; //0
//		b = c; //1
//		
//		c = bb+b;//1
//		bb = b; //1
//		b = c; //1
//		
//		c = bb+b; //2	
		
		
		//TODO 5. 년도에해당하는 동물을 출력하시오
		//    단, zodiacSign배열에 저장된 동물값을 출력합니다.
		System.out.println();
		String[] zodiacSign = {"원숭이", "닭", "개", "돼지", "쥐", "소", 
	              "호랑이", "토끼", "용", "뱀", "말", "양"};
		int year = 2020;
		System.out.println(zodiacSign[year%12]);
		
		//TODO 6.다음 numArr배열은 1부터 10사이의 숫자들로 구성되어있다.
		//     숫자들의 출현횟수를 출력하시오
		//출력결과는 1의 출현횟수는 2회
		//        2의 출현횟수는 1회
		//        3의 출현횟수는 4회
		//        4의 출현횟수는 0회
		//        5의 출현횟수는 1회
		//        6의 출현횟수는 0회
		//        7의 출현횟수는 0회
		//        8의 출현횟수는 1회
		//        9의 출현횟수는 0회
		//        10의 출현횟수는 2회
		int[] numArr = {1, 3, 1, 10, 3, 5, 8, 3, 10, 2, 3};
		int[] cntArr = new int[10]; //[0]는 1의 출현횟수가 누적될 방
		                            //[1]는 2의 출현횟수가 누적될 방
		                            //[2]  3           
		for(int i=0; i<numArr.length; i++) {
			int num = numArr[i];
			cntArr[num-1]++;
		}
		for(int i=0; i<cntArr.length; i++) {
			System.out.println((i+1)+"의 출현횟수는" + cntArr[i] +"회");
		}
	}
}
