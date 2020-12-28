import java.util.StringTokenizer;

public class StringStringBuffer {

	public static void main(String[] args) {
		String s1,s2,s3,s4;
		s1 = new String("hello");
		s2 = new String("hello");
		s3 = "hello";
		s4 = "hello";
		System.out.println(s1==s2); //false
		System.out.println(s1==s3); //false
		System.out.println(s3==s4); //true
		
		System.out.println(s1.equals(s2)); //true
		System.out.println(s1.equals(s3)); //true
		System.out.println(s3.equals(s4)); //true
		
		System.out.println(s1+"java"); //+연결연산자, s1객체내용불변
		System.out.println(s1);//hello
		
		StringBuffer sb1, sb2, sb3, sb4;
		sb1 = new StringBuffer("hello");
		sb2 = new StringBuffer("hello");
		sb3 = new StringBuffer("hello");
		sb4 = new StringBuffer("hello");
		
		System.out.println(sb1 == sb2); //false
		System.out.println(sb1 == sb3); //false
		System.out.println(sb3 == sb4); //false
		System.out.println(sb1.equals(sb2)); //==와 같음 false
		
		System.out.println(sb1.append("java")); //hellojava
		System.out.println(sb1); //hellojava
		//-----------------------
		char c = s1.charAt(0); //hello의 0번index문자값 h
		int size = s1.length();//hello의 문자길이 5
		byte[]bytes = s1.getBytes(); //hello의 바이트값들
		for(byte b: bytes) {
			System.out.print(b);
			System.out.print(",");
		}
		System.out.println();
		
		//앞뒤가 같은 문자열 :ABBA
		s1 = "ABC";//"ABBA";
		size = s1.length();
		int i;
		for(i=0; i<size/2; i++) {
			if(s1.charAt(i) != s1.charAt(size-1-i)) {
				break;
			}
		}		
		if(i == size/2) {
			System.out.println("앞뒤가 같은 팰린드롬문자열입니다");
		}else {
			System.out.println("앞뒤가 다른 문자열입니다");
		}
		
		
		s1 = "ABCD.javaABCD.javaABCD.java";
		int index = s1.indexOf("D");
		System.out.println(index); //3
		int lastIndex = s1.lastIndexOf("D");
		System.out.println(lastIndex);//21
		System.out.println(s1.indexOf("D", 10));		
		System.out.println(s1.indexOf("JAVA")); //-1
		
		String[]arr = s1.split("\\.java");
		for(String s: arr) {
			System.out.print(s);
			System.out.print(",");
		}
		
		s1 = "90:85::70";//국어90, 영어85, 수학미응시, 과학70
		s2 = "80:60:0:20";//국어80, 영어60, 수학0, 과학20
		System.out.println();
		String []arr1 = s1.split(":");
		for(String s: arr1) {
			System.out.print(s);
			if(s.equals("")) {
				System.out.println("미응시");
			}
			System.out.print(",");
		}
		System.out.println();
		StringTokenizer stk = new StringTokenizer(s1, ":");
		while(stk.hasMoreTokens()) {
			String s = stk.nextToken();
			System.out.print(s);			
			System.out.print(",");
		}
	}

}
