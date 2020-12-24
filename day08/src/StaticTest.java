class A{
	int mf; //객체생성시 자동초기화, HEAP
	static int smf; //클래스 로드시 자동초기화, CLASS AREA,
	void method1() {
		System.out.println("method1()입니다");
		System.out.println(this.mf);
		this.method2();
		
		System.out.println(smf);
		smethod2();
	}
	void method2() {
		
	}
	static void smethod1() {
		System.out.println("smethod1()입니다");
		//System.out.println(this.mf);
		//System.out.println(mf);
		//method2();
		System.out.println(smf);
		System.out.println(A.smf);
		smethod2();
		A.smethod2();
	}
	static void smethod2() {
		
	}
}
public class StaticTest {
	public static void main(String[] args) {
		//System.out.println(A.mf);
		System.out.println(A.smf); //Good Code		
		A a1 = new A();
		System.out.println(a1.mf);
		System.out.println(a1.smf);//0
		a1.mf++;
		a1.smf++;
		
		A a2 = new A();
		System.out.println(a2.mf); //0
		System.out.println(a2.smf);//1
		
		System.out.println(A.smf);//1	
		
		//A.method1();
		a1.method1();
		a2.method1();
		
		A.smethod1();
		a1.smethod1();
		
		
		double radius = 5.2;
		double area = Math.pow(radius, 2) * Math.PI;
		
		//직각삼각형 빗변길이계산 
		double width = 3;
		double height = 4.0;
		double hypo;//빗변 hypo^2 = width^2 + height^2
		hypo = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
		System.out.println(hypo);
		
		
	}

}
