class A{ //class A extends Object{로 바뀜
	int num;
	A(int num){
		this.num = num;
	}
	public String toString() {
		return "A의 num은 " + num + "입니다";
	}
	@Override
	public boolean equals(Object obj) {
		//현재객체의 num값과  
		//매개변수로 전달된 객체의 num값이 같으면 true반환
		//그외는 false반환
		if(obj instanceof A) {
			A a = (A)obj;
			//if(this.num == obj.num) {
			if(this.num == a.num) {
				return true;
			}
		}
		return false;
	}	
}
class B{
	String subject;
	B(String subject){
		this.subject = subject;
	}
	public String toString() {
		return "B의 subject은 " + subject+"입니다";
	}
}
public class ObjectTest {
	public static void test(Object p) {
		//System.out.println(p.toString());
		System.out.println(p);
	}
	public static void test(Object obj1, Object obj2) {
		System.out.println("equals test메서드:" + obj1.equals(obj2));//
		//System.out.println("obj1객체정보는" + obj1.toString());
		//System.out.println("obj2객체정보는" + obj2.toString());
		System.out.println("obj1객체정보는" + obj1);
	}
	public static void main(String[] args) {
		B b1,b2;
		b1 = new B("java");
		//B클래스의 toString가 재정의됨. 출력결과: "B의 subject은 java입니다"
		//System.out.println(b1.toString()); 
		test(b1);
		
		b2 = new B("C++");
		//System.out.println(b2.toString()); //"B의 subject은 C++입니다"
		test(b2);
		test(b1, b2);//출력:false
		B b3;
		b3 = b2;
		test(b2, b3);//출력:true
		
		
		
		A a1,a2;
		a1 = new A(10); //num값이 10으로 초기화된 객체
		a2 = new A(10); //num값이 10으로 초기화된 객체
		
		//System.out.println(a1.toString()); //Object클래스의 toString가 상속됨. 출력결과:A@XXXXXXXX
		test(a1);                                   //A클래스의 toString가 재정의됨.    출력결과: A의 num은 10입니다
		System.out.println(a1.equals(a2)); //Object클래스의 equals가 상속됨. ==과 같음 false
		test(a1, a2);//출력:true
		test(a1, b1);//출력:false
		
		Object o1,o2;
		o1 = new Object();
		o2 = new Object();
		System.out.println(o1.hashCode());
		System.out.println(o2.hashCode());
		//System.out.println(o1.toString());
		test(o1);
		//System.out.println(o2.toString());
		test(o2);
		System.out.println(o1 == o2); //false
		System.out.println(o1.equals(o2)); //==와 같음 false
		

	}

}
