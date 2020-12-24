package inheritance;
class Person{
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
class Employee extends Person{
	private String no;
	private int salary;
	public Employee() {}
	public Employee(String no) {
		this.no = no;
	}
	public Employee(String no, String name) {
		this.no = no;
		setName(name);
	}
	public Employee(String no, String name, int salary) {
		this.no = no;
		setName(name);
		this.salary = salary;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void calcSalary() {
		System.out.println("계산된 연봉은" + (salary*12)+"입니다");
	}
	
}
class TempEmployee extends Employee{	
	private int time;
	public TempEmployee() {}
	public TempEmployee(String no) {
		setNo(no);
	}
	public TempEmployee(String no, String name) {
		setNo(no);
		setName(name);		
	}
	public TempEmployee(String no, String name, int time) {
		setNo(no);
		setName(name);
		this.time = time;
	}
	public TempEmployee(String no,String name, int time, int salary) {
		setNo(no);
		setName(name);
		setSalary(salary);
		this.time = time;		
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void calcSalary() {
		System.out.println("계산된 연봉은" + (getSalary()*time)+"입니다");
	}
}
public class EmployeeTest {
	public static void main(String[] args) {
		int cnt = 0;
		Employee[] employees = new Employee[100];
		employees[cnt++] = new Employee("001", "오문정", 1000);
		employees[cnt++] = new Employee("002", "홍길동", 800);
		employees[cnt++] = new TempEmployee("003", "비정규1", 100, 1000);
		employees[cnt++] = new TempEmployee("004", "비정규2", 50, 800);
		
		for(int i=0; i<cnt; i++) {
			Employee e = employees[i];
			String no = e.getNo();
			String name = e.getName();
			int salary = e.getSalary();	
			System.out.print("사번("+ no + ")" + name +"사원의 기본급은" + salary+",");
			employees[i].calcSalary();	
		}
	}
}
