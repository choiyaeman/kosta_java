# day15

배열 - 동일 자료형의 데이터 묶음[크기고정]

int[] arr = new int[3];

arr[0] =10; arr[1]=20; arr[2]=30;

~~arr[3] = 40;~~ //ArrayIndexOfBoundException

arr = new int[10]; // 10개 짜리 방이 새로 만들어지는 거지 기존 3개의 방에서 더 늘어 나는 게 아니다. 

arr[3] = 40;

---

![1](https://user-images.githubusercontent.com/63957819/103459670-56930780-4d54-11eb-99af-c8f6fb7d7b35.jpg)

크기를 가변적으로 늘릴 수 있다.  java.util 패키지에서 제공

**컬렉션 프레임워크의 주요 인터페이스로는 List, Set, Map**이 있다.

ArrayList, Vector, LinkedList는 List 인터페이스를 구현한 클래스로, List 인터페이스로 사용 가능한 컬렉션이다.

HashSet, TreeSet은 Set 인터페이스를 구현한 클래스로 Set 인터페이스로 사용 가능한 컬렉션이다.

HashMap, Hashtable, TreeMap, Properties는 Map 인터페이스를 구현한 클래스로 Map 인터페이스로 사용 가능한 컬렉션이다.

extends 예약어는 상속, implements 구현한다는 예약어

- 클래스 extends 클래스
- 인터페이스 extedns 인터페이스
- 클래스 implements 인터페이스

Collection는 자료를 키 없이 저장한다. List계열은 인덱스를 이용해서 자료를 관리 

Set계열은 인덱스가 없다. 

 +메서드이름(매개변수이름:매개변수타입) 반환형

 +메서드이름(매개변수타입)반환형

**컬렉션 인터페이가 갖고 있는 메소드로 +add(Object), +size(), +remove(Object), +iterator()가 있다**.

 컬랙션 인터페이스가 갖고 있는 +add(Object) 메소드가 하위 클래스 쪽에서 오버라이딩이 되있을거라 예상한다.  Set은 큰 가방이라 생각하면 된다. 인덱스 없이 자료가 저장되므로 순서 없이 들어간다.

어떻게 재구성 하는가에 따라 자료구조가 달라진다. 

컬렉션 인터페이스가 갖고 있는+size() 메소드는 저장된 자료의 개수만 반환하는 메소드이다.

컬렉션 인터페이스가  +get(int) 메소드를 갖고있을까?  매개변수를 인덱스에 해당하는 요소를 반환하는 메소드인데 상위 인터페이스 컬렉션에 있을 수 없다.. List에는 인덱스가 있지만  Set에는 인덱스가 없다. 상위 인터페이는 공통점만 갖고 있어야 한다. 그러므로 List가 갖고 있는 메소드이다.

컬렉션 인터페이스가 갖고 있는 remove메소드는 하나이다. 바로 +remove(Object)이다. +remove(int) 상위 인터페이스인 컬렉션에 없다. 이것은 List가 갖고 있어야 할 메소드이다.

Set계열을 다시 보면, 아무 요소를 찾고 찾을 요소가 남았다면 찾아가고 순서 없이 찾아가는 방식이다. 다음 자료를 반복자를 이용해서 취하는 방식을 iterator() 한다. Set, List 계열도 반복자를 사용한다. List계열에서 반복자를 사용할 때 순서대로, Set은 해쉬코드 값 순서대로 기본적으로 찾는다. 해쉬코드 값이 큰 얘가 나중에 찾아진다. 

List는 중복 자료를 저장 할 수 있다. 반면 Set계열은 나중에 찾을 때 문제가 생기므로 중복된 자료를 저장 할 수 없다.

Hashtable, HashMap은 Map인터페이스로 부터 구현된 하위 클래스이다.

컬렉션이랑 Map 차이점은 무엇일까? 컬렉션에는 값만 저장하고, Map에는 키와 값을 저장한다.

자료를 저장할 때 키와 값을 같이 저장하는 목적은 빨리 검색하기 위함이다.

Map은 index가 없다. 키는 중복이 되면 안된다. 키를 중복 시키면 덮어 쓰게 되는 것이다.

**Map 인터페이스가 갖고 있는 대표 메소드로 + put(Obect, Object), +size(), +remove(Object), +get(Object)가 있다.**

Vector와 ArrayList의 차이점은 무엇일까? Vector는 자바1버전 부터 제공되는 api이다 ArrayList는 자바2버전 부터 제공되는 api인데 Vector는 스레드의 동기화가되어 있다. ArrayList에는 스레드의 동기화가 안되어 있다. 그러므로 일반적으로 ArrayList가 처리속도가 빠르다. 

Hashtable와 HashMap의 차이점은? Hashtable은 스레드 동기화 되어있다. HashMap은 스레드 동기화 안되어있다. 처리 속도는 스레드 동기화 안되어있는 HashMap이 더 빠르다.

TreeSet은 인덱스가 없이 정렬이 되는 api이다. TreeMap에도 정렬을 해준다.

![2](https://user-images.githubusercontent.com/63957819/103459672-57c43480-4d54-11eb-9bf0-a612bb0e3e32.png)

![3](https://user-images.githubusercontent.com/63957819/103459673-57c43480-4d54-11eb-902b-fcba3a4a9e10.png)

---

```java
package day15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JCF { //JavaCollectionFramework 자료구조형 라이브러리
	public static void test(Collection c) {
		c.add(new Object()); //매개변수 형태가 Object타입이므로 인자로 쓰일수 있다.
		c.add(new String("1")); //String타입은 Object타입으로 upcasting 할 수 있으므로
		c.add(new Integer(2)); //클래스 타입도 Object타입으로 upcasting가능
		c.add(3.0); //컴파일시 c.add(new Double(3.0));
		c.add(3.0); 
		
		System.out.println("자료수:" + c.size());
		System.out.println("자료들:" + c); //c.toString() 자동 호출됨. System.out.println하면 참조형 인자의 toString메소드가 자동 호출된다.
		Iterator iterator = c.iterator();
		while(iterator.hasNext()) {
			Object element = iterator.next();
			System.out.println("자료:" + element);
		}
	}
	
	public static void test(Map m) {
		m.put("one", new Object());
		m.put("two", new String("1"));
		m.put("three", new Integer(2));
		m.put("four",3.0);	//값중복
		m.put("five",3.0);	//값중복
		m.put("two", true); //키중복     key값이 two는 true로 덮어씌어진다.
		System.out.println("------ㅡ-Map--------");
		System.out.println("자료수:" + m.size());
		System.out.println("자료들:" + m); //key에 해당하는 value
		
		Set keys = m.keySet(); //키들을 찾고
		Iterator iterator = keys.iterator(); //키 반복수행
		while(iterator.hasNext()) {
			Object key = iterator.next(); //키를 하나씩 찾아온다
			Object element = m.get(key); //키를 알때는get메소를 쓰면된다.
			System.out.println("자료:키=" + key + ",값=" +element);
		}
	}
	public static void main(String[] args) {
		
		Collection c;
		//c = new Collection(); //(X) 인터페이스와 추상 클래스는 new키워드로 객체를 생성할 수 없는 타입니다.
		//c = new List(); //(X) List역시 인터페이스이므로
		c = new ArrayList(); //ArrayList 타입으로 upcasting
		test(c);
		
		c = new HashSet();
		test(c);
		
		Map m = new HashMap();
		test(m);
	}
}
```

List계열의 특성은 순차저장(0) 중복허용(0)
Set계열의 특성은 순차저장(x) 중복허용(x)
저장된 순서대로 누적 되지 않았다. 마구잡이로 저장이 된 건데 왜 출력 될 때는 1,2,3.0~ 자료들이 출력 되지? 
Hash코드값 순서대로 출력이 된거다. 해쉬코드값이 큰 얘가 나중에 찾아진다.

### 왜 제네릭을 사용해야 하는가?

제네릭 타입을 이용함으로써 잘못된 타입이 사용될 수 있는 문제를 컴파일 과정에서 제거할 수 있게 되었다. 제네릭은 클래스와 인터페이스, 그리고 메소드를 정의할 때 타입(type)을 파라미터(parameter)로 사용할 수 있도록 한다. 타입 파라미터는 코드 작성 시 구체적 타입으로 대체되어 다양한 코드를 생성하도록 해준다.

- **컴파일시 강한 타입 체크를 할 수 있다.**

실행 시 타입 에러가 나는 것보다는 컴파일 시에 미리 타입을 강하게 체크해서 에러를 사전에 방지하는 것이 좋다.

- **타입 변환(casting)을 제거한다.**

```java
List<String> list = new ArrayList<String>();
list.add("hello");
String str = list.get(0); //타입 변환을 하지 않는다.
```

---

```java
import java.util.List;
import java.util.Scanner;

import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOList;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Customer;

public class CustomerMain {
	private CustomerDAO dao = new CustomerDAOList(3);
	//Scanner타입의 sc멤버변수 선언
	private static Scanner sc = new Scanner(System.in);
	
	public void findAll() {
		System.out.println(">>1. 고객 전체 조회<<");
		try {
			List<Customer> cAll = dao.selectAll();			
			for(int i=0; i<cAll.size(); i++) {
				Customer c = cAll.get(i);
				System.out.println(c.getId() + "," + c.getPwd() + ", " + c.getName());
			}
		}catch(FindException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void add() {
		System.out.println(">>2. 고객추가<<");
		System.out.print("아이디를 입력하세요:");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요:");
		String pwd = sc.nextLine();
		System.out.print("이름를 입력하세요:");
		String name = sc.nextLine();
		try {
			dao.insert(new Customer(id, pwd, name));
		} catch (AddException e) {
			System.out.println(e.getMessage());
		}
	}
	public void findById() {
		System.out.println(">>3. 고객 ID로 조회<<");
		System.out.print("아이디를 입력하세요:");		
		String id = sc.nextLine();
		Customer c4;
		try {
			c4 = dao.selectById(id);
			System.out.println(c4.getId() + "," + c4.getPwd() + ", " + c4.getName());
		} catch (FindException e) {
			System.out.println(e.getMessage());
		}		
	}	
	static public  void main(String[] args) {
		CustomerMain customerMain = new CustomerMain();
		while(true) {
			System.out.println("사용법: 1.고객전체조회, 2.고객추가, 3.고객 ID로 조회, 4.고객수정, 5.고객삭제, 9.종료");
			System.out.print("작업을 선택하세요:");
			String menu = sc.nextLine();
			//System.out.println(menu);
			if("1".equals(menu)) {
				customerMain.findAll();
			}else if("2".equals(menu)) {
				customerMain.add();
			}else if("3".equals(menu)) {
				customerMain.findById();
			}else if("9".equals(menu)) {				
				return;
			}
		}
	}
}
```

```java
package com.my.dao;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Customer;

public class CustomerDAOList implements CustomerDAO {
	private List<Customer> customers; //고객저장소 // 순자적으로 저장하려는 의도로 List를 썼다.
	public CustomerDAOList() {
		customers = new ArrayList<Customer>(); //
	}
	public CustomerDAOList(int size) {
		customers = new ArrayList<Customer>(size);
	}
	
	@Override
	public void insert(Customer c) throws AddException {
		try {
			selectById(c.getId());
			throw new AddException("이미 존재하는 아이디입니다");
		} catch (FindException e) {
			//저장소에 추가하기
			customers.add(c);			
		}
	}

	@Override
	public List<Customer> selectAll() throws FindException {
		if(customers.size() == 0) { //리스트는 크기가 가변적이므로 멤버변수가 필요없어서 cnt선언X, 그러나 배열은 크기가 불변이어서 멤버변수가 필요하다.
			throw new FindException("고객이 한명도 없습니다");
		}
		return customers;		
	}
	@Override
	public Customer selectById(String id) throws FindException {
		for(int i=0; i<customers.size(); i++) {
			Customer c = customers.get(i); //list에는 get메서드가 있다.
			if(c.getId().equals(id)) {
				return c;
			}
		}
		throw new FindException("아이디에 해당하는 고객이 없습니다");
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

고객정보 수정/삭제 완성하시오.

```java
import java.util.List;
import java.util.Scanner;

import com.my.dao.AbstractDAO;
import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOList;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Customer;

public class CustomerMain {
	private CustomerDAO dao = new CustomerDAOList(3);
	//Scanner타입의 sc멤버변수 선언
	private static Scanner sc = new Scanner(System.in);
	
	public void findAll() {
		System.out.println(">>1. 고객 전체 조회<<");
		try {
			List<Customer> cAll = dao.selectAll();			
			for(int i=0; i<cAll.size(); i++) {
				Customer c = cAll.get(i);
				System.out.println(c.getId() + "," + c.getPwd() + ", " + c.getName());
			}
		}catch(FindException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void add() {
		System.out.println(">>2. 고객추가<<");
		System.out.print("아이디를 입력하세요:");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요:");
		String pwd = sc.nextLine();
		System.out.print("이름를 입력하세요:");
		String name = sc.nextLine();
		try {
			dao.insert(new Customer(id, pwd, name));
		} catch (AddException e) {
			System.out.println(e.getMessage());
		}
	}
	public void findById() {
		System.out.println(">>3. 고객 ID로 조회<<");
		System.out.print("아이디를 입력하세요:");		
		String id = sc.nextLine();
		Customer c4;
		try {
			c4 = dao.selectById(id);
			System.out.println(c4.getId() + "," + c4.getPwd() + ", " + c4.getName());
		} catch (FindException e) {
			System.out.println(e.getMessage());
		}	
		
	}
	
	public void updateCustomer() {
		System.out.println(">>4. 고객수정");
		System.out.print("아이디 입력: ");
		String id = sc.nextLine();
		System.out.print("변경할 비번 입력: ");
		String pwd = sc.nextLine();
		System.out.print("변경할 이름 입력: ");
		String name = sc.nextLine();
		Customer c = new Customer(id, pwd, name); // c는 Customer객체를 받은거다 즉 주소값을 받은거
		try {
			dao.update(c); //dao에 update메소드 호출해라
			//여기를 넘어가면 update가 성공한게 보장됨
			System.out.println(c.getId() + "," + c.getPwd() + "," + c.getName());
		} catch(FindException e) {
			//update가 실패했을 경우 여기로
			System.out.println(e.getMessage());
		}
	}
	public void deleteCustomer() {
		System.out.println(">>5. 고객삭제<<");
		System.out.print("아이디 입력:");
		String id = sc.nextLine();
		Customer c;
		try {
			c = dao.delete(id);
			System.out.println("삭제된 고객정보: " + c.getId() + "," + c.getPwd() + "," + c.getName());
		} catch(FindException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	static public  void main(String[] args) {
		CustomerMain customerMain = new CustomerMain();
		while(true) {
			System.out.println("사용법: 1.고객전체조회, 2.고객추가, 3.고객 ID로 조회, 4.고객수정, 5.고객삭제,  9.종료");
			System.out.print("작업을 선택하세요:");
			String menu = sc.nextLine();
			//System.out.println(menu);
			if("1".equals(menu)) {
				customerMain.findAll();
			}else if("2".equals(menu)) {
				customerMain.add();
			}else if("3".equals(menu)) {
				customerMain.findById();
			}else if("4".equals(menu)) {
				customerMain.updateCustomer();
			}else if("5".equals(menu)) {
				customerMain.deleteCustomer();
			}else if("9".equals(menu)) {				
				return;
			}
		}
	}
}
```

```java
package com.my.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Customer;

public class CustomerDAOList implements CustomerDAO {
	private List<Customer> customers; //고객저장소
	public CustomerDAOList() {
		customers = new ArrayList<Customer>(); //
	}
	public CustomerDAOList(int size) {
		customers = new ArrayList<Customer>(size);
	}
	
	@Override
	public void insert(Customer c) throws AddException {
		try {
			selectById(c.getId());
			throw new AddException("이미 존재하는 아이디입니다");
		} catch (FindException e) {
			//저장소에 추가하기
			customers.add(c);
			
		}
	}

	@Override
	public List<Customer> selectAll() throws FindException {
		if(customers.size() == 0) {
			throw new FindException("고객이 한명도 없습니다");
		}
		return customers;		
	}
	@Override
	public Customer selectById(String id) throws FindException {
		for(int i=0; i<customers.size(); i++) {
			Customer c = customers.get(i);
			if(c.getId().equals(id)) {
				return c;
			}
		}
		throw new FindException("아이디에 해당하는 고객이 없습니다");
	}

	@Override
	public Customer update(Customer 인자로받은Customer객체) throws FindException{
		if(인자로받은Customer객체 != null) {
			String id = 인자로받은Customer객체.getId();
			String name = 인자로받은Customer객체.getName();
			String pwd = 인자로받은Customer객체.getPwd();
			Customer 인자로받은Customer객체의id값에해당하는Customer객체 = selectById(id);
			//////
			인자로받은Customer객체의id값에해당하는Customer객체.setId(id);
			인자로받은Customer객체의id값에해당하는Customer객체.setName(name);
			인자로받은Customer객체의id값에해당하는Customer객체.setPwd(pwd);
		}
		
		return null;
	}

	@Override
	public Customer delete(String id) throws FindException {
		for(int i=0; i<customers.size(); i++) {
			Customer c = customers.get(i);
			if(c.getId().equals(id)) {
				customers.remove(c);
				return c; 
			}
			
		}
		throw new FindException("아이디에 해당하는 고객이 없어 삭제 불가능합니다.");
		
	}

}
```

---

추상클래스

- 클래스인데 반만 구현됨 —(확장 extends)—> 자식: 구현 안된 부분만 구현

인터페이스

- 클래스 설계도, 구현체 없음 —(구현 implements)—>자식: 인터페이스에 정의된 메소드 전부 구현

```java
package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Customer;

public abstract class AbstractDAO {
	
	protected String name;
	
	abstract String getName(); //구현안된 부분
	
	public void printName() { //구현된 부분
		System.out.println(getName());
	}

}
```

```java
package com.my.dao;

public class YaemanDAO extends AbstractDAO {

	@Override
	String getName() {
		this.name = "yaeman";
		return this.name;
	}

}
```

```java
package com.my.dao;

public class JaebooDAO extends AbstractDAO {

	@Override
	String getName() {
		this.name = "jaeboo";
		return this.name;
	}

}
```

```java
package com.my.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainPrinter {
	public static void main(String[] args) {
		List<AbstractDAO> list = new ArrayList<>();
		list.add(new YaemanDAO());
		list.add(new JaebooDAO());
		list.add(new AbstractDAO() {
			
			@Override
			String getName() {
				return "sunhee";
			}
		});
		
		for(AbstractDAO elem: list) {
			elem.printName();
		}
		
	}
}
```

![4](https://user-images.githubusercontent.com/63957819/103459674-585ccb00-4d54-11eb-97df-0f1dd3f1a72a.png)
