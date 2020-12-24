class Employee{
	String no; //사원 번호
	String name;//사원 이름
	int salary;//급여
	Employee(){	}
	Employee(String no, String name){
		//this.no = no;
		//this.name = name;
		this(no, name, 0);
	}
	Employee(String no, String name, int salary){
		this.no = no;
		this.name = name;
		this.salary = salary;
	}
}
class Rectangle{
	int width;
	int height;
	double area;
	Rectangle(int size){
		//this.width = size;
		//this.height = size;
		this(size, size);//다른 생성자를 호출한다
	}
	Rectangle(int width, int height){
		this.width = width;
		this.height = height;
	}
	void calcArea() {
		area = this.width * height;
	}
	void display() {
		System.out.println("가로" + width 
				        +", 세로" + height
				        +"인 사각형의 면적은 " +area + "입니다");
	}
}
public class ConstructorTest {
	public static void main(String[] args) {
		//Employee e1 = new Employee();
		//e1.no = "001"; e1.name="오문정"; e1.salary=100;
		Employee e1 = new Employee("001", "오문정", 100);				
		
		//Employee e2 = new Employee();
		//e2.no = "002"; e2.name="홍길동"; e2.salary=200;
		Employee e2 = new Employee("002", "홍길동", 200);
		
		//Employee e3 = new Employee();
		//e3.no = "011"; e3.name="나자바";
		Employee e3 = new Employee("011", "나자바");
		
		//int totalSalary = e1.salary + e2.salary + e3.salary;
		//System.out.println("총급여: " + totalSalary);
		
		Rectangle r1 = new Rectangle(3,4); //가로길이가3이고 세로길이가4인 사각형객체
		r1.calcArea(); //사각형의 면적을 계산한다
		r1.display(); //"가로3, 세로4인 사각형의 면적은 12.0입니다"가 출력된다
		
		Rectangle r2 = new Rectangle(5); //가로길이 5, 세로길이 5인 사각형객체
		r2.calcArea(); 
		r2.display(); 
	}
}
