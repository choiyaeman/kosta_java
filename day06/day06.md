# day06

원을 회전 시켜라

원← 객체지향언어(Java, C#, C++)

회전 시켜라← 절차적언어(C, COBOL)

절차지향 언어보다 객체지향 언어가 개발속도가 느리다. 왜? thing이 갖고 있는 속성, 기능들이 무엇인지 고려해야 하기 때문이다.

void rotate(double r)                                                                                                                // double type의 매개변수(parameter) r값을 void 즉 반환하지 않겠다라는 의미
// 작업 된 결과를 반환하자고 하면 반환 값 return value라고 한다.

rotate(5.7); 호출  
// 함수 호출시에 전달되는 값(5.7)을 인자값이라 부른다. 매개변수 아래 자동대입
{
// 선언부
// r을 이용한 중심축 계산 회전한다 // 구현부
}

- 제공하는 소스코드

```java
// 원을 회전시켜라
class Circle{ // 클래스 선언 <- 클래스 이름은 다른 클래스와 식별할 목적으로 사용
 double r;
 void rotate(){
		r을 이용한 중심축계산
		회전한다
	}
}
```

```java
// 사각형을 회전시켜라
class Rectangle{
	double width;
	double height;
	void rotate(){
		widht와 height이용하여 중심축계산
		회전한다
	}

}
```

---

- 사용하는 소스코드

```java
class Test {
	public static void main(String[] args) { // 실행하기 위한 코드
		Circle c;
		c = new Circle(); // new는 클래스로부터 객체를 생성시키는 연산자
		c.r = 5.7;
		c.rotate();
}
```

함수 제공하는 소스코드와 사용하는 소스코드가 분리된다. 라이브러리가 바뀌면 사용하는 소스코드도 바꿔야 한다. 즉  결합도가 높아 유지 보수성이 떨어진다.

---

- 필드(Field): 객체의 데이터가 저장되는 곳

    ex) int fieldName;

- 생성자(Constructor): 객체 생성 시 초기화 역할 담당

    ex) ClassName(){..}

- 메소드(Method): 객체의 동작에 해당하는 실행 블록

    ex) void methodName(){...}

메소드는 객체 간의 데이터 전달의 수단으로 사용된다.

byte, short, int 타입의 값이 리턴 되어도 상관x. byte와 short은 int로 자동 타입 변환되어 리턴 되기  때문

초기값이 지정되지 않은 필드들은 객체 생성 시 자동으로 기본 초기값으로 설정된다.

필드의 타입에 따라 초기값이 다르다. 정수 타입 필드는 0, 실수 타입 필드는 0.0 boolean 필드는 false로 초기화, 참조 타입은 객체를 참조하고 있지 않은 상태인 null로 초기화된다.

Car c1= new Car(); 이라는 변수가 하나 만들어지고 new를 만들어서 Car타입의 객체가 생성된다 그 객체를 c1이 참조한다.

```java
// Car 객체 타입
company
model
color
maxSpeed
.
.
tire
```

Car c1= new Car(); 

Car c2= new Car();

Car c3= c1;  

Car.maxSpeed = 400; {x}

c3.maxSpeed = 400;

sop(c3.maxSpeed); // 400

sop(c2.maxSpeed); // 0

변수: 변할 수 있는 값이 저장될 메모리공간

변수의 종류

```java
class A{
	int field1; // 클래스가 갖고있는 변수는 멤버변수, 멤버필드라 한다. 객체 생성시 자동 초기화
	String field2;
	public static void main(String[] args) { // 매개변수도 해당 함수가 끝나면 없어지는 변수
		int num; // 지역변수, local variable, 자동 초기화x
		//~~System.out.println(num)~~; // 초기화안됨 컴파일 오류발생
		if(~~){
			boolean flag = true; // 블록안에 flag는 끝나면 소멸되는 변수. 자동 초기화x
		}
		for(int i=0; ~~~~) { // 괄호안에 i는 끝나면 소멸되는 변수. 자동 초기화x
			int j=10;
		}
	}
}
```

멤버변수, 멤버필드만 객체 생성시 자동 초기화된다.

---

>java -cp bin Test

클래스 실행순서

1. Test.class파일찾기
2. JVM로 Test.class파일이 로드됨
3. Test.class바이트 묶음을 0,1로 재해석(실행시에 재해석이 되는것)
4. 재해석한 내용을 ClassArea(클래스영역)에 기억시킴
5. static멤버필드는 자동 초기화됨
6. main()자동 호출됨 // args하고 소문자 참조형 지역변수 a가 메모리에 할당 되어야 한다.

```java
class A{
	int mf;
	static int smf; // 0값으로 자동 초기화
	void m(int p){
		int lv = p+10;
		sop(lv);
	}
}
// classArea에 기억이 된다
```

```java
public class Test{ // 끝나면 쓰였던 지역변수들 영역이 없어진다
	publis static void main(String[] args) {
		A a; // A클래스 타입의 참조형 지역변수

		a = new A(); // 1. new로 heap메모리할당
								 // 2. A타입의 메모리로 채움(A클래스 타입의 똑같은 내용으로 채움)
								 // 3. 생성자 호출
                 // 4. 대입
		A a1 = enw A(); // 이미 A클래스가 로드 되어있으므로 또 로드 하지않는다.
		a.mf = 10; // a가 참조하는 객체의 mf변수값은 10
		a1.mf = 20; // a1이 참조하는 객체의 mf변수값은 20
 	}
}
```

몇개의 클래스가 JVM에 총 load하여 ClassArea에 기억이되는가?                                           총4개( test, string, a, system)

실행 시 사용하자 하는 코드를 JVM영역에 로드를 runtimeDynamic Load라 불린다.

먼저 static필드가 자동 초기화되고 객체 생성 시 non-static field가 자동 초기화가 된다.

**STACK** 

>지역변수가 메소드 종료시 stack에 할당

>>main 메서드에 존재하는 args, A클래스 타입의 참조형 지역변수 a, 참조형 지역변수a1

먼저 선언한 변수는 아래 쌓이고 나중에 선언한 변수는 위에 쌓인다. 즉 a변수가 아래 a1이 위에 쌓인다.

**HEAP** 

>non-staicfield가 객체 생성시 자동 초기화되고 heap 영역에 할당

>>A클래스가 갖고있는 메서드의 필드, 메서드의 선언부 void m(int p)만 채운다.  객체 생성 시 필드는 0으로 초기화     

 * 단, 스태틱변수는 heap영역에 채워지지 않는다.

종류                  메모리          자동초기화여부     초기화시점     소멸시점

static field         class area       o                            클래스로드     클래스언로드(프로그램 종료될때)

지역변수           stack               x                                                   메소드 종료시

non-static field  heap              o                            객체생성        객체소멸

>>가장 메모리가 빨리 소멸되는 것은 local variable이며 가장 오래 사는 것은 static field

**자바프로그램 실행구조**

![1](https://user-images.githubusercontent.com/63957819/103460078-d28e4f00-4d56-11eb-8076-e9d675330f11.png)

- **JVM이란?**

Java Virtual MachineJAVA와 OS 사이에서 중계자 역할JAVA가 OS에 구애받지 않고 재사용을 가능하게 해 줌메모리 관리 기능(Garbage Collection)

**자바프로그램 실행 과정과 JVM메모리 구조**

프로그램이 실행되면, JVM은 OS으로부터 이 프로그램이 필요로 하는 메모리를 할당받고, JVM은 이 메모리를 용도에 따라 여러 영역으로 나누어 관리한다.

![2](https://user-images.githubusercontent.com/63957819/103460079-d326e580-4d56-11eb-9f8f-08f8caf256b5.png)

- JAVA Source : 사용자가 작성한 JAVA 코드
- JAVA Compiler : JAVA 코드를 Byte Code로 변환시켜주는 기능
- Class Loader : Class파일을 메모리(Runtime Data Area)에 적재하는 기능
- Execution Engine : Byte Code를 실행 가능하게 해석해주는 기능
- Runtime Data Area : 프로그램을 수행하기 위해 OS에서 할당 받은 메모리 공간

![3](https://user-images.githubusercontent.com/63957819/103460080-d3bf7c00-4d56-11eb-96fa-578664c3ab4f.png)

---

```java
public class WatchTV { // 실행용도 클래스이므로 메인메서드를 갖는다.

	public static void main(String[] args) {
		TV tv1;
		tv1 = new TV();
		//tv1.power = true; // 전원이 켜져있는상태
		tv1.powerOn();
		tv1.volume = 5;
		for(int i=0; i<10; i++) {
			tv1.volumeUp();
		}
		tv1.volumeUp();
		System.out.println(tv1.volume); //16
		
		//tv1.channel = 11;
		tv1.setChannel(11);
		tv1.channelUp();
		System.out.println(tv1.channel); //12
	}
}
```

```java
public class TV { //멤버필드를 추가해야한다.

	boolean power; //전원
	int volume; //음량
	int channel; //채널
	
	void powerOn() {
		power = true;
	}
	void powerOff() {
		power = false;
	}
	
	void volumeUp() {
		volume++;
	}
	
	void setChannel(int channel) { //인자값을 전달했으므로 인자값에 해당하는 매개변수가 반드시 선언되어 있어야한다.
		this.channel = channel; //리턴값이 없는 메소드로 매개값을 
							    //받아서 channel 필드값을 변경
	}
	void channelUp() { //인자값이 없었으므로 매개변수 없는 메서드 생성
		channel++;
	}
	
}
```

실행순서

day6>java -cp bin WatchTV

1 WatchTV.class파일 찾기

2) JVM메모리에 로드

3) WachTV.class를 0,1로 재해석

4) class area에 기억

5) static field자동초기화 

(예를들어 WatchTV static변수를 만났다 하면 자동 초기화 내려가다 만나면 초기화.. 그래놓고 TV클래스로 찾아가고 static필드를 만나면 초기화한다)

6) main()호출됨

![4](https://user-images.githubusercontent.com/63957819/103460081-d3bf7c00-4d56-11eb-9d52-7ed33c883dfb.png)

**-Class area영역** 

watchTV.class, TV.class 텍스트 형식이 아닌 0과1로 해석되어 저장 되어있다.

>watchTV.class에는 WatchTV, String, TV, System 총4개 클래스가 필요하다.

**-STACK영역**

args(int), tv1(main), ch(setChannel)

**-HEAP영역**

new로 Heap영역 할당. 로드된 TV클래스로 채워짐

new키워드로 객체 생성하는 절차>

power멤버변수 자동 초기화⇒false

volume멤버변수 자동 초기화⇒0

channel멤버변수 자동 초기화⇒0

메서드가 내용까지 들어가는게 아니라 선언부만(void powerOn(), void powerOff(), void volumeUp(), void setChannelUp()) 갖게된다.

진행과정>

tv1.power ⇒tv1이 참조하는 객체를 찾아서 heap영역에서 poweron메서드 찾고 그 메서드를 호출한다는거는 TV클래스가 갖고있는 powerOn메서드로 power는 지금 사용중인 객체의 power를 의미한다. 그러므로 power는 true로 출력이 된다.

...

..

.

tv1.setChannel ⇒tv1이 참조하는 객체의 메서드중에 setChannel 메서드를 찾고 그 메서드의 내용을 확인 해보면 요구되는 ch이라는 매개변수가 필요하고 전달된 인자값이 11이므로 11이 들어오게된다. 현재 사용중인 객체의 channel 필드값을 11로 채워라는 의미 

tv1.ChannelUp ⇒tv1이 참조하는 channelup찾고 호출하러간다 매개변수없이 현재사용중인 channel을 1증가하라 11→12가 된다. System.out.println으로 12가 출력하게된다.

메인메서드가 끝난 다음 클래스가 종료 이제껏 사용했던 jvm에 로드된 메모리가 업로드

```java
public class CallByValue {

	public static void test(int i) {
		i=99;
	}
	public static void test(int[] arr) { // arr
		arr[0] = 99;
	}
	public static void main(String[] args) {
		int i=1; //지역변수 선언
		test(i); //메인메서드에서 test메서드 호출
		System.out.println(i); //1
		
		int[] arr= {1,2,3}; //int배열타입 지역변수 선언. 
		test(arr);
		System.out.println(arr[0]); //99 //배열은 참조형이므로 main메서드 arr이 test메서드 arr에 대입하므로 99가 출력
	}
}
```

기본자료형vs참조형

기본형, 참조형 모두 인자값이 그대로 복사되서 매개변수에 붙여넣기로 사용한다 CallByValue라고 불린다

- 자바에서 배열은 무조건 참조형 테스트메서드 매개변수 arr에 메인이 쓰였던 arr이 대입 되므로 그대로 쓰여진다. test메서드 arr main메서드 arr 같은 값을 갖는다. 1→99 그리고 test메서드 종료하고 메인에서 test출력

![5](https://user-images.githubusercontent.com/63957819/103460082-d4581280-4d56-11eb-9206-633a76112d6a.png)

![6](https://user-images.githubusercontent.com/63957819/103460083-d4f0a900-4d56-11eb-8eb4-c5c2d5eb5d6c.png)

**-Class영역**

main메서드

**-STACK영역**

arr(test)

arr(main)

i(test) ⇒1

i(main) ⇒1값대입

**-HEAP영역**

세개방

진행과정>

지역변수 i는 계속 남아있고 test메서드 호출

test메서드로 가면 두개 메서드가 있는데 첫번째 int타입 i변수가 인자로 전달이 되면

매개변수가 int타입으로 선언된 메서드를 호출하게된다.  전달된 인자형 자료형=매개변수 자료형

i변수값을 99로 바꾸라해서 1→99되고 test메서드가 끝나면 호출이 마무리가 되니깐 test메서드는 삭제가 되고 메인 메서드의 다음 코드인 sop(a); a값이 1 출력이 된다.

int타입 arr 배열은 heap영억에 세개의 방이 생기고 각 방에 1,2,3이 들어간다. int타입 arr 배열 인자로 전달이 되면 매개변수 int타입으로 선언된 메서드를 호출하게되고 i변수값은 99,2,3이 된다. test메서드가 끝나면 호출이 마무리되고 삭제된다. 메인 메서드로 넘어가 sop(arr[0])는 99가 출력이된다.
