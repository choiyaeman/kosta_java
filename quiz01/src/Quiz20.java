public class Quiz20 {
	public static void main(String[] args) {
		int [ ][ ]arr = new int[4][3];
		/*
		 * [0][0]에는 1, 
[1][0]에는 2, 
[2][0]에는 3, 
[3][0]에는 4,
[0][1]에는 5, 
[1][1]에는 6,
[2][1]에는 7,
[3][1]에는 8
[0][2]에는 9
[1][2]에는 10.
[2][2]에는 11,
[3][2]에는 12가 저장되도록 한다 
		 */
		  //TODO이곳을 완성하시오.
		int num=1;
		for(int col=0; col<arr[0].length; col++) {
			for(int row=0; row<arr.length; row++) {
				arr[row][col] = num++;
			}
		}
		for(int i=0; i<arr.length; i++){
		   for(int j=0; j<arr[i].length; j++){
		      System.out.print(arr[ i ][ j ] );
		  }
		  System.out.println( );
		}

	}
}