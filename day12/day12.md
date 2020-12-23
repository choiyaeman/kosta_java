# day12

interface는 system에 접속할 수 있는 접속 장치를 말한다.

![day12%20773983106e4a40ac906d99866756ba48/20201223_114826.jpg](day12%20773983106e4a40ac906d99866756ba48/20201223_114826.jpg)

왼쪽 java개발자 오른쪽 DB

자바 개발자가 **1)DB**에 접속하고 DB에서부터 자료를 검색해야 하므로 **2)검색 문법**을 써서 검색을 하고 마지막으로 **3)DB접속 해제**한다.

mysql 접속을 하자고 할 때 하고 오라클 db에 접속하는 방법은 다를 수밖에 없다. 각 db사들은 자바 개발자들이 각자의 db에 접속해서 검색 할 수 있도록 라이브러리를 제공되어야 한다. 이것을 바로 JDBC드라이버 이라 한다. 압축 파일 형태로 제공이 되는데 클래스 즉 라이브러리들의 묶음이라 보면 된다.

자바 개발자가 db접속하고 검색 문법하고 DB접속 해제 하기 전에 **0)JDBC드라이버를 설치** 해야 한다.

몇 천 개 몇 만 개의 db에 연결되는 드라이버가 다 들어 있다고 할 때, 프로젝트가 완성된 상태에 DB가 바뀐다면? 자바에서는 플랫폼의 독립성을 띈다는 의미로 해석해서 관계없이 쓸 수 있도록 중간 라이브러리를 제공한다. 직접 mysql라이브러리를 쓰는게 아니다. 중간에 java.sql이 끼어들고 Statement 인터페이스가 있고 db에 접속할 수 있는 접속 장치 역할을 한다. statemnt인터페이스 대표 메소드로 executeQuery가 있다. interface갖는 모든 메소드는 추상 메소드이므로 메소드 선언부만 있고 구현부가 따로없다. db사 쪽에서는 O클래스, M클래스를 java.sql패키지에 있는 특정 인터페이스를 구현 해야 하는 제약이 맺어있다. executeQuery메소드는 O클래스 M클래스에 오버라이딩 해야한다. 그 안에는 오라클있는 데이터 검색해서 반환하고, mysql에 저장되어 있는 데이터를 검색해서 반환하는 코드를 만들어줘야 한다. 자바 개발자가 구체화된 O타입 객체, M타입 객체를 사용 하는 게 아니고(O o =new O();(x) M m =new M m();(x)) Staetement인터페이스 타입을 사용한다(Staetement stmt = ~~~~; ). 인터페이스를 제공함으로써 클래스 템플릿의 역할을 해주고 사용자들과 인터페이스 관계를 보면 직접 구체화된 클래스 타입을 사용 하는게 아니라 interface를 보고 클래스를 작성하고 쓴다. 인터페이스가 없으면 O타입 객체 생성한 다음 O메소드로 호출 해줘야 한다. 제공자 소스코드가 바뀜에 의해서 사용자 소스 코드도 바뀜으로 interface가 제공되는 것 이다. java.sql interface는 db쪽에서 제공이 되어야 되고 오버라이딩이 되어야 한다. 메소드 이름은 executeQuery로 똑같으므로 검색할 수 있는 코드는 바뀌지 않는다. 

oracle시스템 mysql시스템 접속 장치로써 interface를. interface 사용법을 switch라 보면 된다.

인터페이스가 없으면 사용자 소스코드 개발자들은 제공자 소스코드가 만들어질 때까지 펑펑 논닼ㅋ 그러므로 개발 속도가 떨어질 수 밖에 없다. 그러나 인터페이스가 있으면 제공자 소스코드를 만들면서 사용자 소스코드도 Stament~~하고 stmt.executeQuery메소드 호출하고 같이 병행 되어 개발 속도가 빨라진다.

![day12%20773983106e4a40ac906d99866756ba48/Untitled.png](day12%20773983106e4a40ac906d99866756ba48/Untitled.png)

인터페이스가 갖는 모든 메소드는 추상 메소드이다.

![day12%20773983106e4a40ac906d99866756ba48/Untitled%201.png](day12%20773983106e4a40ac906d99866756ba48/Untitled%201.png)

파라미터는 db에 쓰는 sql구문에 전달하고 주어진 쿼리에 담은 처리된 데이터를 포함하고 있다. 주어진 쿼리에 대한 결과 데이터를 포함한 resultset객체를 반환 한다.

---

![day12%20773983106e4a40ac906d99866756ba48/20201223_123157.jpg](day12%20773983106e4a40ac906d99866756ba48/20201223_123157.jpg)

인터페이스의 이름으로 CustomerDAO를 만들고 인터페이스에 메소드(추상 메소드)의 선언부만 갖도록 설계한다. 인터페이스로부터 구현된 하위 클래스를 만든다. implements 예약어를 써서 구현한다. CustomerDAOArray, CustomerDAOFile(처리 클래스), CustomerDAOOracle, CustomerDAOMySQL → 상속 계층으로 interface를 구현. uml 표기법으로는 인터페이스와 클래스 간의 구현은 실선으로 표시한다. <<>>→interface를 의미하는구나라고 이해하면 된다.

![day12%20773983106e4a40ac906d99866756ba48/Untitled%202.png](day12%20773983106e4a40ac906d99866756ba48/Untitled%202.png)

```java
package com.my.dao;

import com.my.vo.Customer;

public interface CustomerDAO {
	
	/**
	 * 저장소에 고객정보를 저장한다
	 * @param c 고객객체
	 */
	void insert(Customer c); //모든 메소드는 public abstract이 생략됨. interface가 갖고 있는 메소드는 모두 추상메소드이므로 선언부만 선언.
	
	/**
	 * 저장소의 모든 고객을 반환한다
	 * @return 저장소의 모든고객. 고객이 한명도 없으면 null을 반환한다.
	 */
	abstract Customer[] selectAll();
	
	/**
	 * 저상소의 아이디에 해당 고객을 반환한다.
	 * @param id 아이디
	 * @return 고객객체. 아이디에 해당하는 고객이 없으면 null을 반환한다.
	 */
	public abstract Customer selectById(String id);
	Customer update(Customer c);
	Customer delete(String id);
}
```

```java
package com.my.dao;

import com.my.vo.Customer;

public class CustomerDAOArray implements CustomerDAO{
	private Customer[] customers;
	private int cnt;
	public CustomerDAOArray() {
		customers = new Customer[10];
	}
	public CustomerDAOArray(int size) {
		customers = new Customer[size];
	}
	@Override
	public void insert(Customer c) {
		System.out.println("CustomerDAOArray의 insert()");
		customers[cnt] = c;
		cnt++;
		//customers[cnt++] = c; //증감연산자를 다른 연산자와 같이 쓰는거를 권장하지 않는다.
	}

	@Override
	public Customer[] selectAll() { // 저장되어 있는 모든 고객들만 반환
		System.out.println("CustomerDAOArray의 selectAll()");
		if(cnt == 0) {
			return null;
		}
		Customer [] all = new Customer[cnt];
		System.arraycopy(customers, 0, all, 0, cnt); //복사를 시작할 원본, 원본의 몇번 인덱스부터 시작할것인지, 대상본, all의 0번인덱스부터 붙여넣기, cnt만큼 all.length로 해도 된다.
		return all;
	}

	@Override
	public Customer selectById(String id) {
		for(int i=0; i<cnt; i++) {
			Customer c = customers[i];
			String cId = c.getId();
			if(cId.equals(id)) {
				return c; //저장소 고객객체로 반환
			}
		}
		return null;
	}

	@Override
	public Customer update(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer delete(String id) {
		// TODO Auto-generated method stub
		return null;
	} // 추상메소드는 반드시 재정의해줘야한다
	

}
```

x체크박스눌러서 add unimplements체크

```java
import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOArray;
import com.my.dao.CustomerDAOFile;
import com.my.vo.Customer;
public class CustomerDAOTest {

	public static void main(String[] args) {
		CustomerDAO dao;
		//dao = new CustomerDAO(); //컴파일 오류!! interface는 직접 new키워드로 객체생성 못한다. interfac는 모든 메소드가 다 추상메소드. 시스템의 접속 장치 역할로 사용자들에게 사용법을 제공
		dao = new CustomerDAOArray(); //upcasting. 클래스가 부모 상위 인터페이스 타입으로도 업캐스팅이 될 수 있다. inteface메소드가 호출 되는게 아니라 하위 클래스의 CustoemrDAOArray의 오버라이딩 된 메소드가 호출된다. selectAll을 클릭하면 interface의 사용법을 볼 수 있다. 
		//dao = new CustomerDAOFile(); //upcasting. 
		
		Customer[] all = dao.selectAll();
		if(all != null) {
			for(Customer c: all) { //향상된 for문
				System.out.println(c.getId() + ":" + c.getName());
			}
		}
		
	}

}
```

```java
package com.my.dao;

import com.my.vo.Customer;

public class CustomerDAOFile implements CustomerDAO {

	@Override
	public void insert(Customer c) {
		System.out.println("CustomerDAOFile의 insert()");
	}

	@Override
	public Customer[] selectAll() {
		System.out.println("CustomerDAOFile의 selectAll()");
		return null;
	}

	@Override
	public Customer selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer update(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
```

![day12%20773983106e4a40ac906d99866756ba48/Untitled%203.png](day12%20773983106e4a40ac906d99866756ba48/Untitled%203.png)

interface구현 방법 

---

```java
class Parent1{}

class Parent2{}

class Child extends Parent1, Parent2{} //(x)

=> extends예약어로부터 상속 받지 못한다. 자바는 단일 상속 기준이다.
```

```java
interface RemoteControl{void r();}

interface Searchable{void s();}

class Child implements RemoteControl, Searchable{ //(O)
	void r(){}
	void s(){}
}

=> 부모의 메소드를 항상 물려받을수는 없고 재정의를 해야한다.

Child c = new Child();
RemoteControl rc = c; //upcasting
Searchable s = c; //upcasting
rc.r() 
rc.s();//(x) 컴파일러는 메모리 실행 되기전 rc타입의 자료형인 RemoteControl에 가보니 s가 없으므로 오류
s.r(); //(x) 
s.s();
```

### 오류

- **컴파일 오류**
- **실행 오류**

    -Error : 개발자가 처리할 수 없는 오류(대표: 메모리 부족)

    -Exception : 개발자가 충분히 처리할 수 있는 오류(경미한 오류)

    ex) ArithmeticExeption [//](//0으로) 0으로 나누기 할 때

    ArrayIndexOutOfBoundsExeption  // 배열 범위를 벗어 났을 때

    NullPointException  // null값을 가지고 있는 상태에서 메모리를 찾아갈 때

![day12%20773983106e4a40ac906d99866756ba48/Untitled%204.png](day12%20773983106e4a40ac906d99866756ba48/Untitled%204.png)

![day12%20773983106e4a40ac906d99866756ba48/20201223_153211.jpg](day12%20773983106e4a40ac906d99866756ba48/20201223_153211.jpg)

Unchecked Exception은 스스로 체크 해줘야 한다.

```java
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionTest {
	public static void test(int a) {
		int result = 5/a;
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 입력하세요:");
		try {
			int num = sc.nextInt(); //0->ArithmeticException
															//a->InputMissMatchException
			if(num != 0) {
				test(num);	
			}
		}catch(InputMismatchException e) {
			System.out.println("숫자가 입력되지 않았습니다. 정확히 숫자로 입력하세요.");
		}
		
	}

}
```

컴파일이 통과되나 문제가 발생할 수 있는 위험한 코드이다. 실행 시에 예외가 발생할 수 있다. RuntimeException[Unchecked Exception]발생!

예외 해결 방법

1. 예외 발생하지 않도록 조건문(if)으로 예외를 피해가기
2. 예외 발생 후 처리 try~catch →예외 발생 가능 윗 부분에 적는다! 예외 발생 시 catch구문으로 이동