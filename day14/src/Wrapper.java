
public class Wrapper {

	public static void main(String[] args) {
		int i = 7;
		Object obj;
		obj = i; //컴파일시 아래2줄코드로 변환됨. 오토박싱
//		Integer inte = new Integer(i); //기본형->참조형. 박싱
//		obj = inte; //upcasting
		
		int j;
		j = (Integer)obj; //컴파일시 아래2줄코드로 변환됨. 오토언박싱 
//		Integer inte2 = (Integer)obj; //downcasting
//		j = inte2.intValue(); //참조형->기본형. 언박싱
		
		//i.toString() (X)
		Integer inte = new Integer(i);
		inte.toString();
		System.out.println("int타입의 최대값:" + Integer.MAX_VALUE); 
		
		Object[] arr = new Object[10];
		arr[0] = new Object();
		arr[1] = new String("hello");
		arr[2] = new StringBuffer("hello");
		arr[3] = 10; //autoboxing 컴파일시 arr[3] = new Integer(10);
		arr[4] = true; //autoboxing      arr[4] = new Boolean(true);
	
		String[]arr1 = {"a", "b", "c"};
		Object obj2 = arr1;
		
		
		//Shallow Copy & Deep Copy
		
		
		
	
	
	}
}
