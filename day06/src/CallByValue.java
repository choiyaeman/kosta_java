public class CallByValue {

	public static void test(int i) {
		i=99;		
	}
	public static void test(int[] arr) {
		arr[0] = 99;
	}
	public static void main(String[] args) {
		int i=1;
		test(i);
		System.out.println(i); //1? 99?
		
		int []arr = {1,2,3};
		test(arr);
		System.out.println(arr[0]); //1? 99?
	}

}
