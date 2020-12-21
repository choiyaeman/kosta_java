# day10

### **UML : 객체지향 모델링 언어**

        **CD, UD, SD**

![day10%209581ea6cd9d946b2ae10e98972fe6e68/20201221_102753.jpg](day10%209581ea6cd9d946b2ae10e98972fe6e68/20201221_102753.jpg)

**Circle**

-radius:double

-area:double 

+makeArea():void 

+display():void

+setRadius(double):void

+getRadius():double

-는 private을 의미하는 표기법

+는 public을 의미하는 표기법

:는 자료형 double타입으로 선언하라는 의미

**사용자쪽에서**

Circle c = new Circle();

c.radius = 5.0; //(X) private으로 선언 했으므로 접근 불가 → 방법 1)private→public 2)메소드로 접근

c.setRadius(-5.0);

```java
public class ShapeTest {

	public static void main(String[] args) {
		Circle c1 = new Circle();
		c1.setRadius(5.0); //반지름 설정
		c1.makeArea(); //면적 계산
		c1.display(); //결과값 출력

		Rectangle r1 = new Rectangle(3, 4); //가로3, 세로4인 사각형 객체를 생성한다.
		r1.makeArea(); //면적 계산한다
		r1.display(); //결과를 출력한다 가로3.0, 세로4.0인 사각형의 넓이 출력
		
		Triangle t1 = new Triangle(2, 3); //밑변2, 높이가 3인 이등변 삼각형의 객체를 생성
		t1.makeArea();
		t1.display();
	}
}
```

```java
public class Circle {
	private double radius; //반지름
	private double area; //면적
	
//	public Circle() {
//		
//	}
//	public Circle(double radius) {
//		this.radius = radius;
//	}
	
	public void makeArea() {
		this.area = Math.pow(radius, 2) * Math.PI; //파이*반지름제곱
	}
	public void display() {
		System.out.println("반지름이"+radius+"인 원의 면적은"+area+"입니다.");
	}
	public double getRadius() {//
		return radius;
	}
	public void setRadius(double radius) {// 누구나 접근할수 있도록 return 타입이 없다 ->void 그리고 double타입의 매개변수
		if(radius<=0) {
			System.out.println("반지름은 0보다 커야합니다.");
			return; //아래 this.radius를 안하도록 return
		}
		this.radius = radius; 
	}
}
```

```java
public class Rectangle {
	private double width; //가로
	private double height; //세로
	private double area; //면적
	
	public Rectangle(double widht, double height) {//double 타입의 매개변수 선언
		this.width = widht;
		this.height = height;
	}
	
	public void makeArea() {
		this.area = width*height;
	}
	public void display() {
		System.out.println("가로"+width+", 세로"+height+"인 사각형의 면적은"+area);
	}
	
}
```

```java
public class Triangle {
	private double base; //밑변
	private double height; //높이
	private double area; //면적
	
	public Triangle() { //매개변수없는 생성자
		
	}
	public Triangle(int base, int height) {
		setBase(base);
		this.height = height;
	}
	public void setBase(double base) {
		if(base<=0) {
			System.out.println("밑변이 0이상 이어야 합니다.");
			return;
		}
		this.base = base;
	}
	
	public void makeArea() {
		this.area = base*height/2;
	}
	public void display() {
		System.out.println("밑면"+base+" 높이가"+height+"인 이등변 삼각형의 넓이는"+area);
	}
}
```

---

![day10%209581ea6cd9d946b2ae10e98972fe6e68/20201221_110629.jpg](day10%209581ea6cd9d946b2ae10e98972fe6e68/20201221_110629.jpg)

![day10%209581ea6cd9d946b2ae10e98972fe6e68/20201221_112435.jpg](day10%209581ea6cd9d946b2ae10e98972fe6e68/20201221_112435.jpg)

class Circle extends Shape{}

Circe c = new Circle();

Circle타입의 객체는 부모 영역이 먼저 할당이 된다. 부모 쪽인 shape에서 제공되는 shape영역이 먼저 할당되고 area변수를 갖게 된다. area변수는 부모인 shape이 갖고 있다.

Circle, Rectangle, Triangle는 모두 Shape의 자식 관계이다. 즉 형제 관계를Sibling관계라 한다.  Parent, Super | Child, Sub

부모의 private변수에는 자식이 접근 할 수 없다.

부모 영역부터 먼저 초기화되고 그 다음 자식 영역이..

생성자 호출 관계도 부모가 먼저

Shape s; s= c; → c 변수는 Circle타입의 변수 s변수는 Shape타입의 변수. c변수 값을 그대로 s변수에다가 복사 붙여 넣기 s변수에 참조하는 변수가 Circle영역에 찾아간다. c가 참조하는 객체나 s가 참조하는 객체는 메모리 정보가 똑같으나

c변수는 자료형도 Circle참조도 Circle Circle전체 영역을 참조

s변수는 자료형은 Shape를 참조하며 일단 객체를 찾아 가니깐 s와c는 실제 참조하는 자료형이 불일치. 객체의 내용 안쪽을 들여다봐서 shape타입 영역이 있나 보는 거다. s변수는 Shape영역만 참조

s=c; →자식 타입의 객체를 부모 타입을 참조하는 것을 upcasting이라 부른다.

```java
package inheritance;

public class Shape { //부모클래스. 공통점만 갖고 있어야한다
	private double area;
	public void setArea(double area) { //설정 용도이기 때문에 매개변수가 필요
		this.area = area;
	}
	public double getArea() { //반환하는 용도이기  때문에 return필요
		return area;
	}
}
```

```java
package inheritance;

public class Circle extends Shape{ //Shape으로부터 상속받는 하위 클래스
	private double radius; //반지름
	
	public Circle() {
		
	}
	public Circle(double radius) {
		this.radius = radius;
	}
	
	public void makeArea() {
		//this.area = Math.pow(radius, 2) * Math.PI; //파이*반지름제곱
		setArea(Math.pow(radius, 2) * Math.PI);
	}
	public void display() {
		System.out.println("반지름이"+radius+"인 원의 면적은"+getArea()+"입니다.");
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {// 누구나 접근할수 있도록 return 타입이 없다 ->void 그리고 double타입의 매개변수
		if(radius<=0) {
			System.out.println("반지름은 0보다 커야합니다.");
			return; //아래 this.radius를 안하도록 return
		}
		this.radius = radius; 
	}
}
```

```java
package inheritance;

public class Rectangle extends Shape{
	private double width; //가로
	private double height; //세로
	
	public Rectangle(double widht, double height) {//double 타입의 매개변수 선언
		this.width = widht;
		this.height = height;
	}
	
	public void makeArea() {
		//this.area = width*height;
		setArea(width*height);
	}
	public void display() {
		System.out.println("가로"+width+", 세로"+height+"인 사각형의 면적은"+getArea());
	}
	
}
```

```java
package inheritance;

public class Triangle extends Shape{
	private double base; //밑변
	private double height; //높이
	
	public Triangle() {
		
	}
	public Triangle(int base, int height) {
		setBase(base);
		this.height = height;
	}
	public void setBase(double base) {
		if(base<=0) {
			System.out.println("밑변이 0이상 이어야 합니다.");
			return;
		}
		this.base = base;
	}
	
	public void makeArea() {
		//this.area = base*height/2;
		setArea(base*height/2);
	}
	public void display() {
		System.out.println("밑면"+base+" 높이가"+height+"인 이등변 삼각형의 넓이는"+getArea());
	}
}
```

---

![day10%209581ea6cd9d946b2ae10e98972fe6e68/20201221_115159.jpg](day10%209581ea6cd9d946b2ae10e98972fe6e68/20201221_115159.jpg)

자료형은 Circle c변수의 값이 c변수를 test메소드인 매개변수로 대입 s변수는 s변수가 참조하고 있는 Circle타입 객체 객체를 찾아가 봤더니 전체는 참조하지 못하고 그 안에 들어있는 shape영역만 참조. test메소드 한개 가지고서 Circle타입의 객체가 갖는 영역에 shape부분 참조, Rectangle타입의 객체가 갖는 영역에 shape부분 참조.. 여려 형태로 처리 가능 ⇒ 다형성 

부모 타입으로 올라가는 것을 추상화(upcasting) 자식 타입으로 내려가는 것을 구체화(downcasting)

```java
public class ShapeTest {

	public static void main(String[] args) {
		Circle c = new Circle();
		c.setRadius(5);
		c.makeArea();
		c.display();
		
		Rectangle r = new Rectangle(3,4);
		r.makeArea();
		r.display();
		
		Triangle t = new Triangle(2, 3);
		t.makeArea();
		t.display();
		
		Shape[] sArr = new Shape[3];
		sArr[0] = c; // 자식 타입의 객체인 c가 부모 타입인 shape타입으로 upcasting이 되서 0번방 Circle전체가 아니라 안에 있는 Shape영역을 참조
		sArr[1] = r;
		sArr[2] = t;
		
		//for(int i=0; i<sArr.length; i++) {
			//sArr[0].makeArea(); //(X) sArr[0] shape클래스는 makeArea()메서드가 없기 때문에 호출은 불가능. 부모가 지원하는 메서드만 호출 가능	
			//System.out.println(sArr[i].getArea()); //(O)
		//}
		 for(Shape s: sArr) { //향상된for문
			 System.out.println(s.getArea());
		 }
		
	}

}
```

---

```java
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
```

**this는 현재 사용 중인 객체를 의미**,  **super**는 현재 사용 중인 객체의 부모 영역을 의미.

---

- 부모 클래스를 상위 클래스라고 부르기도 하고, 자식 클래스를 하위 클래스, 또는 파생 클래스라고 부른다.
- 자바는 단일 상속만 가능하다(부모는 하나 이다)
- extends
- super()는 부모의 기본 생성자를 호출
- 다형성은 같은 타입이지만 실행 결과가 다양한 객체를 이용할 수 있는 성질
- 다형성을 위해 자바는 부모 클래스로 타입 변환을 허용. 즉 부모 타입에 모든 자식 객체가 대입 될 수 있다.
- 자동 타입 변환은 프로그램 실행 도중에 자동적으로 타입 변환이 일어나는 것을 말한다.(upcasting)
- 부모 자식관계 에서 메소드 이름, 매개변수, 리턴 타입 같은 것을 오버 라이딩이라한다.

![day10%209581ea6cd9d946b2ae10e98972fe6e68/20201221_145255.jpg](day10%209581ea6cd9d946b2ae10e98972fe6e68/20201221_145255.jpg)

부모쪽에 메소드2가 먼저 만들어지고 자식 쪽 에서 메소드2가 또 만들어진다. 이런 걸 메소드 재정의를 말한다. 둘 중에 하나만 쓸 수 있도록 미리 만들었던 메소드를 새로만든 메소드로 덮어쓴다. 부모쪽 메소드가 먼저 메모리에 탑재 그리고 자식 메소드가 다시 재정의를 의미.

child는 parent같은 메모리를 참조하러 가긴 하는데 Parent는 전체 영역을 다 참조하지 못하고 Parent영역만 참조한다. child.method2()가 호출되면 참조 하러 가는데 두개가 있다. 그러나 사용한 가능한 메소드는 child의 메소드2를 호출한다. parent.method2() 호출하러 가면 두 개의 메소드가 있는데 실제 메모리 2는 가려져 있고 child쪽에서 덮어 썼으므로 child의 메소드2가 호출된다. parent.method3()호출하러 가는데 parent에 없으니까 x

```java
public class UpcastingTest {

	public static void main(String[] args) {
		Circle c = new Circle();
		c.setRadius(5.0);
		Shape s; //shape타입의 s변수 선언
		s = new Circle();
		s = c; //upcasting
		s.makeArea(); //upcasting  Shape메모리에 있는 메소드만 호출 가능. Shape에 메소드를 만들어주자
					  // 부모는 가려진 상태고 사용할 수 있는 메소드는 Circle메소드이므로 Circle메소드를 호출하게 된다.
		c.display();
	}

}
```

```java
public class Shape { //부모클래스. 공통점만 갖고 있어야한다
	private double area;
	public void setArea(double area) { //설정 용도이기 때문에 매개변수가 필요
		this.area = area;
	}
	public double getArea() { //반환하는 용도이기  때문에 return필요
		return area;
	}
	public void makeArea() { //부모쪽에는 있으나 내용 채울게 없다

	}
}
```

Circle타입의 객체는 부모 타입의 영역을 갖고 있다 area는 0.0값을 가지고 있다. 부모쪽 영역에는 setArea, getArea, makeArea메소드들이 있다. 자식쪽에도 radius, makeArea(),setRadius(),getRadius()메소드가 있다. 인자가 5.0이 전달되고 setRadius메소드를 통해서 5.0이되고 c변수값 s변조 참조하는 값은 가지만 서로 자료형이 다르다. c는 Circle전체, s는 Circle전체가 아닌 Shape영역만 참조 하므로 s.makeArea()는 부모쪽에 makeArea()메소드를 호출 할 것처럼 보이지만 **부모, 자식 메소드가 같은 경우에는 자식 쪽에 선언만 메소드가 우선!** 즉 부모 쪽 메소드가 가려진 상태이므로 Circle에 오버라이딩 된 makeArea()메소드가 호출

eclipse에서 오버라이딩은 줄 번호 앞에 초록색 삼각형은 오버라이딩된 메소드를 의미.

![day10%209581ea6cd9d946b2ae10e98972fe6e68/Untitled.png](day10%209581ea6cd9d946b2ae10e98972fe6e68/Untitled.png)

**자바의 최상위 클래스**

![day10%209581ea6cd9d946b2ae10e98972fe6e68/20201221_154928.jpg](day10%209581ea6cd9d946b2ae10e98972fe6e68/20201221_154928.jpg)

![day10%209581ea6cd9d946b2ae10e98972fe6e68/Untitled%201.png](day10%209581ea6cd9d946b2ae10e98972fe6e68/Untitled%201.png)

화살표의 화살 쪽이 닫혀야 상속이다.

부모 메소드를 자식 쪽에서 또 만든 것을 오버라이딩이라한다.

```java
Object o1, o2, 03;

o1 = new Object();

o2 = new Object()

o3 = 01;

sop(01.equasl(02)); // false
```

```java
Object o1, o2;

obj1 = new Sring("hello");

obj2 = new String("hello");

sop(obj1.equals(obj2)); //string의 equals메소드가 호출된다.
```

Object클래스가 갖고 있는 equals는 ==하고 같다.

---

실습>

![day10%209581ea6cd9d946b2ae10e98972fe6e68/_(1).png](day10%209581ea6cd9d946b2ae10e98972fe6e68/_(1).png)

![day10%209581ea6cd9d946b2ae10e98972fe6e68/Untitled%202.png](day10%209581ea6cd9d946b2ae10e98972fe6e68/Untitled%202.png)

Package Presentation>Hierarchical(계층) 선택

![day10%209581ea6cd9d946b2ae10e98972fe6e68/Untitled%203.png](day10%209581ea6cd9d946b2ae10e98972fe6e68/Untitled%203.png)

```java
package main;

import vo.child.Employee;
import vo.child.TempEmployee;

public class PersonTest {
	public static void main(String[] args) {//cnt++이므로 cnt는 0->1->2->3->4
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
```

```java
package vo;

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
package vo.child;

import vo.Person;

public class Employee extends Person {
	private String no;
	private int salary;
	
	public Employee() {
		super();
		System.out.println("임직원을 한명 생성합니다.");
	}
	
	public Employee(String no) {
		this();
		this.no = "#"+no;
	}
	
	public Employee(String no, String name) {
		this(no); // super 또는 this 생성자 메소드가 먼저
		setName(name); // Person 부모 클래스의 이름을 설정이라봐도 되고 자기이름이라 보면된다. pirvate으로 선언되어있어 this.불가능
	}
	
	public Employee(String no, String name, int salary) {
		this(no,name);//no와name을 매개변수로 하는 이 클래스 생성자 호출. 호출하고 위로 올라가서 똑같이 this로 중복되는 매개변수로하는 이 클래스 생성자 호출하는 방식
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
package vo.child;

public class TempEmployee extends Employee {
	private int time;
	
	public TempEmployee() {
		super();
	}
	
	public TempEmployee(String no) {
		super(no);	//부모 클래스인 Employee에 no가 있으므로 super로 부모 클래스의 no를 받아오겠다.
	}
	
	public TempEmployee(String no, String name) {
		super(no,name);
	}
	
	public TempEmployee(String no, String name, int time) {
		super(no,name);
		this.time = time;
	}
	
	public TempEmployee(String no, String name, int time, int salary) {
		super(no,name,salary);
		this.time = time;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	@Override
	public void calcSalary() {
		System.out.println(
			String.format("%s의 계산된 연봉은 %d 값입니다.", getName(), getSalary()*time));
	}
	
}
```

![day10%209581ea6cd9d946b2ae10e98972fe6e68/Untitled%204.png](day10%209581ea6cd9d946b2ae10e98972fe6e68/Untitled%204.png)

---