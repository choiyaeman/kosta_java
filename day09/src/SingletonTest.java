class A{
	private static A sa = new A();
	private A(){}
	public static A getInstance() {
		return sa;
	}
}
public class SingletonTest {

	public static void main(String[] args) {
		//new A(); //이 작업은 compile error가 발생된다!
		A a1 = A.getInstance();
		A a2 = A.getInstance();
		System.out.println(a1 == a2);//true
	}

}
