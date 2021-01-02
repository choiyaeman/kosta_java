# day07

## **객체지향 언어의 목적**

- 클래스의 재사용성을 높이자
- 클래스사용법을 문서(API Document)로 제공해야한다
    - HTML형태로 제공
    - document용 주석문(/** */)를 클래스선언부, 멤버필드 선언부, 메서드 선언부 사용
    파일 오른쪽 클릭> export> java doc> configure로 찾는다 →C:\Program Files\Java\jdk1.8.0_271\bin에 java.doc클릭> package로 선택> next> next> finish> yes to all
    console창에 오류->utf-8
    - locale ko_KR -encoding UTF-8 -charset UTF-8 -docencoding UTF-8 을 복사해서 아까 위의 경로에서 Vm option에 붙여 넣는다.

![1](https://user-images.githubusercontent.com/63957819/103460155-7415a080-4d57-11eb-94dc-77829ffcfe87.png)

![2](https://user-images.githubusercontent.com/63957819/103460156-74ae3700-4d57-11eb-9dd4-b1792039f87d.png)

![3](https://user-images.githubusercontent.com/63957819/103460157-7546cd80-4d57-11eb-8f3d-8a6fe645da75.png)

![4](https://user-images.githubusercontent.com/63957819/103460158-75df6400-4d57-11eb-8a3f-abbe3b36f97b.png)

![5](https://user-images.githubusercontent.com/63957819/103460159-75df6400-4d57-11eb-8dbd-83fbd898ad9d.png)

    ```java
    package day07;
    /**
     * Document용 주석문
     * 원객체용 클래스입니다.
     * @author 최예만
     * @version 1.0
     */
    class Circle { //Circle쪽에서 메서드를 제공해줘야한다
    	/**
    	 * 반지름
    	 */
    	double radius;
    	/**
    	 * 매개변수 반지름값이 0미만이면 "0이상 이어야합니다."메시지가 콘솔에 출력되고 객체의 멤버필드에 대입되지 않는다.
    	 * @param radius 반지름 //param이란 매개변수를 뜻하고 뒤에 radius은 매개변수 이름이다
    	 */
    	void setRadius(double radius) {
    //		if(r<0) {
    //			System.out.println("0이상 이어야합니다.");
    //			return; //메소드를 끝낸다는 의미
    //		} else {
    //			radius = r;
    //			System.out.println(radius);
    //		}
    		if(radius < 0) {
    			System.out.println("0이상 이어야 합니다.");
    			return;
    		}
    		this.radius = radius; // 객체지향에서는 무조건 자기꺼가 우선이다. 
    							  // 현재사용중인 객체의 radius를 this라히며  앞에 this.멤버필드로 해준다. 
    		                      // 매개변수값은 멤버필드(자기값)에 대입한다는 의도
    		System.out.println(radius);
    	}
    }

    public class ThisTest {

    	public static void main(String[] args) {
    		Circle c1; //참조형 지역변수
    		c1 = new Circle(); //객체생성. (); -> 생성자 호출절차를 말한다.
    		c1.radius = 5.7;
    		c1.setRadius(5.7);
    		c1.setRadius(-1.2);
    		
    		Circle c2 = new Circle();
    		c2.setRadius(2.3);
    	}

    }
    ```

![6](https://user-images.githubusercontent.com/63957819/103460160-7677fa80-4d57-11eb-8e70-c20b82a8aff1.jpg)

    java -cp ThisTest

    **클래스 실행순서**

    1. ThisTest.class파일찾기
    2. JVM로 ThisTest.class파일이 로드됨
    3. ThisTest.class파일 내용을 byte→binary(0,1)로 재해석
    4. class area에 기억
    5. static변수 자동 초기화
    6. main()자동 호출됨 

    → 6번이 호출 되기 전에 static변수는 이미 자동 초기화 되어져 있다.

    **-ClassArea**

    ThisTest.class

    ---

    **-STACK**

    메인메서드 호출이되면서 매개변수,지역변수가 메모리 스택영역에 할당이 된다.

    위로 쌓인다..args→c1→radius→c2→radius

    ~~radius 2.3~~ // 끝나면 소멸

    c2 

    ~~radius 5.7~~    [//](//5.7인자값이) 5.7 인자값이 메소드호출시 메서드변수에 자동대입. 끝나면 소멸

    c1 → Circle참조

    args

    this.radius 지금 사용중인 현재 객체 radius → heap영역에 만들어진 setRadius 

    5.7로 채워라

    this.radius 값이 2.3으로 채워진다

    ---

    **-HEAP**

    new를 만나 heap메모리 공간 할당 Circle클래스로 채울 것이다.

    radius ~~0.0~~ →2.3

    void setRadius(double radius)

    radius ~~0.0~~ → 5.7

    void setRadius(double radius)

    =연산자로 값이 대입이되면 c1변수는 참조하게된다

    c1.setRadius(5.7); 호출(;)5.7인자값을 가지고 호출하러 갑니다.

    c2.setRadius(2.3) 2.3인값을 가지고 호출하러 갑니다

    ---

    객체 생성시 자동 호출이 되는 특수 메서드는 생성자이다.

    생성자의 이름은 클래스의 이름과 같아야한다.

    ```java
    package day07;

    class Employee { // 사원들의 정보가 들어갈 객체용 클래스
    	// 001
    	// 011 ->2001년에 입사한 사원
    	String no; // 사원번호
    	String name; // 사원이름
    	int salary; // 급여
    	Employee() {
    		
    	}
    	Employee(String no, String name) {
    //		this.no = no;
    //		this.name = name;
    		this(no, name, 0); // 몰아가기..매개변수가 두개짜리 생성자에서 인자가 3개짜리가 생성
    	}
    	// 제공자쪽에서 생성자를 제공해주자
    	Employee(String no, String name, int salary) { // 생성자. return타입 자체가없다.
    		this.no = no;
    		this.name = name;
    		this.salary = salary;
    	} 
    }

    class Rectangle {
    	int width; // 멤버필드는 자동초기화 => 묵시적 초기화
    	int height;
    	double area;
    	Rectangle(int size) { // 생성자를 통해서 전달된 인자값이 매개변수에 대입 초기화 => 명시적 초기화
    //		this.width = size;
    //		this.height = size;
    		this(size,size); // 다른 생성자를 호출하라는 뜻 즉 매개변수의 갯수가 다르거나 자료형이 다른 생성자를 말함.인자 2개가 전달 처리해줄 생성자가 호출이된다 매개변수 int타입으로 선언되어있는 생성자 Rectangle(int width, int height)를 호출 => size가 width, size가 height으로
    	}
    	
    	Rectangle(int width, int height) {
    		this.width = width;
    		this.height = height;
    	}
    	void calcArea() {
    		area = this.width*height; //width와 height는 앞에 this.이 생략이 된거다.
    		//double area = this.width*height; // X area앞에 double타입을 쓰면 다른 메서드의 지역변수나 매개변수들은 사용하지 못한다.
    	}
    	void display() {
    		System.out.println("가로:"+width+","+"세로:"+height+"인 사각형 면적은 "+area+"입니다.");
    	}
    }

    public class ConstructorTest {

    	public static void main(String[] args) {
    		Employee[] employees; // employee배열타입의 employees
    		employees = new Employee[10]; // 사원수 10. 배열 각 방(0~9)은 null값으로 자동 초기화 왜냐 배열 자체는 참조형이니깐
    		
    		//Employee e1 = new Employee(); // new키워드를 만나면 heap영역 할당. //위에 제공자쪽에서 매개변수 있는 생성자를 만들면 사용자쪽에는 매개변수가 없는데 쓰려고할때 컴파일에러뜬다. 그러므로 위에 매개변수없는 생성자를 만들어주면 된다.
    		Employee e1 = new Employee("001", "최예만", 100); // 제공자쪽에서 생성자를 제공하게되면 생성자르 골라 쓸 수 있다. 재사용성 높아진다.
    		employees[0] = e1;
    //		Employee e2 = new Employee();
    //		e2.no = "002";
    //		e2.name = "콩쥐";
    //		e2.salary = 200;
    		Employee e2 = new Employee("002", "콩쥐", 200);
    		employees[1] = e2;
    //		Employee e3 = new Employee();
    //		e3.no = "011";
    //		e3.name = "코스타";
    		Employee e3 = new Employee("011", "코스타");
    		employees[2] = e3;
    		
    		//int totalSalary = e1.salary + e2.salary + e3.salary; // 만약 사원수가 100이상이면 복잡해진다. 이럴경우 배열에 관리를하면 편리해진다
    		int totalSalary = 0;
    		//for(int i=0; i<employees.length; i++) {
    		for(int i=0; i<3; i++) {
    			totalSalary += employees[i].salary; // 길이범위가 employees.length미만으로 주면 0~9이므로 i가 3일경우 employees[3]=null이된다. 아무것도 참조하지않은 값을 참조한다? 논리적으로 문제가 있다 그러므로 앞에가 null일경우 NullPointException이 발생..
    		                                        //.연산자 앞에오는 null인지 확인해봐야한다
    		}
    		
    		System.out.println("총급여:"+totalSalary); // 300
    		
    		Rectangle r1 = new Rectangle(3,4); // 가로길이가 3이고 세로길이가 4인 사각형 객체 //위에 생성자를 만들어야한다.
    		r1.calcArea(); // 사각형의 면적을 계산한다. //위에 메소드를 만들어야한다
    		r1.display(); // "가로3, 세로4인 사각형의 면적은 12.0입니다"가 출력된다. //위에 메소드를 만들어야한다
    		
    		Rectangle r2 = new Rectangle(5); // 가로길이 5, 세로길이 5인 사각형객체 즉 정사각형. 위에 매개변수가 한개인 생성자를 만들어주면 된다
    		r2.calcArea();
    		r2.display();

    	}

    }
    ```

    디폴트 생성자는 객체가 생성될 때 사용자가 초깃값을 명시하지 않으면, 컴파일러가 자동으로 제공하는 생성자이다.
    디폴트 생성자는 사용자로부터 인수를 전달받지 않으므로, 매개변수를 가지지 않는다.
    매개변수를 가지지 않으므로 대부분의 디폴트 생성자가 0이나 NULL, 빈 문자열 등으로 초기화를 진행한다.

    **this() 주요 특징**
    1) this()는 생성자 코드에서만 사용할 수 있다 : 생성자가 아닌 일반 메소드에서 this()를 사용할 수 없다.
    2) this()는 동일한 클래스 내의 다른 생성자를 호출할 때 사용한다
    3) this()는 반드시 생성자의 첫 번째 문장에서 사용되어야 한다 : this()의 호출문은 반드시 생성자 코드의 첫 번째 문장이 되어야 한다. 그렇지 않을 경우, 컴파일 오류가 발생하게 된다. 즉 ths 생성자 호출은 생성자의 첫 줄 첫 머리에 와야한다

![7](https://user-images.githubusercontent.com/63957819/103460161-77109100-4d57-11eb-8a7a-04c5e97dbf36.jpg))

    employees 지역변수 stack 영역에 선언이고 Employee 배열 타입이면 참조 할 수 있다.

    new를 만나면 heap영역이 만들어지고 10개 짜리 방이 만들어진다. 0~9번 방까지 null값으로 자동 초기화 된다. Employee타입의 객체 하나 e1 생성, heap영역에 Employee타입으로 no, name, salary 객체가 만들어진다 .  string 타입 객체가 만들어지고 no가 직접 값을 갖는게 아니라 참조한다. string 타입 객체가 만들어지고 name이 참조, 마찬가지로 int 타입 객체가 만들어지고salary가 참조한다. e1이 참조하고 있는 객체를 0번 배열에 대입한다 그러면 이곳의 값이 더 이상 null이 아니다 0번 인덱스는 똑같이 "001"로 참조한다.

    ---

    **생성자 오버로딩**

    매개 변수의 타입, 개수, 순서가 다르게 선언. 

    단, 매개 변수의 타입 개수 그리고 선언된 순서가 똑같을 경우 매개변수 이름만 바꾸는 것은 아무 의미 없다.

    ```java
    class Calculator{
    	int n1;
    	int n2;
    	int result;
    	Calculator() {
    		
    	}

        int plus(int n1, int n2) { // 반환값이 int타입으로 앞에 int이어야 한다.
    		return n1+n2; // 반환한다는 의미.
    	}
    	double plus(double n1, double n2) { // 위와 서로다른 매개변수타입 선언 => 메소드 오버로딩
    		return n1+n2;
    	}
    	double plus(double n1, double n2, double n3) { // 매개변수 개수가 다르다 => 메소드 오버로딩
    		return n1+n2+n3;
    	}

    }
    public class OverloadTest {

    	public static void main(String[] args) {
    		Calculator c;
    		c = new Calculator();
    		int result1 = c.plus(3,4); //3+4계산 결과를 반환한다
    		System.out.println(result1); //7
    		
    		double result2 = c.plus(3.1, 4.2);
    		System.out.println(result2); //7.300000000000001 //7.3으로 딱 떨어지지 않는이유? double타입은 큰 값이 출력이 될 수 있다.
    		
    		double result3 = c.plus(3,  4, 5.0); // int타입 3값이 double 타입으로 자동 형변환..그러므로 위에 n1=3.0이된다.
    		System.out.println(result3); //12.0

    	}

    }
    ```

    [Java Platform SE 8](https://docs.oracle.com/javase/8/docs/api/index.html)

![8](https://user-images.githubusercontent.com/63957819/103460162-77109100-4d57-11eb-84ba-4af79e6463b7.png)

    >> 오버로드

    오버로드는 왜 할까? 객체지향 목적에 부합하기 위해.. 다양한 형태의 인자 값을 처리하기 위해 비슷한 기능이 있다면 오버로드 한다.

    **인스턴스 멤버란?**

    new A(); //객체생성작업 = 인스턴스화
                  // 객체 = 인스턴스
                  // non-static(스태틱에 선언되어있지 않은)필드 = 인스턴스 멤버 필드
                  // non-static 메서드 = 인스턴스 멤버 메서드

    non-static 필드들만 객체 안에 들어가는 거다.

    **정적 멤버와 static**

![9](https://user-images.githubusercontent.com/63957819/103460164-77a92780-4d57-11eb-93f1-c5a8b55895af.png)

    정적은 '고정된'이란 의미를 가지고 있다.

    정적 멤버는 객체(인스턴스)에 소속된 멤버가 아니라 클래스에 소속된 멤버이기 때문에 클래스 멤버라고도 한다.

    static은 객체와 무관하다. 메서드 타입의 객체를 생성하지 않고 클래스이름. 으로 직접 쓰는 방식

![10](https://user-images.githubusercontent.com/63957819/103460165-77a92780-4d57-11eb-9a8e-0d7d679e5894.jpg)

    사용법: 클래스이름.스태틱멤버
    ⇒ static필드 = 클래스 변수
        static메서드 = 클래스메서드

    main에 i가 있는지 확인 sop(A.i);//(x) A.i변수가 있는지 확인 ⇒ 컴파일 에러.  i변수 선언이 클래스.i로 되어있네? static으로 선언된 메소드에서는 static으로 선언되지 않은(non-static)메소드를 불러오지 못한다.

    i변수는 static없이 선언되어있고 non-static메소드는 객체 생성을 해야 사용 가능하다 sop(new A().i); //(0)

    sio(A.si);//(0) static변수 자동 초기화 되므로 사용가능

    sop(new A().si); //(0)객체를 생성해서 써도 되고 객체 생성 없이도 사용할 수 있다.

    sop(newA().si);는 heap에 객체 생성을 하고 안에 si를 찾아라 근데 si가 없네? 그러면 A클래스로 가서 si를 참조한다. 이렇게 하면 느려지고 복잡해지며 이런 방법같이 객체 참조 변수로도 접근이 가능하나 정적 요소는 클래스 이름으로 접근하는 것이 좋다. 이클립스에서는 정적 멤버를 클래스 이름으로 접근하지 않고 객체 참조 변수로 접근했을 경우, 경고 표시가 나타나기 때문이다.

    그러므로 sop(A.si)는 si변수가 static으로 선언 되어져 있고 객체생성 없이 바로 A클래스로 가서 si를 참조한다. main()이 호출되기 전에 static변수는 이미 자동 초기화 되어져 있어 0값을 가진다. 위의 방법보다 클래스이름.스태틱멤버를 쓰는 걸 추천한다.
