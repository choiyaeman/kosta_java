class Student{
	int no;//학번
	String name;//이름
	String major;//전공
}

public class FieldTest {	
	public static void main(String[] args) {
		Student s1 = new Student();
		s1.no = 1;
		s1.name = "오문정";
		s1.major = "체육";
		
		Student s2 = new Student();
		s2.no = 2;
		s2.name = "홍길동";
		s2.major = "컴공";
		
		Student s3 = new Student();
		System.out.println(s1.no + ": " + s1.name);
		System.out.println(s3.no + ": " + s3.name);		
	}
}