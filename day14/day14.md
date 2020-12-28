# day14

### j**ava.lang.String클래스** → 객체내용 불변

- 객체가 한번 만들어지면 문자열 내용이 변경 불가
- ""로 대입가능. 사용법) String a = "hello";
- 플러스 연산으로 문자열 연결 a+"java"
- equals()를 overriding해둠→ **문자열 내용 비교**

### **StringBuffer** → 객체내용 가변

- 객체 내용이 가변적으로 변할 수 있다.
- ""로 대입불가. 사용법) StringBuffer a = new StringBuffer("hello");
- append메서드로 문자열 연결 a.append("java");
- equals()를 overriding안해둠-**Object클래스의 equals()상속됨==**

### StringTokenizer

- 특정 문자로 구분된 문자열을 뽑아낼 때 사용

### Wrapper클래스

- Byte클래스, Short클래스, Integer클래스, Long클래스,

      Character클래스, Boolean클래스

      Float클래스, Double클래스,

- Object클래스의 하위 클래스들이다.
- 클래스 타입의 참조 자료형

**기본형과 참조형 사이에서 서로 형 변환 못한다.**
int i=7;
Object obj = i; (X -기본형과 참조형 사이에서 서로 형 변환 불가)

Integer inte = new Integer(i); //기본형→참조형으로 변환 : 박싱
obj = inte; (O - upcasting)

int j = (int)obj; (X-기본형과 참조형 사이에서 서로 형 변환 불가)
Integer inte2 = (Integer)obj; (O -downcasting) 
int j = inte2.intValue(); (O) //참조형→기본형으로 변환 : 언박싱

```java
package day14;

import java.util.StringTokenizer;

public class StringStringBuffer {
	
	public static void main(String[] args) {
		String s1, s2, s3, s4;
		s1 = new String("hello");
		s2 = new String("hello");
		s3 = "hello";
		s4 = "hello";
		System.out.println(s1==s2); //s1이 참조하는 객체 s2가 참조하는 객체는 다른 값이므로 false
		System.out.println(s1==s3); //같은 내용임에도 불구하고 다른 객체이므로 false 
		System.out.println(s3==s4); //s3이 가리키는 메모리와 s4가 가리키는 메모리는 서로 같으므로 true
		
		System.out.println(s1.equals(s2)); //true
		System.out.println(s1.equals(s3)); //true
		System.out.println(s3.equals(s4)); //true
		
		System.out.println(s1 + "java"); //+연결 연산자, s1객체 내용불변
		System.out.println(s1); //hello 
		
		StringBuffer sb1, sb2, sb3, sb4;
		sb1 = new StringBuffer("hello");
		sb2 = new StringBuffer("hello");
		sb3 = new StringBuffer("hello");
		sb4 = new StringBuffer("hello");
		
		System.out.println(sb1 == sb2); //false 서로 다른 객체  
		System.out.println(sb1 == sb3); //false
		System.out.println(sb3 == sb4); //false
		System.out.println(sb1.equals(sb2)); //==와 같음 false
		
		System.out.println(sb1.append("java")); //hellojava
		System.out.println(sb1); //hellojava
		//----------------------------------------------------
		char c = s1.charAt(0); //hello의 0번 index 문자값 h
		int size = s1.length(); //hello의 문자길이 5
		byte[] bytes = s1.getBytes(); //hello의 바이트값들
		for(byte b: bytes) {
			System.out.print(b);
			System.out.print(",");
		}
		System.out.println();
		
		//앞뒤가 같은 문자열 :ABBA
		s1 = "ABC"; //"ABBA";
		size = s1.length();
		int i;
		for(i=0; i<size/2; i++) { //산술연산이 비교연산보다 우선순위가 높다. size를 2로 나누는 연산이 먼저
			if(s1.charAt(i) != s1.charAt(size-1-i)) {
				break;
			}
		}
		if(i == size/2) { //변수가 반복문안에서 선언된 변수는 안에서만 쓸수있다. 그러므로 밖에서 선언 해준다.
			System.out.println("앞뒤가 같은 팰린드롬 문자열 입니다.");
		} else {
			System.out.println("앞뒤가 다른 문자열 입니다.");
		}
		
		s1 = "ABCD.javaABCD.javaABCD.java";
		int index = s1.indexOf("D"); //0번 인덱스부터 찾아가라
		System.out.println(index); //3
		int lastIndex = s1.lastIndexOf("D"); //가장 마지막 인덱스
		System.out.println(lastIndex); //21
		System.out.println(s1.indexOf("D", 10)); //12 -> 10번 인덱스부터 찾아가라
		System.out.println(s1.indexOf("JAVA")); //-1
		
		String[] arr = s1.split("\\.java"); //.java를 기준으로 문자열을 자른다. -> 매개변수표현식이 레귤러이므로 일반으로 표현하기위해 \\하자!
		for(String s : arr) {
			System.out.print(s);
			System.out.print(",");
		}
		
		s1 = "90:85::70"; //국어90, 영어85, 수학 미응시, 과학70.  ::점수란? 의미상 아에 시험을 보지않은 과목  
		s2 = "80:60:0:20"; //국어80, 영어60, 수학0, 과학20.
		System.out.println();
		String[] arr1 = s1.split(":");
		for(String s : arr1) {
			System.out.print(s);
			if(s.equals("")) {
				System.out.print("미응시");
			}
			System.out.print(",");
		}
		
		System.out.println();
		StringTokenizer stk = new StringTokenizer(s1, ":"); //s1.split이나 StringTokenizer이용하는거랑 같다. 
															//StringTokenizer는 빈 문자열은 무시한다. 즉 미응시한 수학점수는 분리 해주지 않는다.
														  	//의미를 담고있는 문자열을 token이라 한다. 중간 공백, 줄바꿈 같은 것은 토큰이라 보지 않는다.
		while(stk.hasMoreTokens()) { //hasMoreTokens()는 StringTokenizer 클래스 객체에서 다음에 읽어 들일 token이 있으면 true, 없으면 false를 return한다.
			String s = stk.nextToken(); //nextToken()는 StringTokenizer 클래스 객체에서 다음 토큰을 읽어 들인다.
			System.out.print(s);
			System.out.print(",");
		}
		
	}

}
```

![1](https://user-images.githubusercontent.com/63957819/103213919-79877b00-4952-11eb-9041-841cb98b57a0.jpg)

![2](https://user-images.githubusercontent.com/63957819/103213921-7ab8a800-4952-11eb-97d0-d07ea7894b43.jpg)

**정규 표현식**

![3](https://user-images.githubusercontent.com/63957819/103213922-7b513e80-4952-11eb-80a1-1f6f054f4de1.png)

```java
package day14;

public class Wrapper {
	public static void main(String[] args) {
		int i = 7;
		Object obj;
		obj = i; // 원래는 기본형과 참조형 사이에서 형 변환 불가이지만, 이 코드가 가능한 이유는 컴파일시 아래2줄 코드로 변환됨. 오토박싱
//		Integer inte = new Integer(i); // 기본형->참조형. 박싱
//		obj = inte; //upcasting
		
		int j;
		j = (Integer)obj; //컴파일시 아래2줄 코드로 변환. 오토언박싱
//		Integer inte2 = (Integer)obj; //downcasting
//		j = inte2.intValue(); //참조형->기본형. 언박싱
		
		//i.toString() //(x) 기본형은 직접 값을 가지고있고 메모리를 참조x 왜냐? .은 참조된 메모리를 찾아간다는 의미이다.
		Integer inte = new Integer(i);
		System.out.println("int타입의 최대값:" + Integer.MAX_VALUE);
		System.out.println("int타입의 최소값:" + Integer.MIN_VALUE);
		
		Object[] arr = new Object[10];
		arr[0] = new Object();
		arr[1] = new String("hello");
		arr[2] = new StringBuffer("hello");
		arr[3] = 10;   //autoboxing 컴파일시 arr[3] = new Integer(10);
		arr[4] = true; //autoboxing 컴파일시 arr[4] = new Boolean(true);
		
		String[] arr1 = {"a", "b", "c"}; // String도 참조형
		Object obj2 = arr1; 
		
		//Shallow Copy & Deep Copy
		
	}
}
```

---

책 490p 11번째 줄 클래스 실행 순서

![4](https://user-images.githubusercontent.com/63957819/103213924-7b513e80-4952-11eb-9e0e-bc367ef98a4c.jpg)

Dynamic Load = 실행 시 필요한 자원을  JVM으로 로드

A. **Loadtime Dynamic Load**

1. java.lang.Class.class 파일 찾기

      java.lang.String.class 파일 찾기(rt.jar)

  2. JVM에 로딩

  3. byte코드를 binary로 변환

  4. static변수 초기화

- - - - - - - - - - - - 

B. **Runtime Dynamic Load**

 5. main()자동 호출

11번째 줄 실행

Class.forName("com.my.Customer"); 

1) 인자(com.my.Customer) 문자열에 해당하는 클래스(com.my.Customer.class파일)를 찾기

2) JVM에 로딩

3) byte코드를 binary로 변환

4) static 변수 초기화

→실행 전에는 2개파일 로드 됐다가 실행 후에는 3개 파일이 로드

---

```java
package day14;

import java.util.Scanner;

public class ClassTest {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("로드할 클래스 이름을 패키지까지 정확히 입력하세요: ");
		String className = sc.nextLine();
		try {
			Class clazz = Class.forName(className); // 실행시에 클래스를 로드할 수 있다.
			Object obj = clazz.newInstance(); // 로드된 클래스를 갖고 객체 생성하는 작업. 키보드 입력받은 클래스 객체생성을 의미
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
```

메인메서드 호출되기 전 상태 로드 될 클래스 개수는 ClassTest, String, Scanner, System 클래스, Class, Object, ClassNotfound, InstantiationException, IllegalAccessException 총 9개클래스가 로드 되어 있다. 메인메서드 호출된 후에는 인자(className)문자열에 해당하는 클래스 총 10개가 로드된다.

![5](https://user-images.githubusercontent.com/63957819/103213925-7be9d500-4952-11eb-8071-04afac1da642.jpg)

/a.do /b.do /c.do /d.do 이런 다양한 요청들이 들어오게되면 요청을 처리해줄 프로그램을 만드는데 이렇게 코드를 한다 해보자 if("/a.do요청") a타입 객체를 생성해라 A a = new A().. b~~, c~~, d~~ 있다고 하자 변수의 이름도 많아질 수 밖에 없다. 정말 안 좋은 코드이다. 이 요청이 a~d까지 끝나는게 아니라 e, x, y도 요청이 될 수 있다. 확장성을 고려해야 한다.

![6](https://user-images.githubusercontent.com/63957819/103213926-7c826b80-4952-11eb-869f-1b3794e11730.jpg)

 문서를 하나 만들어두자 웹 서버 안쪽에 xml파일 /a.do=A /b.do=B, /c.do=C 에 해당하는 클래스는 A, B, C야 ~~ 구분하지 말고 Class.forName()을 이용해서 요청 url값을 인자로 사용하면 사용자가 /a.do라고 했을 때 class.forName() 메서드의 인자가 자동 대입이 되고  /b.do가 요청이 된다고 하면 ClassName이름을 찾고 그 클래스를 이름을 가지고 ClassforName인자로 사용하게 되고 추가되는 것은 파일에 더 추가만 해주면 된다.

---

### 리플렉션(getDeclaredConstructors(), getDeclaredFields(), getDeclaredMethods())

- Class 객체를 이용하면 클래스의 생성자, 필드, 메소드 정보를 알아낼 수 있다. 이 것을 리플랙션이라고 한다.
- Class 객체는 리플렉션을 위해 getDeclaredConstructors(), getDeclaredFields(), getDeclaredMethods()를 제공하고 있다.

→ 유지보수를 편하게 하기 위해서 사용

```java
package day14;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ClassTest {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("로드할 클래스 이름을 패키지까지 정확히 입력하세요: ");
		String className = sc.nextLine();
		try {
			Class clazz = Class.forName(className); // 실행시에 클래스를 로드할 수 있다.
			Object obj = clazz.newInstance(); // 로드된 클래스를 갖고 객체 생성하는 작업. 키보드 입력받은 클래스 객체생성을 의미
			Field[] fields = clazz.getDeclaredFields(); // 이 클래스에서 지원하는 멤버필드들이 무엇인지 알아낼수 있다. 객체가 갖고있는 필드를 알아낼수 있다.
			for(Field f : fields) {
				System.out.print(f.getName());
				System.out.print(",");
			}
			System.out.println();
			Method[] methods = clazz.getDeclaredMethods(); //clazz가 갖고있는 선언된 메서드들을 반환한다. // 객체를 반영 해주는 작업
			for(Method m : methods) {
				System.out.print(m.getName());
				System.out.print(",");
				if(m.getName().equals("toString")) { //toString이 있으면 호출
					try {
						Object result = m.invoke(obj); // invoke: 메소드를 invoke로 호출하는 방법
						System.out.println("\n" + result);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
			System.out.println();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
```

![7](https://user-images.githubusercontent.com/63957819/103213927-7c826b80-4952-11eb-8586-35ae4d0a46b8.png)

arrayList가 갖고있는 메서드들

![8](https://user-images.githubusercontent.com/63957819/103213928-7d1b0200-4952-11eb-92c4-5b0de9854760.png)

---

```java
package day14;

import java.util.Calendar;
import java.util.Date;

public class DateCalendar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Date now = new Date();
		System.out.println(now);

		// Calendar c = new Calendar(); // 추상 클래스이므로 new키워드로 객체생성x
		Calendar c = Calendar.getInstance(); // 메소드 호출하게되면 GregorianCalendar객체를 생성해서 리턴하는 코드가 getInstance메소드 안에 들어있고 리터된값이 캘린더 타입으로 upcasting
		Class clazz = c.getClass(); // c가 참조하는 객체의 자료형 정보를 담고있다. GregorianCalendar타입
		System.out.println("Calendar객체의 실제 클래스 타입은 " + clazz.getName());

		int ampm = c.get(Calendar.AM_PM);
		if (ampm == Calendar.AM) {
			System.out.println("오전");
		} else {
			System.out.println("오후");
		}

		System.out.println(c.get(Calendar.MONTH) + 1); // 1월인 경우 0이므로 더하기 1을 반드시 해줘야한다.
		switch (c.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			System.out.println("일요일");
			break;
		case Calendar.SATURDAY:
			System.out.println("토요일");
			break;
		default:
			System.out.println("주중~~~");
			break;
		}

		System.out.println(c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Calendar feb = Calendar.getInstance();
		feb.set(2021, 1, 1); // 년,월,일을 강제로 세팅 //이 값은 2021년 2월 1일이다. 월을 가져올때는 1월이 0부터이다.
		System.out.println("마지막 날짜 : " + feb.getActualMaximum(Calendar.DATE) + "일");

		// -------------------------
		System.out.println("2021년 2월 달력");
		System.out.println("일  월  화  수  목  금  토");
		// TODO 결과로 아래와 같은 달력을 출력하시오
		/*
		 * 일 월 화 수 목 금 토 
		 *    1  2  3  4  5  6 
		 * 7  8  9  10 11 12 13
		 * 14 15 16 17 18 19 20
		 * 21 22 23 24 25 26 27 
		 * 28
		 */

		int maxDate = feb.getActualMaximum(Calendar.DATE);
		int cnt = 0;
		switch (feb.get(Calendar.DAY_OF_WEEK)) { //calendar.get(Calendar.DAY_OF_WEEK)은 calendar가 가르키는(의미하는) 특정 날짜가 무슨 요일인지 알기 위해 쓰임.
		case Calendar.SATURDAY:// 6
			cnt++; // cnt=6; break;와 같음
		case Calendar.FRIDAY:// 5
			cnt++; // cnt=5; break;와 같음
		case Calendar.THURSDAY:// 4
			cnt++;
		case Calendar.WEDNESDAY:// 3
			cnt++;
		case Calendar.TUESDAY:// 2
			cnt++;
		case Calendar.MONDAY:// 1
			cnt++;
		// case Calendar.SUNDAY: 0
		}
		for (int i = 0; i < cnt; i++) { // 1일 출력하기 전에 공백출력하기
			System.out.print("   ");
		}
		for (int date = 1; date <= maxDate; date++, cnt++) {
			if (cnt % 7 == 0) {
				System.out.println();
				cnt = 0;
			}

			System.out.print(date);
			if (date < 10) {
				System.out.print("  ");
			} else {
				System.out.print(" ");
			}

		}

	}

}
```

![9](https://user-images.githubusercontent.com/63957819/103213929-7d1b0200-4952-11eb-9cf6-15a417ab7a17.jpg)

![10](https://user-images.githubusercontent.com/63957819/103213931-7db39880-4952-11eb-8799-ae8c9fe12449.png)

캘린더 생성자는 왜 존재할까? 자식(GregorianCalendar)을 위해 존재
