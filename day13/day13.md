# day13

```java
import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOArray;
import com.my.dao.CustomerDAOFile;
import com.my.vo.Customer;
public class CustomerDAOTest {

	public static void main(String[] args) {
		CustomerDAO dao;
		//dao = new CustomerDAO(); //컴파일 오류!! interface는 직접 new키워드로 객체생성 못한다. interfac는 모든 메소드가 다 추상메소드. 시스템의 접속 장치 역할로 사용자들에게 사용법을 제공
		dao = new CustomerDAOArray(2); // 저장소의 크기를 최대 2명만 저장 할 수 있도록 저장소를 만든다.
		//dao = new CustomerDAOFile(); //upcasting. 
		
//		//1.반복문 내부에서 예외처리 -> 프로그램이 죽지 않는것까지만 수행하는거지 프로그램이 죽지 않는다.
//		for(int i=0; i<10; i++) { // 2명을 저장 할 수 있는 저장소에 10명을 넣으려는 의도 ->2번 인덱스부터 넣으려는 순간 ArrayIndexOutOfBoundsException오류발생!
//			Customer c = new Customer("id"+i, "p"+i, "n"+i);
//			try {
//				dao.insert(c);//예외발생 가능 코드
//			}catch(ArrayIndexOutOfBoundsException e) {
//				//System.out.println("저장소가 꽉 찼습니다. 현재인원은 "+ dao.cnt+"입니다."); // dao.cnt(x) -> cnt변수는 private으로 선언 되어있으므로 접근조차 x
//				System.out.println("저장소가 꽉 찼습니다.");
//			}
//		}
		
		//2.반복문 외부에서 예외처리
		try {
			for(int i=0; i<10; i++) { // 2명을 저장 할 수 있는 저장소에 10명을 넣으려는 의도 ->2번 인덱스부터 넣으려는 순간 ArrayIndexOutOfBoundsException오류발생!
				Customer c = new Customer("id"+i, "p"+i, "n"+i);
				dao.insert(c);//예외발생 가능 코드
			}
		}catch(ArrayIndexOutOfBoundsException e) {
				//System.out.println("저장소가 꽉 찼습니다. 현재인원은 "+ dao.cnt+"입니다."); // dao.cnt(x) -> cnt변수는 private으로 선언 되어있으므로 접근조차 x
				System.out.println("저장소가 꽉 찼습니다.");
		}
	

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
		//customers[cnt++] = c; //cnt가 0이면 추가 성공   //증감연산자를 다른 연산자와 같이 쓰는거를 권장하지 않는다.
														//cnt가 1이면 추가성공
														//cnt가 2이면 ArrayIndexOufOfBoundsException발생! 
	}

	@Override
	public Customer[] selectAll() { // 저장되어 있는 모든 고객들만 반환
		System.out.println("CustomerDAOArray의 selectAll()");
		if(cnt == 0) { //cnt는 2
			return null;
		}
		Customer [] all = new Customer[cnt];
		System.arraycopy(customers, 0, all, 0, cnt); // 만약 위의 customers[cnt++]=c;의 잘못된 코드를 사용하였을 경우 cnt는 3이므로 ArrayIndexLOfBoundsException이 발생!
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

프로그램이 죽지 않도록 하려면 예외 처리를 해줘야 한다.

![day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_102936.jpg](day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_102936.jpg)

insert내부에서 에러발생 try~catch가 있으면 처리 할 건데 그 안쪽에 없죠 insert메소드 호출된 곳으로 ArrayIndexOufOfBoundsException객체가 떠 넘겨진다. 떠 넘겨 지기 전에 메소드를 마무리를 해야 하니깐 ++를 해야 해 하고 3이 되어 떠 넘겨지므로 문제가 되는 것이다. 그래서 안 좋은 코드이다.    =연산자부터 수행하기 전에 2번 인덱스가 없는 거 아니에요? 2번 인덱스가 없어서 ArrayIndex발생을 해도 ++를 하고 예외를 떠 넘겨지는 거다. 그러므로 두 줄 짜리 코드인 custoemrs[cnt] = c; 여기서  cnt++;를 만나지도 못하고 바로 ArrayIndexOfBoundsException이 떠 넘겨지므로 더 안전한 코드이다.

---

![day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_110200.jpg](day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_110200.jpg)

오른쪽 끝에는 본사이고 본사에는 본사 형db가 있겠죠? db는 보안 문제 때문에 본사가 아닌 다른 곳 에서는 접속을 못한다. 분당지사, 제주지사는 본사db직접 접속 불가 할 경우, 자료를 요구 한다거나 추가를 한다 거나 하고 싶다면 본사 쪽에 메소드를 만든다. a메소드를 만들고 분산 시스템으로 관리를 할 수 있다. 자바를 이용한 분산 시스템 기술을 RMI(Remote Method Invocation) 원격 메소드 호출 기술이라 한다. 분당지사에서 a();메소드를 마치 자기 것처럼 만드는 기술. 필요하다면 본사의 a를 호출 하면 된다. 제주 쪽 에서도 RMI형태로 제공해주세요~ 자기 제주 메소드 만들면서 분당의 메소드를 호출 받을 수 있는 거다. 분당지사에서 본사의 메소드를 호출하면 그 메소드가 제대로 호출 된 건지 알 길이 없다. 그래서 예외 처리를 하는데 a메서드의 호출하는 부분에 catch처리를 한다. 실패라는 메시지를 출력하게 하고 분당에서 a메소드를 호출 했을 때 정상 성공이 되지 않으면 그 즉시 catch구문에 와서 본사 콘솔이 출력 된다. 이건 잘못 설계한 코드이다. 분당에서 호출하면 분당에서 콘솔이 보여야 된다. 본사의 try~catch구문을 막고 a메서드 선언부에 throws로 예외를 떠 넘긴다. 그러면 분당 쪽에서 자기가 try~catch를 해줘야 한다. 실패를 출력하고 실패를 하였을 경우 출력 되는 콘솔은 분당 쪽에서 실행된다.

![day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_110255.jpg](day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_110255.jpg)

기록이 양쪽에서 실패 메시지를 다 보고 싶으면 본사 쪽의 try~catch를 살린다. 본사 쪽에서 실패 메시지를 볼 수 있고 그냥 끝나는게 아니라 다시 한번 throw(강제예외 발생) e;를 한다. e라는 예외 처리용 try~catch가 없죠? 그러면 e가 throws로 넘어가게 된다. 

![day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_113222.jpg](day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_113222.jpg)

분당지사가 b라는 메소드를 만들어 뒀는데 본사와 일도 해야 하고 자기 지사에 db가 있다고 하자. 자기와의 db와도 일 해야 한다. sqlException이 발생할 수 있으므로 try~catch구문으로 실패시 메시지 출력하게 했고 콘솔에 출력 되는 결과를 본사에서 보낸 실패인지 자기 쪽에서 보낸 실패인지 알 수 가없다.  메시지를 달리하는 방법밖에 없다. 메시지를 만들 때 본사 쪽에서는 throw enw SQLException으로 객체 생성하고 생성시 생성자 인자로 예외 메시지를 설정 할 수 있다. throw 강제 예외 발생한다. e라는거하고 new키워드 객체 생성하는거 차이는 e는 기존객체를 강제 new는 새로운객체를 만들어서 강제 예외 발생하는 코드이다. sqlexception객체는 새로만든 객체가 throws로 전달 되는 거다. 분당에서는 e.getMessage()로 하면 생성자로 만들어진 인자 본사실패 메시지가 분당 지사에서 출력 되게 만들 수 있다. getMessage메소드를 통해 상세 메시지를 가져 올 수 있다.

![day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_113257.jpg](day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_113257.jpg)

분당의 입장에서 try~catch가 두번이나 있으니 지저분하다. b메소드에서 a메소드를 호출하고 try~catch를 한번에 묶음 처리로 여러 다중catch를 할 수 있지 않을까? catch(SQLExeption e) 하나로 잡는다. 이렇게 try~catch하나로 묶음 처리로 할 수 있다. 근데 catch입장에서는 모호하다. 하나로 몰아가게 되면 메시지 구분은 할 수 있으나 catch입장에서 분당에서 catch가 된 건지 본사에서 catch가 된건지 구분할 수 가 없다. 사용자 임의로 클래스를 만들듯이 만들 수는 있으나 exception클래스를 만들 때는 Exception으로 부터 상속을 받고 아니면 SQLException을 상속 받는다. 

---

![day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled.png](day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled.png)

![day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%201.png](day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%201.png)

![day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%202.png](day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%202.png)

```java
package com.my.exception;

public class AddException extends Exception {

	public AddException() {
		super();
		
	}

	public AddException(String msg) {
		super(msg);//getMessage를 호출할때 조회가 될 수 있게
		
	}
	
}
```

```java
package com.my.dao;

import com.my.exception.AddException;
import com.my.vo.Customer;

public interface CustomerDAO {
	
	/**
	 * 저장소에 고객정보를 저장한다
	 * @param c 고객객체
	 * @throws AddException 아이디가 이미 존재하는 경우, 
	 * 						저장소가 꽉찬 경우 발생한다.
	 */
	void insert(Customer c) **throws AddException**; //insert메소드를 호출한 놈은 AddException해라
	
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

import com.my.exception.AddException;
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
	public void insert(Customer c) **throws AddException**{ // **자식에서도 throws 해줘야한다.**
		System.out.println("CustomerDAOArray의 insert()");
		//아이디 중복확인
		Customer c1 = selectById(c.getId());
		if(c1 == null) { //id에 해당하는 고객이 없을 경우
			try {
			customers[cnt] = c;
			}catch(ArrayIndexOutOfBoundsException e) {
				**throw new AddException**("저장소가 꽉 찼습니다");
			}
			cnt ++;
		}else { //이미 저장소에 있을경우
			**throw new AddException**("이미 존재하는 아이디 입니다.");
		}
		//customers[cnt++] = c; //cnt가 0이면 추가 성공   //증감연산자를 다른 연산자와 같이 쓰는거를 권장하지 않는다.
								//cnt가 1이면 추가성공
								//cnt가 2이면 ArrayIndexOufOfBoundsException발생! 
	}

	@Override
	public Customer[] selectAll() { // 저장되어 있는 모든 고객들만 반환
		System.out.println("CustomerDAOArray의 selectAll()");
		if(cnt == 0) { //cnt는 2
			return null;
		}
		Customer [] all = new Customer[cnt];
		System.arraycopy(customers, 0, all, 0, cnt); // 만약 위의 customers[cnt++]=c;의 잘못된 코드를 사용하였을 경우 cnt는 3이므로 ArrayIndexLOfBoundsException이 발생!
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

```java
import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOArray;
import com.my.dao.CustomerDAOFile;
import com.my.exception.AddException;
import com.my.vo.Customer;
public class CustomerDAOTest {

	public static void main(String[] args) {
		CustomerDAO dao;
		//dao = new CustomerDAO(); //컴파일 오류!! interface는 직접 new키워드로 객체생성 못한다. interfac는 모든 메소드가 다 추상메소드. 시스템의 접속 장치 역할로 사용자들에게 사용법을 제공
		dao = new CustomerDAOArray(2); // 저장소의 크기를 최대 2명만 저장 할 수 있도록 저장소를 만든다.
		//dao = new CustomerDAOFile(); //upcasting. 
		
//		//1.반복문 내부에서 예외처리 -> 프로그램이 죽지 않는것까지만 수행하는거지 프로그램이 죽지 않는다.
//		for(int i=0; i<10; i++) { // 2명을 저장 할 수 있는 저장소에 10명을 넣으려는 의도 ->2번 인덱스부터 넣으려는 순간 ArrayIndexOutOfBoundsException오류발생!
//			Customer c = new Customer("id"+i, "p"+i, "n"+i);
//			try {
//				dao.insert(c);//예외발생 가능 코드
//			}catch(ArrayIndexOutOfBoundsException e) {
//				//System.out.println("저장소가 꽉 찼습니다. 현재인원은 "+ dao.cnt+"입니다."); // dao.cnt(x) -> cnt변수는 private으로 선언 되어있으므로 접근조차 x
//				System.out.println("저장소가 꽉 찼습니다.");
//			}
//		}
		
		//2.반복문 외부에서 예외처리
		try {
			**//dao.insert(new Customer("id0", "paaa"));** // 이 문장을 쓰면 중복된 값이 들어가므로 이미 존재하는 아이디가 출력된다
			for(int i=0; i<10; i++) { // 2명을 저장 할 수 있는 저장소에 10명을 넣으려는 의도 ->2번 인덱스부터 넣으려는 순간 ArrayIndexOutOfBoundsException오류발생!
				Customer c = new Customer("id"+i, "p"+i, "n"+i);
				dao.insert(c);//예외발생 가능 코드
			}
		}catch(**AddException** e) {
				//System.out.println("저장소가 꽉 찼습니다. 현재인원은 "+ dao.cnt+"입니다."); // dao.cnt(x) -> cnt변수는 private으로 선언 되어있으므로 접근조차 x
				System.out.println(e.getMessage());
		}
	

		Customer[] all = dao.selectAll();
		if(all != null) {
			for(Customer c: all) { //향상된 for문
				System.out.println(c.getId() + ":" + c.getName());
			}
		}
		
	}

}
```

AddException은 추가 시 발생되는 예외를 알리기 위해

클래스기 때문에 상속 계층을 만들 수 있다.

```java
package com.my.exception;

public class DuplicateException extends AddException { //중복시 발생되는 Exception 추가하는 동안에 발생하는 문제가 중복 되어서 예외가 발생.. 
	  												  //생성자는 아무리 자식이라도 상속이 안된다. 객채 생성시 인자가 필요 하다면 생성자를 만들어줘야한다.

	public DuplicateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DuplicateException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	} 
	
	
}
```

```java
package com.my.exception;

public class FindException extends Exception {

	public FindException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FindException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
```

```java
package com.my.dao;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Customer;

public interface CustomerDAO {
	
	/**
	 * 저장소에 고객정보를 저장한다
	 * @param c 고객객체
	 * @throws AddException 아이디가 이미 존재하는 경우, 
	 * 						저장소가 꽉찬 경우 발생한다.
	 */
	void insert(Customer c) throws AddException;
	
	/**
	 * 저장소의 모든 고객을 반환한다
	 * @return 저장소의 모든고객. 고객이 한명도 없으면 null을 반환한다.
	 * @throws FindException 고객이 한명이 없으면 발생한다.
	 */
	abstract Customer[] selectAll() throws FindException;
	
	/**
	 * 저상소의 아이디에 해당 고객을 반환한다.
	 * @param id 아이디
	 * @return 고객객체.             //아이디에 해당하는 고객이 없으면 null을 반환하면 메소드를 호출하는 쪽에서 nullpointException 빠질 가능성이 있으므로 뺀다. 문제가 발생하면 throws로 떠 넘기기 할거다.
	 * @throws FindException 아이디에 해당하는 고객이 없으면 예외 발생한다.
	 */
	public abstract Customer selectById(String id) throws FindException;
	Customer update(Customer c);
	Customer delete(String id);
}
```

```java
package com.my.dao;

import com.my.exception.AddException;
import com.my.exception.DuplicateException;
import com.my.exception.FindException;
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
/*
caller() {
	try{
	  insert(customer); // throws AddException 이기 때문
	} catch (AddException e) {
		...
	}
}
*/

	@Override       
	public void insert(Customer c) throws AddException{ // 자식에서도 throws 해줘야한다.
		System.out.println("CustomerDAOArray의 insert()");
		
		//아이디 중복확인
		try { // 선언부가 바뀌었으므로 정상 고객을 찾았다 정상 리턴이 됐다라는 뜻 에외가 발생하면 못찾았다라는 뜻.
			Customer c1 = selectById(c.getId()); 
			
			throw new DuplicateException("이미 존재하는 아이디입니다."); //이거는 AddException e = new DuplicateException("이미 존재하는 아이디입니다");
																																 //       throw e; 랑 같은 코드이다.
																																 //상속개념: 자식 DuplicateExeption은 부모의 AddException이 하는거랑 같다. 그러므로 에러발생x.
		}catch(FindException fe){ //검색해서 없을때 발생
			try { 
				customers[cnt] = c; // 검색해서 없으니까 넣어준다.
			}catch(ArrayIndexOutOfBoundsException e) { // 배열이 초과될시 (런타임 익셉션, throws가 없어도 발생할 수 있음)
				throw new AddException("저장소가 꽉 찼습니다");
			}
			cnt ++;
		}
	}

	@Override
	public Customer[] selectAll() throws FindException{ // 저장되어 있는 모든 고객들만 반환
		System.out.println("CustomerDAOArray의 selectAll()");
		if(cnt == 0) { //cnt는 2
			//return null;(x)
			throw new FindException("고객이 한명도 없습니다.");
		}
		Customer [] all = new Customer[cnt];
		System.arraycopy(customers, 0, all, 0, cnt); // 만약 위의 customers[cnt++]=c;의 잘못된 코드를 사용하였을 경우 cnt는 3이므로 ArrayIndexLOfBoundsException이 발생!
		return all;
	}

	@Override
	public Customer selectById(String id) throws FindException{
		for(int i=0; i<cnt; i++) {
			Customer c = customers[i];
			String cId = c.getId();
			if(cId.equals(id)) {
				return c; //저장소 고객객체로 반환
			}
		}
		//return null; (x)
		throw new FindException("아이디에 해당하는 고객이 없습니다.");
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

```java
import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOArray;
import com.my.dao.CustomerDAOFile;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Customer;
public class CustomerDAOTest {

	public static void main(String[] args) {
		CustomerDAO dao;
		//dao = new CustomerDAO(); //컴파일 오류!! interface는 직접 new키워드로 객체생성 못한다. interfac는 모든 메소드가 다 추상메소드. 시스템의 접속 장치 역할로 사용자들에게 사용법을 제공
		dao = new CustomerDAOArray(2); // 저장소의 크기를 최대 2명만 저장 할 수 있도록 저장소를 만든다.
		//dao = new CustomerDAOFile(); //upcasting. 
		
//		//1.반복문 내부에서 예외처리 -> 프로그램이 죽지 않는것까지만 수행하는거지 프로그램이 죽지 않는다.
//		for(int i=0; i<10; i++) { // 2명을 저장 할 수 있는 저장소에 10명을 넣으려는 의도 ->2번 인덱스부터 넣으려는 순간 ArrayIndexOutOfBoundsException오류발생!
//			Customer c = new Customer("id"+i, "p"+i, "n"+i);
//			try {
//				dao.insert(c);//예외발생 가능 코드
//			}catch(ArrayIndexOutOfBoundsException e) {
//				//System.out.println("저장소가 꽉 찼습니다. 현재인원은 "+ dao.cnt+"입니다."); // dao.cnt(x) -> cnt변수는 private으로 선언 되어있으므로 접근조차 x
//				System.out.println("저장소가 꽉 찼습니다.");
//			}
//		}
		
		//2.반복문 외부에서 예외처리
		try {
			dao.insert(new Customer("id0", "paaa")); // 이 문장을 쓰면 중복된 값이 들어가므로 이미 존재하는 아이디가 출력된다
			for(int i=0; i<10; i++) { // 2명을 저장 할 수 있는 저장소에 10명을 넣으려는 의도 ->2번 인덱스부터 넣으려는 순간 ArrayIndexOutOfBoundsException오류발생!
				Customer c = new Customer("id"+i, "p"+i, "n"+i);
				dao.insert(c);//예외발생 가능 코드
			}
		}catch(AddException e) {
				//System.out.println("저장소가 꽉 찼습니다. 현재인원은 "+ dao.cnt+"입니다."); // dao.cnt(x) -> cnt변수는 private으로 선언 되어있으므로 접근조차 x
				System.out.println(e.getMessage());
		}
	

		Customer[] all;
		try {
			all = dao.selectAll();
			for(Customer c: all) { //향상된 for문
				System.out.println(c.getId() + ":" + c.getName());
			}
		} catch(FindException e) {
			//e.printStackTrace(); // 이 메소드는  예외 내용을 추적해서 출력 해준다.
			System.out.println(e.getMessage());
		}
		
	}

}
```

---

![day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_141840.jpg](day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_141840.jpg)

hashCode : 객체 메모리 정보 값. →A,B가 갖고 있는 메소드가 아니라 Object의 영역에서 지원하는 메소드이다.

객체의 정보값이 출력 되는거지 메모리 번지수가 출력 되는거는 아니다. 객체를 구분해주는 정보 값이다. 서로 다른 객체면 서로 다른 객체의 메모리 정보 값.

hashCode : 객체의 정보값을 숫자의 형태로 반환

toString : 객체의 정보값을 문자열 형태로 반환→클래스이름 @ 16진수 문자열 값으로 반환한다는 의미

equals: Object의 equals 메소드는 ==와 같다.

![day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%203.png](day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%203.png)

![day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%204.png](day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%204.png)

![day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%205.png](day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%205.png)

![day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%206.png](day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%206.png)

```java
class A { //class A extends Object로 바뀜
	int num;
	A(int num){
		this.num = num;
	}
	public String toString() {
		return "A의 num은" + num + "입니다";
	}
	@Override
	public boolean equals(Object obj) {
		//현재객체의 num값과
		//매개변수로 전달된 객체의 num값이 같으면 true반환
		//그외는 false반환
		if(obj instanceof A) { //obj가 A타입의 객체 일때만
			A a = (A)obj; //downcasting
			//if(this.num == obj.num) { //컴파일(x) num변수를 사용하려는 obj자료형을 보면 object클래스인데 멤버필드로 하나도 없으므로 num이라는 변수가 없다
			if(this.num == a.num) { 
				return true;
			}
		}
		return false;
	}
	
}
class B {
	String subject;
	B(String subject ){
		this.subject = subject;
	}
	public String toString() {
		return "B의 subjet는" + subject + "입니다.";
	}
}
public class ObjectTest {
	public static void test(Object	p) { //a타입 upcasting b타입도 upcasting 되려면 Object. 가장 추상화 할 수 있는 일반 클래스는 java.lang패키지의 Object클래스이다.
		//System.out.println(p.toString());
		System.out.println(p); // 출력할때 참조변수의 toString메소드가 자동호출이 된다.  
	}
	public static void test(Object obj1, Object obj2) {
		System.out.println("equals test메소드:" + obj1.equals(obj2)); //a,b클래스 equals메소드가 정의 안되어있으니 이때 equals는 object의 equals로 호출된다. ==
//		System.out.println("obj1객체정보는" + obj1.toString()); 
//		System.out.println("obj2객체정보는" + obj2.toString());
		System.out.println("obj1객체정보는" + obj1); // 문자열+참조변수 -> 참조변수의 toString메소드가 자동호출 = obj1.toString()메소드 호출하는거랑 같다.
												 // 참조변수의 toString메소드가 자동 호출되는거다.
	}
	public static void main(String[] args) {
		B b1,b2;
		b1 = new B("java");
//		System.out.println(b1.toString()); //B클래스의 toString가 재정의됨. 출렬결과:  "B의 subject는 java입니다."
		test(b1); //메소드에 b1전달
		
		b2 = new B("C++");
//		System.out.println(b2.toString()); //"B의 subject는 C++입니다."
		test(b2);
		test(b1, b2); //b1과 b2 인자를 전달하는 경우. 출력: false
		B b3;
		b3 = b2;
		test(b2, b3); //출력: true -> 같은 객체를 참조하므로 true
		
		
		// a1이 참조하는 객체가 자기만의 고유한 변수 num이 선언되어있고 a2가 참조하는 객체도 자기의 고유한 num변수가 있다.
		// 객체가 달라도 같은 num값을 가지고 있으면 true로 반환 해보자. a클래스의 오버라이딩된 메소드를 갖다 써보자!
		A a1,a2;
		a1 = new A(10); //num값이 10으로 초기화된 객체
		a2 = new A(10); //num값이 10으로 초기화된 객체
//		System.out.println(a1.toString()); // Object클래스의 toString이 상속됨. 출력결과: A@XXXXXXXX
										   // A클래스의 toString이 재정의됨. 출렬결과: 출렬결과A의 num은 10입니다.
		test(a1);
		System.out.println(a1.equals(a2)); // Object클래스의 equals가 상속됨. ==과 같음. 결과는 false
		test(a1, a2); // 출력: false -> true // a1->A, a2->A : upcasting, 매개변수 obj a1 obj a2가 대입이 되서 쓰이게 되는데 A의 equals메소드가 이미 오버라이딩되어있다.
		test(a1, b1); // 출력: false 
		
		Object o1, o2;
		o1 = new Object();
		o2 = new Object();
		// o1이 참조하는 해쉬코드값과 o2가 참조하는 해쉬코드값은 다르다.
		System.out.println(o1.hashCode());
		System.out.println(o2.hashCode());
//		System.out.println(o1.toString());
		test(o1);
//		System.out.println(o2.toString());
		test(o2);
		System.out.println(o1 == o2);
		System.out.println((o1.equals(o2))); //서로 다른 메모리를 참조하고 있으니까 다르다.
	}

}
```

1. 두 개의 메소드를 각각
2. Object타입의 매개변수를 받는 메소드

![day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_172057.jpg](day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_172057.jpg)

![day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_154636.jpg](day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_154636.jpg)

a1이 test 메소드 Object obj1에 전달 a1이 참조하는 객체와 obj1이 참조하고 있는 객체는 같다. equals메소드에서 매개변수 obj가 있는데 역 추적해서 보면 test 메소드에 a2객체가 두 번째 매개변수로 전달이 되고 equals메소드의 인자로 쓰인 거죠. 매개변수로 쓰이는 obj2는 역 추적 해서 가면 a2와 같은 객체를 참조.

a1,obj1,this 다 같은 객체를 참조

a2,obj2,obj 다 같은 객체를 참조

![day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_183120.jpg](day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_183120.jpg)

a1이 Object타입의 obj1라는 매개변수에 전달,  b1은 Object타입의 obj2라는 매개변수에 전달이 되어 upcasting한다. obj1.equals(obj2)→ a1에 b1을 비교 그래서 A클래스에서 정의된 equals메소드로 가서 Object타입의 obj는 b1이 되는 것이고, if(obj instanceof A) → obj가 A타입의 객체 일때만이므로 b1이 A타입의 객체가 아니므로 false로 리턴된다. → 클래스exception발생!

출력 할 때 참조 변수의 toString메소드가 자동 호출이 된다.

![day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%207.png](day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%207.png)

![day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%208.png](day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%208.png)

![day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%209.png](day13%20178acf9c865a4f6bb1f05a7a4182b090/Untitled%209.png)

![day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_161336.jpg](day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_161336.jpg)

![day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_161214.jpg](day13%20178acf9c865a4f6bb1f05a7a4182b090/20201224_161214.jpg)

원래 객체의 a의 오버라이딩된 toString메소드가 호출된다.

---

Exception과 RuntimeException 위주로 알면 된다. throwable은 모든 Exception을 잡을 때

받는 사람 입장: throw를 하던지 catch를 하던지 해야 한다.

RuntimeException이면 throws를 안 해도 컴파일은 된다 왜? uncheckedException이 발생하여 컴파일시  예외 체크  안 해서 이다. 단 실행 시 예외 발생 할 경우 에러..  그래서 throws를 해주는게 좋다. 그리고 RuntimeException이 아니면 throws를 해라!

catch안하면 RuntimeExeption은 부모로 간다.

throws: 메소드에 던질 수도 있다라는 떠 넘기는 의미. 받는 사람은 throws를 통해 또 던지거나 아님                                                  try~catch사용

throw: 던질 때 쓰는거

ctrl+o→메소드 보기 단축키