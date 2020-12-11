public class ArrayInit {

	public static void main(String[] args) {
		// 배열 초기화
//		int amount1; //1월금액
//		int amount2; //2월금액
//		int amount3; //3월금액
		System.out.println("-----1~12월 금액 총액과 평균 입금액은?-----");
		int[] amount = {100, 1000, 50, 0, 35, 170,500, 600, 20, 300, 40, 60};
		System.out.println("1월 금액:"+amount[0]+"원");
		System.out.println("12월 금액:"+amount[11]+"원");
		System.out.println("index길이="+amount.length); //12
		int totalAmount=0; // 총 금액
		for(int index=0; index<amount.length; index++) {
			totalAmount += amount[index];
		}
		System.out.println("1~12월의 총 금액:"+totalAmount+"원");
		//평균 입금액은 실수값으로 출력하시오
		System.out.println("평균 입금액 실수값으로 출력하시오:"+((double)totalAmount/amount.length)+"원");
//		double avgAmount = (double)totalAmount/amount.length;
//		System.out.println("평균 입금액:"+avgAmount);
		
		int max; //최대금액
//		max = amount[0];
//		if(max < amount[1]) {
//			max = amount[1];
//		}
//		if(max < amount[2]) {
//			max = amount[2];
//		}
//		if(max < amount[3]) {
//			max = amount[3];		
//		}
		int i=0; 
		max = amount[0];
		for(i=0; i<amount.length; i++) {
			if(max < amount[i]) {
				max = amount[i];
			} 
		}
		System.out.println("최대금액:"+max+"원");
		
		// 최대금액을 갖는 월과 금액을 출력하시오 ex)최대금액:2월, 1000
		int maxIndex = 0; //최대금액을 갖는 index
		for(int index=1; index<amount.length; index++) {
			if(amount[maxIndex] < amount[index]) {
				maxIndex = index;
			}
		}
		String maxMonth = maxIndex+1+"월";
		max = amount[maxIndex];
		System.out.println("최대금액:" + maxMonth+","+max);
	}

}
