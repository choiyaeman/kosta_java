# day09

![day09%20a0e6f6a0172e4cb3995fec336fc6f45b/20201218_092431.jpg](day09%20a0e6f6a0172e4cb3995fec336fc6f45b/20201218_092431.jpg)
![20201218_092431](https://user-images.githubusercontent.com/63957819/102783965-112f1b80-43df-11eb-8841-1743c0cce69a.jpg)

호출순서>

다른 클래스 메소드를 호출하려면 객체를 생성해서 생성하거나 스택틱은 클래스이름. 해야한다.

---

**-STACK**

main메서드 자동호출...args매개변수, t라는 지역변수 아래서부터 위로 차례로 쌓인다.

 t변수가 참조하는 객체는 new라는 키워드를 통해서 힙 영역 할당 클래스 타입으로 채운다.

메서드 선언부만 갖게된다

---

**-HEAP**

void a() void b() void c()

생성자 호출 자동생성자가 만들어지고 test클래스를 보면 생성자를 없지만 매개변수 없는 생성자가 만들어진다. 만들어진 상태이기때문에 객체생성시 호출 할 수 있는 것이다.

t.a(); t가 참조하고 있는 객체를 따라가서 그 객체가 갖고있는 a메서도 선언부가 보이죠? a메서드 선언부로 가고 객체의 안쪽에서는 this.b(); 그리고 this. 생략가능하다 this.b메서드 호출하면 현재 사용중인 객체이므로 역추적해서 찾아가보면 heap영역에 b메서드 보이죠? b메서드 호출하러 갑시다. 다시 b메서드로 가고 this.c 현재 객체가 갖고있는 c메서드가 있는지 보자 있으면 c메서드 호출 후 끝나면 다음 지점인 b메서드의 sop("b ~")로 오게된다. 또 끝나면 a메서드로 가게되고 끝나면 메인으로 돌아와 t.a()로 가고 끝난다. c end, b end 순으로 출력 된다.  왜냐 호출 다음 지점으로 돌아오기 때문

---

![day09%20a0e6f6a0172e4cb3995fec336fc6f45b/20201218_093943.jpg](day09%20a0e6f6a0172e4cb3995fec336fc6f45b/20201218_093943.jpg)

메인 메서드의 a의 값은 기본형 값인 int타입 10

a값이 t.f 메서드로 호출 하면 f메서드에서만 쓰이는 a가 새로 만들어지면서 차곡차곡 쌓인다.

자바 메서드의 인자 전달 방법은 CallByValue로 메인 메서드가 갖고 있던 a변수값이 그래로 복붙되서 f변수 매개변수도 같은 값을 가진다. 그리고 a++;를 통해(10→11) 출력 되는 결과 값은 11이 출력 된다. f메서드가 끝나게 되면 매개변수 지역변수 모두 메모리에서 사라지게 된다. 함수가 끝나면 statc영역에 쓰였던 지역변수 매개변수 메모리에서 사라지게 된다는 뜻이다. 다시 메인 메서드로 가고 메인의 a변수값이 20으로 바뀌고 t.f메서도 호출 매개변수 a가 다시 메모리에 또 만들어지게 된다. 메서드가 호출 될 때마다 없어졌다 만들어지고 한다. f메소드의 매개변수는 10+20=30의 값이 되고  a매개변수는 30이 된다. a++을 만나 31이 되고 함수 끝나면 매개변수 지역변수 없어지게 된다.

메인메서드에서 필요로 하는 c변수가 필요 Customer타입의 객체가 힙 영역에 있다.

t.f인자값으로 c를 전달하겠다. c값이 채워지고 그 값을 f메서드의 인자로 전달 메소드 호출 f메서드에 c가 선언되어있으니 f메소드의 c의 자료형도 Customer타입이고 메인메서드에서 쓰였던 c의 값이 그대로 f메서드 매개변수로 그대로 복붙이 되는 것이다. 그러면 드디어 f도 c가 Customer참조하는 정보값을 가진다. c변수 null이라는 것은 아무것도 참조하지 않는다는 뜻이므로 f매개변수는 c는 아무값도 참조 않을래 c변수 대입하는 값이 없어진다. 끝나면 메인메서드의 다음 코드로 돌아간다. 메인의 c는 아직도 customer 타입을 참조하는 것을 볼 수 있다.

---

```java
package com.my.dao;

import com.my.vo.Customer;

public class CustomerDAO {
	private Customer[] customers; //고객저장소
	private int cnt=0;
	public CustomerDAO(int size){
		customers = new Customer[size];
	}
	public  Customer[] selectAll() {
		//return null;
		//return customers;
		
		//저장소의  고객들이 저장된 새로운 배열생성 
		Customer[] cArr = new Customer[cnt];
		
		//저장소의 고객들을 새로운 배열에 복붙
		//for(int i=0; i<cArr.length; i++) {
		//	cArr[i] = customers[i];
		//}
		System.arraycopy(customers, 0, cArr, 0, cnt);
		return cArr;
	}
	public void  insert(Customer c) {
		// int cnt=0; (x) //누적이 되기 위해서는 cnt선언부로 지역변수 대신 멤버필드로 바꿔
		this.customers[cnt] = c;
		cnt++;
	}
	public Customer selectById(String id) {
		/*
		 * 저장소의 고객ID값과 매개변수ID값이 같은경우
		 * 저장소의 고객객체를 반환
		 */
		for(int i=0; i<cnt; i++) {
			Customer c = customers[i];
			String cId = c.getId();
			//String cId = c.id;
			if(cId.equals(id)) {
				return c;			
			} //else{reurn null} (x) 
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
		CustomerDAO dao = new CustomerDAO(3);
		
		System.out.println(">>1. 고객 전체 조회<<");
		Customer[] cAll = dao.selectAll();
		if(cAll != null) {
			for(int i=0; i<cAll.length; i++) {
				Customer c = cAll[i];
				System.out.println(c.getId() + "," + c.getPwd() + ", " + c.getName());
			}
		}
		System.out.println(">>2. 고객추가<<");
//		dao.insert("id1", "p1", "n1");
		dao.insert(new Customer("id1", "p1", "n1"));
		dao.insert(new Customer("id2", "p2", "n2"));
		
		System.out.println(">>3. 고객 전체 조회<<");
		Customer[] cAll3 = dao.selectAll();
		for(int i=0; i<cAll3.length; i++) {
			Customer c = cAll3[i];
			System.out.println(c.getId() + "," + c.getPwd() + ", " + c.getName());
		}
		
		System.out.println(">>4. 고객 ID로 조회<<");
		String id = "id2";
		Customer c4 = dao.selectById(id);
		if(c4 != null) { //이곳이 출력
			System.out.println(c4.getId() + "," + c4.getPwd() + ", " + c4.getName());
		}else {
			System.out.println("ID가 존재하지 않습니다");
		}
		
		System.out.println(">>5. 고객 ID로 조회<<");
		String id1 = "id9";
		Customer c5 = dao.selectById(id1);
		if(c5 != null) {
			System.out.println(c5.getId() + "," + c5.getPwd() + ", " + c5.getName());
		}else {//이곳이 출력
			System.out.println("ID가 존재하지 않습니다"); 
		}		
	}
}
```

메인메서드는 사용자용 쪽 소스코드 이므로 바꾸지 말고 제공자쪽 소스코드만 바꾸자!!

 CustomerDAO. CustomerDAO쪽에 생성자 만들어라 매개변수는 전달한 인자가 숫자 3이니깐 매개변수 유형이 int타입으로 선언 앞에 접근 제어자가 없는 default접근제어자 상태 자기 클래스와 같은 패키지 접근 가능하나 다른 패키지에 접근 못한다. 메인과 dao패키지는 서로 다른 패키지이므로 누구나 접근 가능한 public 접근 제어자를 써야한다. 저장소에 크기를 지정해주자.→size 예를들어 사용자코드가 30으로 바꾸면 30명만 저장 할 수 있는 코드가 만들어지게 된다.

항상 저장 될 때 고정된 크기가 아니고 사용자 임의로 사용하려면 생성자 인자로 전달 해줘야한다.

dao.selectAll 하는일이 뭔가 보니 고객 전체조회 사용될 selectAll이 CustomerDAO에 있어야 한다.

public 접근 제어자로 selectAll 매개변수없고 리턴 타입이 반드시 결정되어있어야 하는데 반환 받는 사용자 쪽에서 Customer배열 타입으로 리턴 받아가므로 Customer[]타입으로 선언 되어 있어야 한다. 그리고 내용이 채워져야 하는데 참조형인 배열 타입으로 반환한다 했으니깐 null값으로 반환 할 수 있다.

또 메서드가 insert메소드가 필요 접근제어자 public, 메소드 이름은 insert, 매개변수의 타입은 전달된 인자가 Customer타입의 객체가 전달됐다면 매개변수 타입도 Customer타입으로 선언 되어야 한다.

리턴 타입 결정할까요? insert타입의 반환값 있는지 보자 없네? void로 선언해주면 된다.

insert메서드는 고객을 추가하는 작업이에요..Custoemr타입의 객체를 만들어서 전달을 합니다.

dao.insert("id1", "p1", "n1")→인자 3개 전달된 insert메서드 이런 메서드를 정상실행되도록 만들려면 public void insert(String id, String pwd, String name) 각각 인자로 전달 할 수 있지만 매개변수가 많이지면 사용자가 쓰기 불편해진다. 그래서 매개변수를 한 개만 활용해서 Custoemr타입의 객체로 전달 할 거다 .고객의 아이디 비번 이름이 들어가게 될 것이다. insert메서드에서 한개짜리로 처리가 가능하게 되고 c인자를 고객 저장소에 누적하는 일을 해주면 된다. 저장소가 this.customers의 0번 인덱스에 c를 대입하겠다고 하면 저장소에 무조건 0번 인덱스에 호출한다는 의미다. 사용자는 한번 두번 세번 호출할수 있는데 이건 계속 0번인덱스만 덮어쓰기가 된다. 계속 연속적인 차례대로 저장할면 인덱스의 위치가 관련된 변수가 있어야한다. 그 변수를 cnt로 준거다. cnt변수 선언가 cnt++있어야한다. 지역변수로 선언하면 stack영역에 쌓였다가 지역변수가 없어져서 0→1 0→1 호출 될 때마다 0 1 0 1 이므로 누적이 되기 위해서는 cnt선언부로 지역변수 대신 멤버필드로 바꿔주면 된다. 접근하지 못하게 하려면 private으로 선언하면 된다. insert메소드가 2번 호출되면 cnt는 2까지 증가하게 된다.

고객 전체 조회 selectAll만들건데 저장되어있는 고객들을 모두 반환 해줘야 한다. insert두번 했으니깐 저장소에는 0,1인덱스 값이 채워져 있을 거다 그 정보 값을 리턴 해주면 되는데 return customers;

customers참조형으로 0,1 고객정보가 채워져 있고 마지막 2번 인덱스는 null값으로 초기화 되어있는 상태 이걸 cAll3변수에 반환하겠다 이 cAll3도 customers 세개짜리 방을 참조 반복문 for문으로 cAll3이 참조하는 세개짜리 방을 말한다. 0~3작을때까지 반복.. 차례차례 c변수에 대입 i가 0이면 cAll3[0] 그걸 c변수가 참조 다시 반복이 돌게되면 cAll3[1].. 반복문 안에 선언한 변수는 한번밖에 못쓴다 반복이 한번 돌 때마다 변수가 새로 만들어진다. 반복문이 끝나면 c변수는 못쓴다. c변수 메모리가 없어진다. i가 2일때 2번 인덱스값이 현재 null이기 때문에 c변수가 참조하는 값은 null인 것이다. c.getId()작업을 못한다→아무것도 참조하지않는 getId를 호출하라? x 이 자리에서 NullPointException 발생 그 자리에서 프로그래밍이 죽는다. 이런 방법으로 return customers;해주면 안된다. 리턴 타입을 Customer[] cArr = new Customer[cnt] → 저장된 고객 수 만큼 한 배열을 만들어 놓고 그 배열에 복사 붙여 넣기 해야 한다. cnt만큼 customers[i]번째 인덱스만큼 cArr[i]에 대입하라. 그리고 cArr리턴해라

→배열 타입이므로 충분히 리턴 할 수 있다. 0번방, 1번방 채워지고 저장된 고객들만 배열을 만들고 반환하는 절차로 null값이 없다.

자바 라이브러리를 통해 배열 복사 방법은? System.arraycopy메서드를 쓰게 되면 원본 배열의 내용을 복사해서 대상본 배열에 붙여넣기. 

원본이 customers, 0번 인덱스부터 대상본이 cArr로 붙여넣어라..

selectById메서드는 매개변수가 String타입의 문자열 값 id가 전달  전달된 id값과 저장소 고객 id를 반환 해줘야 한다. 먼저 전달된 매개변수 id값이 저장소의 고객id값이 같은 경우 저장소의 고객 객체를 반환하면 되는 것이고 같지 않으면 반복으로 끝까지 돌았다가 못 찾으면 return null 해준다.

String cId = c.id; // X 메서드가 아닌 id 멤버필드로 직접 찾아서 대입하면 안되나? 컴파일 에러 뜬다

id멤버가 pirvate으로 선언 되어 있기 때문에 다른 클래스 에서 접근 할 수 없다.

getId메서드는 public으로 사용되어 있기 때문에 가능한 것이다. 저장소에 저장되어있는 고객 아이디와 인자로 전달된 아이디가 같으면 저장소에 있는 고객 객체를 반환해라. else (x)  0번 인덱스랑 매개변수랑 다르다 해서 1번 인덱스랑 매개변수랑 같을 수도 있는 것이기 때문에  return null해주면 안된다 끝까지 돌고 확인 해줘야 한다.

---

다른 디렉터리에 있는 거 보고 싶으면

1.import

2. worksapce 디렉터리를 붙여넣기해서 new Project만들기

![day09%20a0e6f6a0172e4cb3995fec336fc6f45b/Untitled.png](day09%20a0e6f6a0172e4cb3995fec336fc6f45b/Untitled.png)

![day09%20a0e6f6a0172e4cb3995fec336fc6f45b/Untitled%201.png](day09%20a0e6f6a0172e4cb3995fec336fc6f45b/Untitled%201.png)

---

```java
import java.util.Scanner;

import com.my.dao.CustomerDAO;
import com.my.vo.Customer;

public class CustomerMain {
	private CustomerDAO dao = new CustomerDAO(3);
	//Scanner 타입의 sc멤버변수 선언 =>각각 따로하지말고 한번에 하자!
	//Scanner sc = new Scanner(System.in); //(x) non-static필드로 사용하면 메인메서드에서 sc사용 안된다 왜그러지? static메서드이므로 non-static변수를 사용 할 수 없다.
	private static Scanner sc = new Scanner(System.in); // static, non-static 어디서든 사용할 수 있다.
	
	public void findAll() { // 매개변수 없는 findAll변수
		System.out.println(">>1. 고객 전체 조회<<");
		Customer[] cAll = dao.selectAll();
		for (int i = 0; i < cAll.length; i++) {
			Customer c = cAll[i];
			System.out.println(c.getId() + "," + c.getPwd() + ", " + c.getName());
		}
	}

	public void add() {
		System.out.println(">>2. 고객추가<<");
		//dao.insert(new Customer("id1", "p1", "n1"));
		//Scanner sc = new Scanner(System.in);
		System.out.print("아이디를 입력하세요:");
		String id = sc.nextLine(); // 사용자로부터 입력받는 아이디
		System.out.print("비밀번호를 입력하세요:");
		String pwd = sc.nextLine(); // 사용자로부터 입력받는 비번
		System.out.print("이름을 입력하세요:");
		String name = sc.nextLine(); // 사용자로부터 입력받는 이름
		dao.insert(new Customer("id", "pwd", "name")); // 고정된 id1이아닌 id..
		
	}
	public void findById() {
		System.out.println(">>3. 고객 ID로 조회<<");
		
		//사용자로부터 입력받은 아이디로 조회한다
		//Scanner sc = new Scanner(System.in);
		System.out.print("아이디를 입력하세요:");
		
		String id = sc.nextLine(); // 사용자로부터 입력받는 아이디
		
		Customer c4 = dao.selectById(id);
		if (c4 != null) { // 이곳이 출력
			System.out.println(c4.getId() + "," + c4.getPwd() + ", " + c4.getName());
		} else {
			System.out.println("ID가 존재하지 않습니다");
		}
		
	}
	public static void main(String[] args) { // main메서드는 반드시 static이어야 한다.
		CustomerMain customerMain = new CustomerMain(); //객체가 CustomerMain타입
		//Scanner sc = new Scanner(System.in);
		while(true) {//증가할일도 없고 몇번 반복할지도 결정 안되어 있으니 while반복문쓰자
			System.out.println("사용법: 1.고객 전체 조회, 2.고객 추가, 3.아이디로조회, 9.종료");
			System.out.print("작업을 선택하세요:");
			String menu = sc.nextLine(); // 입력받은 값은 문자열로 1->문자열 2->문자열 ..
			//System.out.println(menu);
			if("1".equals(menu)) {
				//findAll(); //(x) non-static메서드로 호출하려면 객체 생성 후 호출 해줘야한다. 또다른 방법은 static으로 만들어주면 된다.클래스이름.
				//new CustomerMain().findAll();	// 이 방법도 좋으나 반복수행 반복문을 써야하므로 스킾
				customerMain.findAll();
			} else if("2".equals(menu)) {
				customerMain.add();
			} else if("3".equals(menu)) {
				customerMain.findById();
			} else if("9".equals(menu)) {
				return; // 메소드를 끝낸다는 의미. 반복문을 빠져나오는 break;를 써도 된다.
			} 
		}
	}
}
```

```java
package com.my.dao;

import com.my.vo.Customer;

public class CustomerDAO {
	private Customer[] customers; //고객저장소
	private int cnt=0;
	public CustomerDAO(int size){
		customers = new Customer[size];
	}
	public  Customer[] selectAll() {
		//return null;
		//return customers;
		
		//저장소의  고객들이 저장된 새로운 배열생성 
		Customer[] cArr = new Customer[cnt];
		
		//저장소의 고객들을 새로운 배열에 복붙
		//for(int i=0; i<cArr.length; i++) {
		//	cArr[i] = customers[i];
		//}
		System.arraycopy(customers, 0, cArr, 0, cnt);
		return cArr;
	}
	public void  insert(Customer c) {
		//저장소에 저장된 고객수가 저장소크기와 같거나 클 경우
		if(cnt >= customers.length) {
			System.out.println("저장소가 꽉찼습니다. 현재 고객수는" + cnt+"입니다.");
			//return
		} else {
			this.customers[cnt] = c; //3번인덱스 사용하려면 에러발생
			cnt++;
			System.out.println("추가 성공");
		}
	}
	public Customer selectById(String id) {
		/*
		 * 저장소의 고객ID값과 매개변수ID값이 같은경우
		 * 저장소의 고객객체를 반환
		 */
		for(int i=0; i<cnt; i++) {
			Customer c = customers[i];
			String cId = c.getId();
			//String cId = c.id;
			if(cId.equals(id)) {
				return c;			
			} //else{reurn null} (x)
		}
		return null;
	}
}
```

![day09%20a0e6f6a0172e4cb3995fec336fc6f45b/20201218_123042.jpg](day09%20a0e6f6a0172e4cb3995fec336fc6f45b/20201218_123042.jpg)

customerMain이라는 지역변수가 참조하고 있는 객체는 CustomerMain의 클래스 타입 객체

customerMain은 지역변수 CustmerMain객체는 힙 영역

 CustmomerMain들어가보면 CustmomerMain에는 dao 멤버필드를 갖게 된다. 참조형으로 null로 자동 초기화 각 대입하면서 

CustomerDAO타입 객체가 만들어지고 안에는 customers 초기값 null을 가지고 있다. 또한 cnt 초기값 0값이 있고 생성자 호출하는데 size크기 만큼 배열 객체가 생성..3인 전달 됐으니 3개짜리 커스터머 타입으로 그러나 초기값은 null값으로. customerDAO생성자 끝나고 dao멤버 변수에 대입이 된다. 그리고 이 작업도 끝나면 멤버변수가 초기화.

.insert를 할때에는 항상 확인 필요 저장소에 적어도 고객수가 몇명인가 확인 해봐야한다

사용자에게 메시지를 보여줘야 한다.

---

public, private → 접근 제어 용 수식어

static → 객체 없이 사용할 수 있는 수식어

그러므로 static과 public이 바뀌어도 된다. public static = static public , private static = static private

**comment vs annotation**

comment는 컴파일이나 실행 시에 전혀 영향x

annotation는 컴파일이나 실행 시에 영향을 미치는 주석

```java
int[] intArray = null;
intArray[0] = 10;
```

NullPointException 발생! → intArray는 배열 타입 변수이므로 참조 타입 변수이다 그래서 null로 초기화가 가능하다. 이 상태에서 intArray[0]에 10을 저장하려고 하면 에러 발생. 이유는 intArray변수가 참조하는 배열 객체가 없기 때문이다.

```java
int []inArray = new int[3];

//intArray[-1] = 10;

//intArray[3]=10
```

 ArrayIndexOutOfBindException 발생!

```java
p sv main(~~~) {

int []intArray; //지역변수로 초기화x

intArray[0]=10;

}
```

컴파일 에러 → intArray는 지역변수 이므로 지역변수는 자동 초기화x. 그래서 어떻게 사용 할수 있니? 변수가 초기화 되지 않았다 하는 오류

---

![day09%20a0e6f6a0172e4cb3995fec336fc6f45b/20201218_152537.jpg](day09%20a0e6f6a0172e4cb3995fec336fc6f45b/20201218_152537.jpg)

name = " "; → 이것도 문자열이다.

참조형인 배열은 new를 썼는데, String만큼은 객체를 생성하지 않고도 참조 할 수 있다.

값이 대입 되거나 연산을 할 때마다 새로운 객체가 만들어진다.

String name2의 오문정은 String name1 오문정과 같으므로 같이 참조

단, String name2 = new String("오문정")일 경우 새로운 객체를 생성하므로 내용이 같건 다르건 정보 값이 다르다.

연결 고리가 끊어진 객체는 가비지컬랙션 대상이 된다 → JVM역할중 가비지 역할이 있다. 그 역할은 우선순위가 낮다라는 것은 가비지컬랙션을 안 할 수도 있다. 쓸모없는 메모리가 JVM에 쌓이고 메모리가 과부하 되고 시스템이 죽어 버릴 수도 있다. 수행 시 메모리 수거해감

![day09%20a0e6f6a0172e4cb3995fec336fc6f45b/20201218_153808.jpg](day09%20a0e6f6a0172e4cb3995fec336fc6f45b/20201218_153808.jpg)

내용이 같은가 비교하려면 equals를 쓴다. 문자열 같은 경우 비교하는 경우가 훨씬 많다.

if(strVar3 == strVar4){} //false

if(strVar1 == strVar2){} //true

if(strVar3.equals(strVar4)){} //true 

if(strVar1.equals(strVar2)){} //true

---

Integer.parseInt() ⇒ 문자열을 int타입으로 변환

public static void main(String[] args)→자바의 메인 메서드는 스팩이 결정 되어있다 매개변수가 String[]타입이어야 한다. int배열로 선언하면 안된다.

---

for(int score **:** scores) {}

**향상된 for문**은 반복 실행을 하기 위해 카운터 변수와 증감식을 사용하지 않는다.

배열 및 컬렉션 항목의 개수만큼 반복하고, 자동으로 for문을 빠져나간다.

---

**객체**란 물리적으로 존재하거나 추상적으로 생각할 수 있는 것 중에서 자신의 속성을 가지고 있고 다른 것과 식별 가능한 것을 말한다.

**객체의 상호작용**은 객체 간의 메소드 호출을 의미하며 매개값과 리턴값을 통해서 데이터를 주고 받는다. 메시지 패싱(주고받는)방법 = 메소드

객체는 개별적으로 사용될 수 있지만, 대부분 다른 객체와 관계를 맺고 있다. 이 관계의 종류에는 **집합 관계, 사용 관계, 상속 관계**가 있다.

**객체지향** 프로그램의 특징으로는 캡슐화, 상속, 다형성을 들 수 있다.

클래스로부터 만들어진 객체를 해당 **클래스의 인스턴스**라고 한다.

자바에서의 **static은 객체와 무관하게 쓸 수 있다.** 

가끔 전체 프로그램에서 단 하나의 객체만 만들도록 보장해야 하는 경우가 있다. 단 하나만 생성된다고 해서 이 객체를 **싱글톤**이라고 한다.

싱글톤을 만들려면 클래스의 외부에서 new연산자로 생성자를 호출할 수 없도록 막아야 한다. 생성자를 호출한 만큼 객체가 생성되기 때문이다. 생성자를 외부에서 호출할 수 없도록 하려면 생성자 앞에 **private**접근 제한자를 붙여주면 된다.

```java
class A{ // 매개변수없는 default 생성자가 있는 것 이다.
	private static A sa = new A();  // static으로 선언되어 있는 변수sa가 heap영역의 A객체를 생성해서 참조 private외부 접근 못하도록
	private A() {}
	public static A getInstance() {// 매개변수 없고 A타입으로 리턴
		return sa;
	}
}
public class SingletoneTest {

	public static void main(String[] args) {
		//new A(); -> 이 작업은 compile error가 발생되게 하자  private예약어 붙이면 외부클래스에서 사용 못한다
		A a1 = A.getInstance(); 
		A a2 = A.getInstance();
		System.out.println(a1 == a2);
	}

}
```

![day09%20a0e6f6a0172e4cb3995fec336fc6f45b/20201218_165832.jpg](day09%20a0e6f6a0172e4cb3995fec336fc6f45b/20201218_165832.jpg)

sa라는 변수는 A클래스 타입 초기화가 자동 되는 값이 heap영역에 A타입의 객체 이 객체를 sa가 참조 되게 하는 것이다. 참조형 변수라면 stack에서도 class area에서도 참조가 될 수 있다.

a1, a2변수는 스택 영역에 들어가고 a1은 A.getInstance가 호출이 되어서 지금 사용 중인 sa변수를 그대로 리턴

a2도 A클래스 타입으로 되어있고 getInstance호출 되어서 sa값 반환 되어서 그대로 a2도 쓰인다. 같은 메모리를 참조
