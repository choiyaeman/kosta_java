public class Quiz19 {
	public static void main(String[] args) {
		int [ ] arr = { 4, 6, 1, 9, 5, 3, 7};
		int size = arr.length;
		//이곳을 완성하시오.
		//SELECTION SORT 내림차순
		for(int i=0; i<size-1; i++) {
			for(int j=i+1; j<size; j++) {
				if(arr[i] < arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}

		//BUBBLE SORT 내림차순
//		int bubbleSize = size-1;
//		for(int i=0; i<bubbleSize; i++) {
//			for(int j=0; j<bubbleSize-i; j++) {
//				if(arr[i] > arr[j]) {
//					int temp = arr[i];
//					arr[i] = arr[j];
//					arr[j] = temp;
//				}
//			}
//		}
		for(int i=0; i<arr.length; i++){
		    System.out.print(arr[i]);   //arr변수 오타!
		   System.out.print("\t");
		}
		System.out.println();

	}

}
