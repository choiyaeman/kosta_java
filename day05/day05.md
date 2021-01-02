# day05 

int[] arr; //배열선언 int arr[];

arr = new int[5]; // 배열생성

4    1       2    3

1. heap(jvm메모리영역)메모리할당

2. 일렬로 나열된 다섯개짜리 방이 만들어진다. 각 방은 int 타입

3. 각 index가 자동초기화 된다. int 타입으로 각 0값으로 초기화..

4. heap메모리공간 참조

boolean[] bArr = new boolean[3];

1. heap(jvm메모리영역)메모리할당

2. 일렬로 나열된 세개짜리 방이 만들어진다. 각 방은boolean 타입

3. boolean타입으로 각 방은 false값으로 초기화 된다.

4. heap메모리공간 참조

String[] sArr = new String[2];

1. heap(jvm메모리영역)메모리할당

2. 일렬로 나열된 두개짜리 방이 만들어진다. 각 방은 String타입

3. String타입으로 각 방은 값으로 null초기화 된다.

(string은 기본형이 아니라 참조형이므로 직접 값을가지고 있지 않기때문에 null값으로 아무값도 참조하지 않는다는 의미이다.)

4. ex) sArr[1]="JAVA" => 1번 인덱스는 JAVA라는 문자열을 참조한다.

arr = null;

System.out.pritnln(arr[0]); // 실행시 NullPointerException 발생. null이므로 arr연결고리가 끊켰을때 발생..프로그램이 죽어 문제가된다.

**2차원 배열**

int[][] arr2;

arr2 = new int[3][2]; // 3행2열이라고 보면된다. 사각형 형태의 그림으로 그리면x

1. new키워드로 배열변수선언

2. new키워드로 메모리할당

(0번방에는 시작정보가 두개가 들어있고, 1번도 두개짜리방에대한 시작정보가 들어있고, 2번도 담고있다.) [0] ->[0] [1], [1]->[0] [1], [2]->[0][1]

3. int 3행 2열짜리값이 자동 초기화. 마지막 파란색 영역 값으로 초기화 된다.

4. 오른쪽 연산이끝나고 = 대입

sop(arr2.length); // 3

sop(arr2[1].length);

int[][] arr3 = new int[3][]; // 행수만 미리 지정.

// 행별 열수를 각각 다르게 => 많은 자료를 관리할때 메모리를 좀 더 효율적으로 쓸 수 있다.

arr3[0] = new int[50]; // 0행에 열을 50개

arr3[1] = new int[2];

arr3[2] = new int[3];

sop(arr3.length); //3

sop(arr3[0].length); //50

sop(arr3[1].length); // 2

sop(arr3[2].length); //3

```java
import java.util.Scanner;

public class ArrayNew {

	public static void main(String[] args) {
		int[] arr; // 배열선언
		arr = new int[5];
//		arr[0] = 0;
//		arr[1] = 1;
//		arr[2] = 2;
//		arr[3] = 3;
//		arr[4] = 4;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
//		arr[0] = 1;
//		arr[1] = 2;
//		arr[2] = 3;
//		arr[3] = 4;
//		arr[4] = 5;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
			//arr[i] = ++i; //x 오류..1번 인덱스부터값이저장되므로.. i++ = i+1 같은것이 아니다 i+1은 i값 자체가 증가되는게 아니다
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		String[] sArr;
		sArr = new String[12];
		System.out.println(sArr[6]); //null
		
		int [] keyboardArr = new int[3];
		Scanner sc = new Scanner(System.in);
		int size = keyboardArr.length;
		String[] subjectArr = {"국어", "수학", "영어"};
		
		int total = 0; //총점
		
		for(int i=0; i<size; i++) {
			System.out.print(subjectArr[i]+"과목점수를 입력:");
			keyboardArr[i] = sc.nextInt();
			//total += keyboardArr[i];
		}
		
		//총점계산하기
		for(int i=0; i<size; i++) {
			total += keyboardArr[i];
		}
		//평균계산하기
		double avg = (double)total / size;
		
		System.out.println(">>과목 점수입니다<<");
		for(int i=0; i<size; i++) {
			System.out.print(subjectArr[i] + ":" + keyboardArr[i]);
			System.out.print(",");
		}
		System.out.println("총점:" + total + ", 평균:" + avg);
		
		
		// 다음 배열에 정수6개가 저장될 수 있도록 선언 생성하시오
		// 임의의 숫자 범위(1~45)중 숫자를 대입
		int[] lottoArr = new int[6];
		
		for(int i=0; i<lottoArr.length; i++) {
			lottoArr[i] = (int)(Math.random()*45)+1; //현재숫자  0.0이상 1.0미만의 실수값을 주어진다. 1이상 46미만
			// 중복환인하기. 이전숫자들과 현재숫자가 같은가 비교
			for(int j=0; j<i; j++) { //현재숫자 바로 앞까지 반복..j는 이전숫자들의 위치
				if(lottoArr[j]==lottoArr[i]) { // 중복인 경우
					i--; // 중복건수를 처리하기의해 반복분 i번째를 다시 내린다. 현재숫자와 이전숫자가 같은경우에는 현재숫자가 다시 발생하게끔. i변수값이 중복x 감소하지않는다.
					break;
				}
			}
			// break 빠져나오면 이곳으로 온다. 다시 i++쪽으로 이동..
		}
		
		for(int i=0; i<lottoArr.length; i++) {
			
			System.out.print(lottoArr[i]);
			System.out.print(",");
		}
		
	}
}
```

```java
public class Array2 {

	public static void main(String[] args) {
		int[][] arr;
		arr = new int[3][2][6];//3개짜리 배열에 2개짜리가 있고 2개짜리배열에 여섯개

		System.out.println(arr.length); //3
		System.out.println(arr[0].length); //2
		
		System.out.println(arr); //직접 값x 참조 메모리 정보값만 들어가 있다.
		System.out.println(arr[0]); //두개짜리 참조하고있는 참조형
		
		System.out.println(arr[0][0]); //0
		System.out.println(arr[2][1]); //0
		
		int rowSize = arr.length;
		int colSize = arr[0].length;
//		1,1,2,2,3,3 이 출력되게 하시오.
//		for(int row=0; row<rowSize; row++) {
//			arr[row][0] = row+1;
//			arr[row][1] = row+1;
//		}
		
		// 1,2,3,4,5,6 출력하시오.
		int num =1;
		for(int row=0; row<rowSize; row++) {
			for(int col=0; col<colSize; col++) {
				arr[row][col] = num;
				num++;
			}
		}
		
		for(int row=0; row<rowSize; row++) {
			for(int col=0; col<colSize; col++) {
				System.out.print(arr[row][col]);
			}
			System.out.println();
		}
		
		//A,B,C,D,E,F 출력하시오.
		char[][] cArr = new char[3][2]; // 문자자료가 저장될수 있는 공간 
		rowSize = cArr.length;
		colSize = cArr[0].length;
		char c = 'A';
	
		for(int row=0; row<rowSize; row++) {
			for(int col=0; col<colSize; col++) {
				cArr[row][col]=c;
				c++;
			}
		}
		for(int row=0; row<rowSize; row++) {
			for(int col=0; col<colSize;col++) {
				System.out.print(cArr[row][col]);
			}
		}	
		System.out.println();
		char[][] cArr2 = new char[5][]; //5개행만 지정.
//		cArr2[0] = new char[1]; // 0행에는 1개열
//		cArr2[1] = new char[2];
//		cArr2[2] = new char[3];
//		cArr2[3] = new char[4];
//		cArr2[4] = new char[5];
		rowSize = cArr2.length;
		for(int row=0; row<rowSize; row++) {
			cArr2[row] = new char[row+1];
		}

		for(int row=0; row<rowSize; row++) {
			for(int col=0; col<cArr2[row].length; col++) {
				cArr2[row][col] = '☆';
			}
		}
		for(int row=0; row<rowSize; row++) {
			for(int col=0; col<cArr2[row].length; col++) {
				System.out.print(cArr2[row][col]);
			}
			System.out.println();
		}
		
		// 파스칼 수열
		// 1
		// 1,1
		// 1,2,1 
		// 1,3,3,1 
		// 1,4,6,4,1 
		// 1,5,10,10,5,1 
		// 1,6,15,20,15,6,1
		int[][] arr3 = new int[7][]; // 배열 선언 및 생성시 행수지정
		rowSize = arr3.length;
		for(int row=0; row<rowSize; row++) { // 행별 열수지정
			arr3[row] = new int[row+1];
			}
		for(int row=0; row<rowSize; row++) { // 행열에 값 대입
				colSize = arr3[row].length;
				arr3[row][0] = 1; //첫행열
				arr3[row][colSize-1] = 1; //끝행열
				for(int col=1; col<colSize-1; col++) {
					if(row>0) { //row=0은 첫번째행이므로 이전행 개념이 없다 // row>1로 해도된다.. 1행보다 크니깐...row가 2부터 시작
						arr3[row][col] = arr3[row-1][col-1] + arr3[row-1][col];  // 이전행열 + 이전행현재행열
					}	
			}
		}

		for(int row = 0; row<rowSize; row++){
				colSize = arr3[row].length;
				for (int col = 0; col<colSize; col++) {
					System.out.print(arr3[row][col]);
				}
				System.out.println();
			}	
		
		System.out.println("----------------------");
		// 1 2 3
		// 4 5 6
		// 7 8 9
		// 오른쪽으로 90도회전되도록 저장하려면
				// 7 4 1
				// 8 5 2
				// 9 6 3
				
				//[0][0]->[0][2] // 원본의 열값을 대상본 행갑으로 쓰이고있다..   원본의 행값은 대상본의 2-원본의 행값
				//[0][1]->[1][2] 
				//[0][2]->[2][2]  
				//[1][0]->[0][1] 
				//[1][1]->[1][1] 
				//[1][2]->[2][1]
				//[2][0]->[0][0] 
				//[2][1]->[1][0] 
				//[2][2]->[2][0]
		
		int[][] arr4 = {{1,2,3}, {4,5,6},{7,8,9}};
		int[][] arr5 = new int[3][3];
		rowSize = arr4.length;
		colSize = arr4[0].length;
		for(int row=0; row<rowSize; row++) {
			for(int col=0; col<colSize; col++) {
				arr5[col][(rowSize-1)-row] = arr4[row][col]; // 원본의 row값만큼 빼기
			}
		}
		for(int row=0; row<rowSize; row++) {
			for(int col=0; col<colSize; col++) {
				System.out.print(arr5[row][col]);
			}
		System.out.println();
		}

	}

}
```

```java
// 다음 배열에 정수6개가 저장될 수 있도록 선언 생성하시오
// 임의의 숫자 범위(1~45)중 숫자를 대입
// 작은 숫자부터 정렬

```
