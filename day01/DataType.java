public class DataType{
	public static void main(String[] args) {
		System.out.println("============Byte type=================");
		byte b; // 변수선언 -128~127
		//b = 128; 오류 범위..
		b = -127;
		System.out.println(b);
		b = 0;
		System.out.println(b);

		System.out.println("============long type=================");
		long l = 10L; // 변수 선언과 대입. L이 안붙으면 10이라는 숫자가 int type으로 인식.. 그러므로 L대문자로 주자
		l = 1234567890L; // l = 12345678901; 1234... int로 인식..
		System.out.println(l);

		System.out.println("============double type===============");
		double d;
		d = 12.34567;
		//리터럴 표현법
		System.out.println(10);	// 컴파일러가 int로 이해
		System.out.println(10L);	// long 타입으로 이해
		System.out.println(10.4F);	// float type
		System.out.println(10.4);	// double type

		System.out.println("============char type=================");
		char c;
		c = '가';
		System.out.println(c); //가
		c = 44032;
		System.out.println(c);	// 2진수 0과 1로 구성되어 있는 숫자값인데 출력할때 자료형의 형태로 복원 하므로 가 출력이 된다..
		c = 97; // a
		System.out.println(c);
		c = 45;
		System.out.println(c);

		System.out.println("============boolean type===============");
		boolean flag;
		flag = true;
        	// flag = 1; // 컴파일 오류
		System.out.println(flag);
		flag = false;
		// flag = 0; // 컴파일 오류
		System.out.println(flag);
	}
}
