package inheritance;
class Parent{
	int mf = 10;
	int mf1 = 20;
}
class Child extends Parent{
	String mf = "child";
	String mf2 = "child2";
	void cm() {
		System.out.println(mf); //child
		System.out.println(super.mf);//10
		System.out.println(mf1);//20
	}
}
public class VariableTest {
	public static void main(String[] args) {
		Parent p = new Parent();
		System.out.println(p.mf); //10
		System.out.println(p.mf1);//20
		//System.out.println(p.mf2); //컴파일 오류
		
		Child c = new Child();
		System.out.println(c.mf); //child
		System.out.println(c.mf1); //20
		System.out.println(c.mf2);//child2
		
		//c.mf ; //child
		c.cm();
	}

}
