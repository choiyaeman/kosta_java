public class ArrayNew {
	public static void main(String[] args) {
		int []arr;//배열선언
		arr = new int[5];//배열생성
		/*
		 * arr[0] = 1; arr[1] = 2; arr[2] = 3; arr[3] = 4; arr[4] = 5;
		 */
		for(int i=0; i<arr.length; i++) {
			arr[i] = i+1;
		}
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
		
//		String []sArr;
//		sArr = new String[12];
//		System.out.println(sArr[6]); //null
//		
//		int [] keyboardArr = new int[3];
//		java.util.Scanner sc =
//				new java.util.Scanner(System.in);
//		int size = keyboardArr.length;
//		String []subjectArr = {"국어", "수학", "영어"};
//		
//		int total = 0; //총점
//		for(int i=0; i<size; i++) {
//			System.out.print(subjectArr[i] + " 과목점수를 입력하세요:");
//			keyboardArr[i] = sc.nextInt();
//			//total +=keyboardArr[i];
//		}
//		
//		//총점계산하기
//		for(int i=0; i<size; i++) {
//			total += keyboardArr[i];
//		}
//		
//		//평균계산하기
//		double avg = (double)total /size; //??
//		
//		System.out.println(">>입력된 과목점수들입니다<<");
//		for(int i=0; i<size; i++) {
//			System.out.print(subjectArr[i] + ":" + keyboardArr[i]);
//			System.out.print(",");
//		}
//		System.out.println("총점:" + total + ", 평균:" + avg);
//		
		
		//TODO 다음배열에 정수6개가 저장될 수 있도록 선언 생성하시오
		int[] lottoArr = new int[6];
		//임의의숫자(1부터 45까지)중 숫자를 대입
//		lottoArr[0] = (int)(Math.random()*45)+1;
//		lottoArr[1] = (int)(Math.random()*45)+1;
//		lottoArr[2] = (int)(Math.random()*45)+1;
//		lottoArr[3] = (int)(Math.random()*45)+1;
//		lottoArr[4] = (int)(Math.random()*45)+1;
//		lottoArr[5] = (int)(Math.random()*45)+1;
		for(int i=0; i<lottoArr.length; i++) {
			lottoArr[i] = (int)(Math.random()*45)+1;//현재숫자
			//중복확인하기. 이전숫자들과 현재숫자가 같은가 비교
			for(int j=0; j<i; j++) {
				if(lottoArr[j] == lottoArr[i]) { //중복인 경우
					i--;
					break;
				}
			}			
		}		
		for(int i=0; i<lottoArr.length; i++) {
			System.out.print(lottoArr[i]);
			System.out.print(",");
		}
	}
}