# day11

![day11%204a997c36271d46ad8ef0e1e2af871f94/20201222_093111.jpg](day11%204a997c36271d46ad8ef0e1e2af871f94/20201222_093111.jpg)

Person이라는 클래스가 있고 하위 클래스인 Employee

Person타입의 참조 변수 p, Employee의 참조 변수 e 와 new로 객체 생성

p변수의 자료형은 Person클래스 타입, p가 참조하는 객체를 보면 Person클래스 타입 객체

name멤버변수 string타입이므로 초기값 null, p변수가 Person객체를 참조..Person내부에는 메서드 선언부 까지도 객체 내에 있다. Employee의 e변수 자료형이 Employee타입이다 e가 참조하는 객체 new키워드로 Employee. Employee안에 Person타입 객체가 그대로 또 만들어지고 no, salary, calcSalary메서드가 있다. no변수는 null, salary는 0 으로 초기화 될 것 이다.

TempEmployee te = new TempEmployee(); te변수의 자료형은 TempEmployee타입이고 te변수가 참조하고 있는 객체 스택 영역에 저장 되겠죠. Temployee객체가 만들어지면 안에 부모 영역이 있겠죠

그러므로 안쪽에 Employee가있고 안쪽에 Person도 있을 것 이다. 또한 time, 자기만의 메서드 중에서 calcSalary가 있다. 메소드들을 보면 중복된 메소드가 보인다. 중복된 메소드가 employee에서 calcSalary, Tempemployee에서 calcSalary가 있다. 부모 쪽의 calcSalary가 가려지고 자식 쪽의 메소드 calcSalary메소드를 우선한다. 단 salary변수도 중복이 되나 **변수는 오버라이딩이 안된다**. 따라서 private이어서 e.salary하면 컴파일 오류가 뜨고 private이 아니더라도 Employee의 salary변수를 참조.

e = te; 기존 연결 고리를 끊고 te의 값으로 채워라 즉 e가 참조하는 객체는 TempEmployee가 되는 것 이다. 자식이 부모 타입으로 자동 형 변환 되는 것이다. 객체 메모리를 다  참조 할 수는 없고 Employee영역에만 참조하게 된다. e.calcSalary()로 호출하면 e가 참조하는 파란색 영역을 들어가서 그 안에 보면 calcSalary가 있는가? 찾아봅시다. 메소드가 있으나 가려져 있다. 호출하면 재정의 해 놓은 Tempemployee가 갖고 있는 calcSalary 메소드가 호출된다.

**객체 생성 시 사용된 클래스 메소드가 오버라이딩된 메소드가 유지**된다.

**변수는 오버라이딩이 안된다.**

```java
package inheritance;

public class PersonTest {
	public static void main(String[] args) {
		int cnt = 0;
		Employee[] employees = new Employee[100];
		employees[cnt++] = new Employee("001", "오문정", 1000);
		employees[cnt++] = new Employee("002", "홍길동", 800);
		employees[cnt++] = new TempEmployee("003", "비정규1", 100, 1000); //TempEmployee타입이 Employee타입으로 upcasting
		employees[cnt++] = new TempEmployee("004", "비정규2", 50, 800);	//TempEmployee타입이 Employee타입으로 upcasting
		
		for(int i=0; i<cnt; i++) {
			Employee e = employees[i];
			String no = e.getNo();
			String name = e.getName();
			int salary = e.getSalary();	
			System.out.print("사번("+ no + ")" + name +"사원의 기본급은" + salary+",");
			employees[i].calcSalary();	//Employee의 메소드가 호출될수있고 Tempemployee메소드가 호출 될 수 있다 =>다형성
		}
	}
}
```

```java
package inheritance;

public class Person {
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
```

```java
package inheritance;

public class Employee extends Person{
	private String no;
	private int salary;
	
	public Employee() {}
	public Employee(String no) { //컴파일러가 생성자가 하나라도 있으면 디폴트 생성자 안 만든다
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
		System.out.println(
				String.format("%s의 계산된 연봉은 %d 값입니다.", getName(), salary*12));
	}
 
}
```

```java
package inheritance;

public class TempEmployee extends Employee{
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
	public TempEmployee(String no, String name, int time, int salary) {
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
		System.out.println(
				String.format("%s의 계산된 연봉은 %d 값입니다.", getName(), getSalary()*time)); //부모가 갖고 있는 salary가 private으로 선언된 변수 아무리 자식이라도 접근 불가하므로 getSalary로 가져 와야한다.
	}

}
```

---

[Untitled](https://www.notion.so/f1d0e9b92e6e4a4397a0456807809549)

```java
package inheritance;

public class TempEmployee extends Employee{
	private int time;
	
	public TempEmployee() {}
	public TempEmployee(String no) {
//		setNo(no);
		this(no, null); //1번째 생성자에서 2번째 생성자로 호출하겠다
	}
	public TempEmployee(String no, String name) {
//		setNo(no);
//		setName(name);
//		this(no, name, 0); //2개 ->3개 생성자 호출
		this(no, name, 0, 0); //2번째 생성자에서 4번째 생성자로 호출도 가능
	}
	public TempEmployee(String no, String name, int time) {
//		setNo(no);
//		setName(name);
//		this.time = time;
		this(no, name, time, 0); //3번째 생성자에서 4번째 생성자로 호출
	}
	public TempEmployee(String no, String name, int time, int salary) {
		super(no, name, salary); //부모 생성자 호출해라
//		setNo(no);
//		setName(name);
//		setSalary(salary);
		this.time = time;
	}

	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	public void calcSalary() {
		System.out.println(
				String.format("%s의 계산된 연봉은 %d 값입니다.", getName(), getSalary()*time)); //부모가 갖고 있는 salary가 private으로 선언된 변수 아무리 자식이라도 접근 불가하므로 getSalary로 가져 와야한다.
	}

}
```

---

자바에서 abstract이라는 예약어가 있어서 패키지 이름이 되면 안된다

#기호는 protected를 의미

```java
package abstractfinal;

class Shape {
//	private double area;	// 자기 클래스에서만 접근
	protected double area; // 동일패키지에서 접근가능, 다른 패키지의 하위클래스에서 접근가능 
	public double getArea() {
		return area;
	}
	public void makeArea() {} // 부모쪽에 메소드가 있어야 자식쪽에 메소드를 재정의 할 수 있다. 채울게 없음..도형 면적공식이 없기때문에 로직을 만들수가없다.
}

class Circle extends Shape{
	private double radius;
	public Circle(double radius) {
		this.radius = radius;
	}
	public void makeArea() { //Circle의 makeArea()와 Rectangle의 makeArea()는 오버라이딩이 아니다! 그냥 별개의 메소드이다. 오버라이딩은 부모자식관계에서만이지 형제사이는 X
		area = Math.pow(radius, 2) * Math.PI; // area변수가 private으로 선언 되어있으므로 접근 불가 방법 1)private->public 2)메소드
	}
}

class Rectangle extends Shape{
	private double width,height;	//,로 표기하면 같은 자료형의 변수가 된다
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	public void makeArea() {
		area = width * height;
	}
}

**public** class ShapeTest{
	//s.aa, s.bb, s.~~ 이렇게 되면 메인안에 쓸게 많아지므로 간단히 한 줄로 만들어보자
	public static void test(Shape s) { //s는 Circle타입의 오버라이딩된 메소드, Rectangle타입의 오버라이딩된 메소드를 전달 받는다.
		s.makeArea();	// Circle메소드, Rectangle메소드를 호출 할 수 있다
	}
	public static void main(String[] args) {
		Shape s;
		s = new Circle(5);
		//s.makeArea(); // 자바는 실행시에 메모리가 할당된다. Shape클래스에 makeArea를 찾아보고 메소드,매개변수 맞는지...없으므로 upcasting해도 쓸모가 없는거다. shape클래스에서 makeArea메소드를 선언해주자!
		test(s); //Circle타입 변수인 s를 test메소드인 Shape타입의 s변수로 전달
		
		s = new Rectangle(3,4);
		//s.makeArea(); 
		test(s); //Rectangle타입 변수인 s를 test메소드인 Shape타입의 s변수로 전달
		
	}
}
```

컴파일은 성공했으나 실행이 안된다.  여러 클래스가 저장 되어있는 소스코드에 main메소드를 갖는 클래스는 반드시 public으로 선언 해줘야 한다. 그리고 public 클래스명이  .java파일로 만들어야 한다. →ShapeTest.java

```java
package abstractfinal;

**abstract** class Shape { // 추상메소드를 포함한 클래스는 추상 클래스여야한다. 그러나 추상클래스로 추상메소드를 갖는건 아니다
//	private double area;	// 자기 클래스에서만 접근
	protected double area; // 동일패키지에서 접근가능, 다른 패키지의 하위클래스에서 접근가능 
	public double getArea() {
		return area;
	}
	abstract public void makeArea(); //선언부만 갖도록 하고 앞에 예약어 abstarct을 붙여준다.하위클래스에서 반드시 재정의 할 메소드라면 그 메소드를 추상메소드로 선언 해주면 된다.
}									 // 하위 클래스에서 재정의 안하면 컴파일 에러 뜬다					
class Triangle extends Shape{
	**public void makeArea() {};**
}

class Circle extends Shape{
	private double radius;
	public Circle(double radius) {
		this.radius = radius;
	}
	public void makeArea() { //Circle의 makeArea()와 Rectangle의 makeArea()는 오버라이딩이 아니다! 그냥 별개의 메소드이다. 오버라이딩은 부모자식관계에서만이지 형제사이는 X
		area = Math.pow(radius, 2) * Math.PI; // area변수가 private으로 선언 되어있으므로 접근 불가 방법 1)private->public 2)메소드
	}
}

class Rectangle extends Shape{
	private double width,height;	//,로 표기하면 같은 자료형의 변수가 된다
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	public void makeArea() {
		area = width * height;
	}
}

public class ShapeTest{
	//s.aa, s.bb, s.~~ 이렇게 되면 메인안에 쓸게 많아지므로 간단히 한 줄로 만들어보자
	public static void test(Shape s) { //s는 Circle타입의 오버라이딩된 메소드, Rectangle타입의 오버라이딩된 메소드
		s.makeArea();	// Circle메소드, Rectangle메소드를 호출 할 수 있다
	}
	public static void main(String[] args) {
		Shape s;
		s = new Circle(5);
		//s.makeArea(); // 자바는 실행시에 메모리가 할당된다. Shape클래스에 makeArea를 찾아보고 메소드,매개변수 맞는지...없으므로 upcasting해도 쓸모가 없는거다. shape클래스에서 makeArea메소드를 선언해주자!
		test(s); //Circle타입 변수인 s를 test메소드인 Shape타입의 s변수로 전달
		
		s = new Rectangle(3,4);
		//s.makeArea(); 
		test(s); //Rectangle타입 변수인 s를 test메소드인 Shape타입의 s변수로 전달
		
	}
}
```

**클래스 목적** : 객체를 만들기 위해서 클래스를 설계한다

**클래스의 역할**

1)객체를 만드는 역할

2)하위 클래스들의 공통점을 모아두는 역할

**final 클래스** : 객체를 만드는 역할만 하는 클래스 → 하위 클래스에서 재정의(x)

ex) final class String{}       class MyString extends String{} (x) final 클래스로 만들어진 상속 금지!

**abstract클래스** : 하위 클래스들의 공통점을 모아두는 역할만 하는 클래스 → 객체생성(x)

ex) abstract class Shape{}     new Shape(); (x)

**final메소드** : 하위 클래스에서 재정의 못하는 메소드

**abstract메소드** : 하위 클래스에서 반드시 재정의 해야 하는 메소드

멤버필드 앞에는 abstract 못 쓴다.

![day11%204a997c36271d46ad8ef0e1e2af871f94/Untitled.png](day11%204a997c36271d46ad8ef0e1e2af871f94/Untitled.png)

Calendar 클래스는 추상 클래스로 new로 객체 생성을 못한다

private, abstract → new키워드로 객체 생성(x)

![day11%204a997c36271d46ad8ef0e1e2af871f94/Untitled%201.png](day11%204a997c36271d46ad8ef0e1e2af871f94/Untitled%201.png)

Calendar c = new Calendar(); (X)
Calendar c = Calendar.getInstance();(0)

getInstance는 return타입이 캘린더 타입 이니깐 써라

![day11%204a997c36271d46ad8ef0e1e2af871f94/Untitled%202.png](day11%204a997c36271d46ad8ef0e1e2af871f94/Untitled%202.png)

Calendar c = Calendar.getInstance(); → new GregorianCalendar()타입의 객체를 생성 하도록 코드가 되어있다. Calender가 부모고 자식인 GregorianCalendar()타입의 객체가 만들어진다. c는 Calender 전체를 참조 하는게 아니라 GregorianCalendar을 참조.

Calendar c = new GregorianCalendar(); (0) // 만약 더 좋은 버전이 나왔을 경우 더 좋은 클래스 타입의 객체를 만들 수 있기 때문에 GreorainCalendar버전을 지원 안 할 수 있다. 그러므로 getInstance를 쓰면 버전 호환성이 좋으므로 getInstance를 쓰는게 좋다. → 결합도가 낮은 코드

```java
package abstractfinal;
class A{
	final void a() {}
	
}
~~public~~ final class B extends A{ //public으로 선언하면 컴파일 에러!! 여러 클래스가 한 자바 파일에 저장 될 때에는 main메소드를 갖는 클래스가 public으로 선언 되어야한다.
	//void a() {} // A메서드가 부모쪽에 final로 선언 되어있으므로 X
	final int mf;
	static final String msg = "B"; 
	B(){
		mf = 123; // 생성자를 통한 최초로 명시된 초기화는 가능
	}
	void b() {
		//mf++; // 생성자를 통해서 최초로 초기화는 가능한데, 일반 메서드를 통해서 값을 변경하려면 에러!! final로 선언한 변수 변경 금지!!
		System.out.println(B.msg); // static이므로 클래스. 가능
		System.out.println(msg);
		//msg = "다른값"; // final로 선언한 변수는 다른 값으로 변경 할 수가 없다.
	}
}
//class C extends B{} // B클래스는 final클래스이므로 하위클래스로 확장x
public class finalTest {
	public static void main(String[] args) {
		
	}

}
```

### **부모 생성자 호출**

자식 객체를 생성하면, 부모 객체가 먼저 생성되고 자식 객체가 그 다음에 생성된다.

super()는 부모의 기본 생성자를 호출한다.

부모 클래스에는 하는 게 없어도 매개변수 없는 생성자를 만들어 놓는게 좋다. 누구를 위해? 자식을 위해서

### **메소드 재정의**

부모 클래스의 모든 메소드가 자식 클래스에게 맞게 설계 되어 있다면 가장 이상적인 상속이지만, 어떤 메소드는 자식 클래스가 사용하기에 적합하지 않을 수도 있다. 이 경우 상속된 일부 메소드는 자식 클래스에서 다시 수정해서 사용해야 한다. → 메소드 오버라이딩 기능을 제공

**메소드를 오버라이딩할 때 규칙**

- 부모의 메소드와 동일한 시그너처(리턴 타입, 메소드 이름, 매개변수 리스트)를 가져야 한다.
- 접근 제한을 더 강하게 오버라이딩 할 수 없다. → 자식 메소드의 접근범위가 더 크거나 같아야 한다.
- 새로운 예외를 throws할 수 없다.

>> 다형성을 지원하는 개념이 오버라이딩이라 보면 된다.

### **부모 메소드 호출**

자식 클래스 내부에서 오버라이딩된 부모 클래스의 메소드를 호출해야 하는 상황이 발생한다면 명시적으로 super키워드를 붙여서 부모 메소드를 호출할 수 있다.

### **final클래스와 final 메소드**

final 키워드는 클래스, 필드, 메소드 선언 시에 사용할 수 있다. final 키워드는 해당 선언이 최종 상태이고, 결코 수정될 수 없음을 뜻한다.

- **상속할 수 없는 final 클래스**

    final 클래스는 부모 클래스가 될 수 없어 자식 클래스를 만들 수 없다는 것이다.

- **오버라이딩할 수 없는 fianl 메소드**

    부모 클래스를 상속해서 자기 클래스를 선언할 때 부모 클래스에 선언된 final 메소드는 자식 클래스에서 재정의할 수 없다는 것이다.

static은 지역변수 앞에  못 붙인다.

### **protected 접근 제한자**

protected는 public과 default 접근 제한의 중간쯤에 해당한다. 동일 패키지에서는 default와 같이 접근 제한이 없지만 다른 패키지에서는 자식 클래스만 접근을 허용한다.

### **타입 변환과 다형성**

다형성은 같은 타입이지만 실행 결과가 다양한 객체를 이용할 수 있는 성질을 말한다.

다형성은 하나의 타입에 여러 객체를 대입함으로써 다양한 기능을 이용할 수 있도록 해준다.

다형성을 위해 자바는 부모 클래스로 타입 변환을 허용한다. 즉 부모 타입에 모든 자식 객체가 대입될 수 있다.

클래스 타입의 변환은 상속 관계에 있는 클래스 사이에서 발생한다.

**강제타입변환(Casting)**

강제 타입 변환은 부모 타입을 자식 타입으로 변환하는 것을 말한다. 

모든 부모 타입을 자식 클래스 타입으로 강제 변환할 수 있는 것은 아니다.

자식 타입이 부모 타입을 자동 변환한 후, 다시 자식 타입으로 변환할 때 강제 타입 변환을 사용할 수 있다.

```java
Parent p1 = new Parent(); // p1참조하는 객체는 parent

Child c1 =(Child)p1; //downcasting 컴파일 성공할 수 있지만 Parent안에 자식이 없다→실행 시 ClassCastException발생! 객체 타입을 확인 하여한다.
```

**객체타입확인**

강제 타입 변환은 자식 타입이 부모 타입으로 변환되어 있는 상태에서만 가능하기 때문에 다음과 같이 부모 타입의 변수가 부모 객체를 참조할 경우 자식 타입으로 변환x

```java
Child c = (Child)new Parent //실행 시 ClassCastException발생! 객체 타입을 확인 하여한다.

-----------------------------------------------------------------------------------------
Parent p = new Child(); // 자식 타입이 부모 타입으로 변환(upcasting)
Child c1 = (Child)p; // 자식 타입이 부모 타입으로 변환되어 있는 상태이므로 강제타입변환 가능(downcasting)
```

부모 변수가 참조하는 객체가 부모 객체인지 자식 객체인지 확인하는 방법은? 어떤 객체가 어떤 클래스의 인스턴스인지 확인하려면 **instanceof** 연산자를 사용→instanceof연산자의 좌항은 객체가 오고 우항은 타입이 온다.  즉 우항의 타입으로 객체가 생성 되었다면 true를 산출 그렇지 않으면 flase를 산출

boolean result = 좌항(객체) instanceof 우항(타입)