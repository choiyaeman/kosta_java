# day08

```java
**class A{
	int mf; // 메인 메서드 호출 다음에 객체생성시 멤버필드는 자동 초기화, HEAP영역에. 해당 객체가 소멸되면 그때 메모리에서 소멸
	static int smf; // 가장 초기 단계에 클래스 로드시 자동 초기화, CLASS AREA, 가장 나중에 프로세스가 종료될때 JVM메모리에서 없어지게 된다.
	void method1() { //non-static method <= 객체가 생섣된 후에만 
		System.out.println("mehtod1()입니다");
		System.out.println(mf); //메서드 안쪽 즉 이미 객체가 생성된 상태 객체의 안쪽에서는 this예약어 사용 가능. 메인 메서드쪽 객체 바깥부분은 참조변수.mf변수를 사용  객체 안쪽에 들어온 경우가 아니다. 
		                        //a1이 참조하는 객체하고 this객체는 같다
		this.method2(); //객체의 또 다른 메서드 호출 가능. this생략가능
		
		// non-static 이미 객체 생성상태이므로 다 호출 할 수 있다.
		System.out.println(smf);
		smethod2();
	}
	void method2() {
		
	}
	static void smethod1() {
		System.out.println("smethod1()입니다");
		//System.out.println(this.mf); // this.mf컴파일 에러 static 메서드는 객체생성과 무관하게 호출될 수 있다. 객체생성x 충분히 호출될수 있기 때문에 static안쪽에서는 this예약어를 쓸 수 없다.
		//System.out.println(mf); //X non-static필드 사용불가
		//method2(); //X non-static메서드 사용불가
		System.out.println(smf); //static메서드에서는 non-static필드, non-staitc메서드, this사용 불가 .스태틱 필드, 스태틱 메서드만 사용가능하다.
		System.out.println(A.smf); //같은 클래스 안에서는 클래스 생략가능하다.
		smethod2();
		A.smethod2();
	}
	static void smethod2() {
		
	}
}
public class StaticTest {

	public static void main(String[] args) {
		//System.out.println(A.mf); //X, Class이름.으로 사용 불가
		System.out.println(A.smf); //스태틱 필드는 이미 자동 초기화되어 있는 상태
		
		A a1 = new A();
		//a1이 참조하고 있는 객체 heap영역의 객체 안에는 멤버필드라 불리는 mf변수가 들어있고 초기값은 0값으로 초기화 되어 있는 것 을 볼 수 있다.
		//method1(), method2()선언부만 객체가 가지고 있다.
		System.out.println(a1.mf);
		System.out.println(a1.smf); //참조하고 있는 객체의 static필드 mf변수는 있지만 smf스테틱 필드는 없다. A클래스 타입으로 객체만들어졌으니까 A클래스  smf변수값을 찾아간다.
		                            //warning!! 스태틱 방법을 사용해라 => class이름.
		a1.mf++;
		a1.smf++;
		
		A a2 = new A();
		System.out.println(a2.mf);  //0  객체가 따로 생성되어 각각 다르므로 0이 출력
		System.out.println(a2.smf); //a2가 참조하고 있는 객체에smf 스택틱 변수가 없다. static필드는 없고 A클래스의 smf변수의 값은 a1에 사용했던 a2사용했던 smf나 같은 메모리되므로 1이증가. 
		System.out.println(A.smf);	//static변수는 객체 사이의 공유 변수가 된다
		
		//A.method1(); //X, static아닌 메서드를 호출할때도 참조변수.을 사용해야한다.
		a2.method1(); //객체 바깥부분은 참조변수.mf변수를 사용. 객체 안쪽에 들어온 경우가 아니다 
		              //a2객체는 this가 되는 것 이다. this는 현재 사용중인 객체를 의미
		
		A.smethod1();
		a1.smethod1();
		
		double radius = 5.2;
		double area = Math.pow(radius, 2) * Math.PI; //대표적 라이브러리 Math.pow => 제곱
		
		//직각삼각형 빗변길이계산
		double width = 3;
		double height = 4;
		double hypo; //빗변 hypo^2 = width^2 + height^2
		hypo = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2)); //빗변의 길이
		System.out.println(hypo);
		
		//Math.PI = 1.22; //X, final변수 이기 때문에 값 변경 불가
		
	}

}**
```

![day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled.png](day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled.png)

final로 선언한 변수는 값 변경 불가

---

src오른쪽클릭>package>이름지정 → 패키지별로 카테고리를 만든다

각자의 패키지를 구분해서 관리하면 클래스의 중복을 피할 수 있다.

 default 패키지: 패키지가 선언되어있지 않은 상태

java.util, java.sql패키지에 둘 다 Date가 있는데 서로 다른 Date이다.

![day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%201.png](day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%201.png)

![day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%202.png](day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%202.png)

## **접근제어자**

private : 클래스 내부에서 접근 가능

default/package : 같은 패키지 내부에서 접근 가능 

protected: 같은패키지 및 상속받은 자식 클래스에서 접근 가능

public : 누구나 접근 가능

```java
**import java.util.Scanner;

import world.asia.Japen;
//import world.asia.*; // 자바는 동적 바인딩으로 실행시 자원이결정. *쓴다고해서 미리 다 사용하겠다고 준비하는게 아니라 필요한 것을 world.asia패키지를 찾고...속도에 영향x 단 가독성 떨어진다.
import world.asia.Korea;
import world.europe.France;// ctrl+shift+o: 필요한 임포트 구문 생성 단축키

public class CountryTest {

	public static void main(String[] args) {
//		world.asia.Korea k; // world.asia.패키지에 있는 Korea의 k변수. k변수는 메서드 내에서 선언된 지역변수
//		k = new world.asia.Korea(); // new를 만나면 heap메모리를 할당하고 Korea타입으로 채워라
//		
//		world.asia.Japen j = new world.asia.Japen();
//		
//		world.europe.France f = new world.europe.France();
		
		Korea k = new Korea();
		Japen j = new Japen();
		France f = new France(); 
		
		// 적절치 않는데 값들이 대입되고 있다. 제공자쪽에서 제어를 안해주면 사용자는 자유롭게 쓸 수 있다.
//		k.population = 0; //한국의 인구
		k.setPopulation(-100);//setPopulation메서드는 사용가능
		int koreaPopulation = k.getPopulation();
		System.out.println("현재 한국의 인구는"+koreaPopulation+"명");
		//k.language = "일본어"; //한국의 언어
		k.capital = "평양"; //한국의 수도
		
		Scanner sc = new Scanner(System.in);
		
	}

}**
```

```java
package world.asia;

public class Korea {
	//public int population; //인구 //public -> 누구든지 쓸 수 있다.
	private int population; //인구 //private -> 가장 접근 범위가 좁은곳. 해당 클래스에서만 쓸 수 있다
	String language; //언어 //default상태 또는 package상태. 같은 패키지에서만 접근할 수 있는 범위, 즉 world.asia 패키지에서만 접근 가능
	public String capital; //수도 //public -> 누구나 접근 가능
	void k() {
		population = 10; //다른 클래스에서 접근할수 있도록 통로를 마련
		language = "한국어";
		capital = "서울";
	}
	public void setPopulation(int population) { // 직접 접근x 우회적으로접근. 올바른 값을 대입할때만 값을 바꾸는작업
		if(population <=0) {
			System.out.println("인구는 1명 이상이어야 합니다.");
			return;
		}
		this.population = population;//매개변수의 값이 현재 population에 대입
	}
	public int getPopulation() { // 직접 접근x 우회적으로접근.
		return population;
	}
	}
```

```java
package world.asia;

public class Japen {
	void k() {
		Korea korea = new Korea();
		//korea.population = 10; //private이므로 korea에서만 접근가능
		korea.language = "한국어"; //같은 패키지이므로 접근가능
		korea.capital = "서울";
	}
}
```

```java
package world.europe;

import world.asia.Korea;

public class France {
	void k() {
		Korea korea = new Korea();
		//korea.population = 10;
		//korea.language = "한국어";
		korea.capital = "서울";
	}
}
```

![day08%20c1a979cccb1c41f59faa9b75984fcd63/20201217_114132.jpg](day08%20c1a979cccb1c41f59faa9b75984fcd63/20201217_114132.jpg)

통로를 통해서만 population변수를 선언 외부에서 직접 접근x, 대신 메서드를 통해서 접근을 하면 된다. 대표 메서드 set메서드와 get메서드를 이용. capital 변수는 객체의 바깥쪽에 있어 직접 접근해서 쓸 수 있다. 은닉은 자료를 외부에 노출되지 않게 가리는 것을 말한다. 은닉된 정보를 사용해야 하는데 그 방법을 제공하는 것을 캡슐화라 한다.

캡슐화를 왜 쓰는가? 나 혼자 처음부터 개발해서 끝까지 개발 유지보수를 관리한다면 필요 없지만, 내가 개발한 클래스를 여러 사람이 사용하는 경우가 있다. 중요한 데이터는 노출하지 않고 가려서 써야 하므로 필요하다.

default상태와 protected는 안 쓰는 걸 추천. private, pubilc 둘 중에 선언해서 쓰면 좋다.

---

![day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%203.png](day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%203.png)

![day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%204.png](day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%204.png)

vo 패키지 : ValueObject용(값이 저장될 객체) 클래스를 저장
dao패키지: DataAccessObject용(값이 처리할 객체) 클래스를 저장 ⇒CRUD(생성, 수정, 조회, 삭제)

![day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%205.png](day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%205.png)

![day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%206.png](day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%206.png)

![day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%207.png](day08%20c1a979cccb1c41f59faa9b75984fcd63/Untitled%207.png)

오른쪽 클릭> Source> 1.Generate Getters and Setters

                                     2.Generate Constructer from Superclass

                                     3.Generate Constructer using Fields

```java
package com.my.vo;

public class Customer {
	private String id; // 멤버필드는 동일 패키지에서 접근이 되므로 private으로 접근 안되게
	private String pwd;
	private String name;
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}

	/*
	 * Customer c = new Customer("id1", "p1", "최예만"); 
	 * String id = c.getid();
	 * sop(id); //id1
	 */	
	public Customer(String id,String pw, String name) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
```

```java
package com.my.dao;

import java.util.Scanner;

import com.my.vo.Customer;

public class CustomerDAO {
	private Customer[] customers; //customer는 맴버필드이며  위치상 멤버필드는 자동 초기화된다.자료형인 참조형일때 null값으로 자동초기화.
	private int cnt;
//	// 이렇게 호출 될 수 있도록 제공해라 DAO에 생성자를 만들어서 10개짜리방이나 5개짜리 방.. 생성자의 매개변수가지고 조절하면 된다.
//	new CustomerDAO(5); //최대 5명까지 저장 가능한 customers생성
//	new CustomerDAO(10); //최대 10명까지 저장 가능한 customers생성

	public CustomerDAO(int size) {
		customers = new Customer[size];
	}
	
	public Customer[] selectAll(){
		Customer[] a = new Customer[cnt];
		for(int i=0; i<cnt; i++) {
			a[i] = customers[i];
		}
		return a;
	}
	public void insert(Customer c){
		customers[cnt] = c;
		cnt ++;
	}
	public Customer selectById(String id) {
		Customer[] all = this.selectAll();
		for(int i=0; i<all.length; i++) {
			if(all[i].getId().equals(id)) {
				return all[i];
			}
		}
		return null;
		
	}
	
}
```

```java
import com.my.dao.CustomerDAO;
import com.my.vo.Customer;

public class CustomerMain {

	public static void main(String[] args) {
		CustomerDAO dao = new CustomerDAO(3); //최대 3명이 저장될 수 있도록하라
		
		System.out.println(">>1. 고객 전체 조회<<");
		Customer[] cAll = dao.selectAll(); //전체조회 메서드 selectAll
		if(cAll != null) {
			for(int i=0; i<cAll.length; i++) {
				Customer c = cAll[i];
				System.out.println(c.getId() + "," + c.getPwd() + ", " + c.getName());
			}
		}
		System.out.println(">>2. 고객추가<<");
		dao.insert(new Customer("id1", "p1", "n1")); //고객을 추가할때 사용하는 메서드
		dao.insert(new Customer("id2", "p2", "n2"));
		
		System.out.println(">>3. 고객 전체 조회<<");
		Customer[] cAll3 = dao.selectAll();
		for(int i=0; i<cAll3.length; i++) {
			Customer c = cAll3[i];
			System.out.println(c.getId() + "," + c.getPwd() + ", " + c.getName());
		}
		
		System.out.println(">>4. 고객 ID로 조회<<");
		String id1 = "id2";
		Customer c4 = dao.selectById(id1); //id값으로 고객정보를 조회
		if(c4 != null) { //이곳이 출력
			System.out.println(c4.getId() + "," + c4.getPwd() + ", " + c4.getName());
		}else {
			System.out.println("ID가 존재하지 않습니다");
		}
		
		System.out.println(">>5. 고객 ID로 조회<<");
		String id = "id9";
		Customer c5 = dao.selectById(id);
		if(c5 != null) {
			System.out.println(c5.getId() + "," + c5.getPwd() + ", " + c5.getName());
		}else {//이곳이 출력
			System.out.println("ID가 존재하지 않습니다"); 
		}		
	}
}
```

hint : 문자열 비교 연산 방법 equals()메서드 활용!
String s1, s2;
s1 = "최예만", s2="최예만";
boolean b = s1.equals(s2)
sop(b); //true

---

자습>

```java
package yaeman;

import jb.Korea;
import practice.Customer;
import practice.CustomerDAO;

public class Child {
	public static void main(String[] args) throws Exception{
		CustomerDAO dao = new CustomerDAO(10);
		Customer aa = new Customer("goldlife","pass","yaeman");
		System.out.println("주소값 첫번째"+aa);
		dao.insert(aa);//Custoemr 인스턴스를 생성자 호출하는데 String타입의 3개짜리를 호출하겠다. 그러면 Customer인스턴스 하나가 생겼다. 이 인자를 insert메서드로 전달
		dao.insert(new Customer("silverlife","pass","yaeman"));
		dao.insert(new Customer("bronzelife","pass","yaeman"));
		System.out.println(dao.selectAll());
		System.out.println("주소값 두번째"+dao.selectAll()[0]);
		System.out.println(dao.selectById("silverlife"));
	}
```

```java
package practice;

public class CustomerDAO {
	private Customer[] customers; // customer는 맴버필드이며 위치상 멤버필드는 자동 초기화된다.자료형인 참조형일때 null값으로 자동초기화.
	private int cnt;
//	// 이렇게 호출 될 수 있도록 제공해라 DAO에 생성자를 만들어서 10개짜리방이나 5개짜리 방.. 생성자의 매개변수가지고 조절하면 된다.
//	new CustomerDAO(5); //최대 5명까지 저장 가능한 customers생성
//	new CustomerDAO(10); //최대 10명까지 저장 가능한 customers생성

	public CustomerDAO(int size) {
		this.customers = new Customer[size];//customer가 들어갈 길이가 size개인 배열 생성 customers라는 멤버변수에 넣겠다
	}

	public Customer[] selectAll() {// Customer[] 타입을 리턴하는 인자가 없는 selectAll함수
		
		Customer[] a = new Customer[cnt]; //길이가 cnt인 Customeer배열 생성 커스텀 배열 타입 a변수에 넣겟다
		for (int i = 0; i < cnt; i++) {
			a[i] = customers[i];
			//a[0] = customers[0]
		}
		return a; //함수 호출한사람한테 돌려주겠다
	}

	public void insert(Customer c) {// 접근 제어자가 퍼블릭인 리턴타입이 없는 insert메소드를 호출하는데 Customer타입의 인자 하나를 주겠다
					// void 타입을 리턴하는 인자가 Customer 인스턴스인 insert함수// c는 new Customer("goldlife1","pass","yaeman")
		System.out.println(c.getId());
		customers[cnt] = c;
		cnt++;
	}

	// select * from customers_table where id = 'goldlife'
	public Customer selectById(String id) { //Customer타입을 리턴하는 인자가 String 인스턴스변수인 selectbyid함수
		Customer[] all = this.selectAll();
		System.out.println("주소값 세번째"+all[0]);
		for (int i = 0; i < all.length; i++) {
			if (all[i].getId().equals(id)) {
				return all[i];
			}
		}
		return null;

	}

}
```

CustomerDAO 인스턴스를 생성자 호출하는데 int타입의 인자가 10개 짜리 CustomerDAO를 호출하갰다.
CustomerDAO 인스턴스 dao 변수에 대입하겠다
CustomerDAO인스턴스에 인자10이 size에 대입되고
Customer가 들어갈 길이가 size개인 배열 생성 customers라는 멤버변수에 넣겠다.
Customer인스턴스를 생성자 호출하는데 String타입의 3개 짜리를 호출하겠다.
그러면 Customer인스턴스 하나가 생겼다. 이 인자를 insert메서드로 전달
void 타입을 리턴하는 인자가 Customer 인스턴스인 insert함수
여기서 c는 Customer인스턴스인 "goldlife", "pass", "yaeman"을 뜻한다.
이런 c를 cnt는 0이므로 customers[0]에 대입하고 cnt++를 만나 cnt:0->1이 된다.
Customer인스턴스 변수를 inset메서드로 전달
void 타입을 리턴하는 인자가 Customer 인스턴스인 insert함수로 가고
silverlife,~ customers[1]에 대입 cnt 1->2
또 다시 Customer인스턴스를 생성자 호출 String타입의 3개짜리를 호출하고
void 타입을 리턴하는 인자가 Customer인스턴스인 insert함수
brozelife,~ customers[2]에 대입하고 cnt 2->3
다시 Customer클래스로 넘어가서 dao인스턴인 selectAll()메서드를 호출하고
Customer[] 배열 타입을 리턴하는 인자가 없는 selectAll 함수
길이가 cnt인 Customer배열 생성 Customer배열 타입 a변수에 넣겠다.
i는 0부터 cnt개수까지 1씩 증가하는 for문을 돌린다.
a[0] = customers[0] 여기서 customers[0]은 goldlife ~ customers[1]은 silverlife~ customers[2]는 bronzlife~
for문을 다 돌리면 return 하여 함수 호출한 사람한테 돌려주겠다
dao인스턴스인 selectbyid 함수 호출
Customer타입을 리턴하는 인자가 String인스턴스 변수인 selectByid 함수정의
현재 사용중인 selctAll을 Customer[]배열 타입 all에 대입
i=0부터 3미만까지 1씩증가 for문 돌린다
all[0]은 goldlife,pass,yaeman 즉 goldlife,pass,yaeman에 참조하는 id를 받겠다 => goldlife goldlife와 id비교(여기서 id는 silverlife)
다르므로 다시 올라가서 i++하므로 all[1]이며 id와 비교해보면 같다 true이므로 all[1]을 반환
false인경우 null로 반환