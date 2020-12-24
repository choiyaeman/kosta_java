public class Array2 {
	public static void main(String[] args) {
		int [][]arr;
		arr = new int[3][2];
		System.out.println(arr.length); //3
		System.out.println(arr[0].length);//2
		
		System.out.println(arr);
		System.out.println(arr[0]);
		
		System.out.println(arr[0][0]);//0
		System.out.println(arr[2][1]);//0
		
		int rowSize = arr.length;
		int colSize = arr[0].length;
		for(int row=0; row<rowSize; row++) {
			arr[row][0] = row+1;
			arr[row][1] = row+1;
		}
		
		int num=1;
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
		
		char[][] cArr = new char[3][2];
		rowSize = cArr.length;
		colSize = cArr[0].length;
		char c = 'A';
		for(int row=0; row<rowSize; row++) {
			for(int col=0; col<colSize; col++) {
				cArr[row][col] = c;
				c++;
			}
		}
		
		for(int row=0; row<rowSize; row++) {
			for(int col=0; col<colSize; col++) {
				System.out.print(cArr[row][col]);				
			}
			System.out.println();
		}
		
		
		
		char[][] cArr2 = new char[5][];
		/*
		 * cArr2[0] = new char[1]; cArr2[1] = new char[2]; cArr2[2] = new char[3];
		 * cArr2[3] = new char[4]; cArr2[4] = new char[5];
		 */	
		rowSize = cArr2.length;
		for(int row=0; row<rowSize; row++) {
			cArr2[row] = new char[row+1];
		}
		
		for(int row=0; row<rowSize; row++) {
			for(int col=0; col<cArr2[row].length; col++) {
				cArr2[row][col] = '☆'; //'*'				
			}
		}
		
		for(int row=0; row<rowSize; row++) {
			for(int col=0; col<cArr2[row].length; col++) {
				System.out.print(cArr2[row][col]);
			}
			System.out.println();
		}
		
		
		
		int[][]arr3;//배열선언
		arr3 = new int[7][];//생성시 행수지정
		rowSize = arr3.length;
		for(int row = 0; row<rowSize; row++) { //행별 열수지정
			arr3[row] = new int[row+1];
		}
		
		for(int row = 0; row<rowSize; row++) {//행열에 값대입
			colSize = arr3[row].length;
			arr3[row][0] = 1;
			arr3[row][colSize-1] = 1;
			for(int col=1; col<colSize-1; col++) {
				if(row > 0) {
					arr3[row][col] = arr3[row-1][col-1] + arr3[row-1][col];
				}
			}
		}
		for(int row = 0; row<rowSize; row++) {
			colSize = arr3[row].length;
			for(int col=0; col<colSize; col++) {
				System.out.print(arr3[row][col]);
			}
			System.out.println();
		}
		
		int [][]arr4 = {{1,2,3}, {4,5,6}, {7,8,9}};
		int [][]arr5 = new int[3][3];
		rowSize = arr4.length;//3
		colSize = arr4[0].length;//3
		for(int row=0; row< rowSize; row++) {
			for(int col=0; col< colSize; col++) {
				arr5[col][(rowSize-1)-row] = arr4[row][col];
			}
		}
		
		for(int row=0; row< rowSize; row++) {
			for(int col=0; col< colSize; col++) {
				System.out.print(arr5[row][col]);
			}
			System.out.println();
		}
			
	}
}