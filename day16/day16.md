# day16

```java
package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;

public interface CustomerDAO {
	//모든 메서드는 public abstract이 생략됨
	/**
	 * 저장소에 고객정보를 저장한다
	 * @param c 고객객체
	 * @throws AddException 아이디가 이미 존재하는 경우,
	 *                      저장소가 꽉찬경우 발생한다
	 *                      
	 */
	void insert(Customer c) throws AddException;
	
	/**
	 * 저장소의 모든고객을 반환한다
	 * @return 저장소의 모든고객
	 * @throws FindException 고객이 한명이 없으면 발생한다
	 */
	abstract List<Customer> selectAll() throws FindException;
	
	/**
	 * 저장소의 아이디에 해당고객을 반환한다
	 * @param id 아이디
	 * @return 고객객체
	 * @throws FindException 아이디에 해당고객이 없으면 발생한다
	 */
	public abstract Customer selectById(String id) 
			throws FindException;
	
	/**
	 * 고객 정보를 수정한다. 단, 아이디는 수정할 수 없다.
	 * @param c 변경될 내용이 담겨있는 고객객체
	 * @return	변경된 고객객체
	 * @throws  수정 실패시 예외 발생한다  
	 */
	Customer update(Customer c) throws ModifyException;
	
	/**
	 * 고객 정보를 삭제한다
	 * @param id 아이디
	 * @return 삭제 실패시 예외 발생한다.
	 */
	Customer delete(String id) throws RemoveException;
}
```

```java
package com.my.exception;

public class ModifyException extends Exception {

	public ModifyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ModifyException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
```

```java
package com.my.exception;

public class RemoveException extends Exception {

	public RemoveException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RemoveException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
```

```java
package com.my.dao;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
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
		if(customers.size() == 0) { //리스트는 크기가 가변적이므로 멤버변수가필요없어서 cnt선언X, 그러나 배열은 크기가 불변이어서 멤버변수가 필요하다.
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
	public Customer update(Customer c) throws ModifyException{
		//1. c의 id와 같은 값을 갖는 저장소에 저장된 고객을 찾는다
		//2. 1의 경우가 성공되면 1의 고객 정보를 c로 바꾼다
		//   고객정보를 반환한다
		//3. 1의 경우가 실패되면  ModifyException을 강제발생 시킨다.
		
		String cId = c.getId(); //c에 해당하는 id값을 찾아 cId에 대입
		try {
			Customer c1 = selectById(cId); // 저장소 고객찾기 // c1 == cId
			//c1 =c;
			if(!"".equals(c.getName())) { //if(c.getName()==null){} //이름이 빈 문자열인 경우에는 대입을 안하겠다
				c1.setName(c.getName()); // c에 가져온 이름을 c1에 설정하겠다
			}
			
			if(!"".equals(c.getPwd())) {
				c1.setPwd(c.getPwd());
			}
			return c1;
		} catch(FindException e) {
			throw new ModifyException(e.getMessage());
		}
	}

	@Override
	public Customer delete(String id) throws RemoveException{
// 방법1)
		int i=0;
		for(; i<customers.size(); i++) {
			Customer c1 = customers.get(i); //고객을 하나씩 찾아내고
			if(c1.getId().equals(id)) { //id에 해당고객을 저장소에서 찾으면
				customers.remove(i); //저장소의 index를 제거. 예를들어 2번방이 제거되면 3번방이 앞으로땡겨진다
				return c1; // c1을 제거한게 아니라 저장소의 index를 제거한거다. Customer 객체가 제거되는게 아니다. 예를들어 2번방만 제거된거고 고객 객체는 살아 있는거다.
			}
		}
		throw new RemoveException("삭제 실패!"); // 위의  return c1; 못만나면 id해당하는 고객을 못찾았고 삭제도 못했다는 의미
		
		
// 방법2)
//		try {
//			Customer c = selectById(id); // 고객찾아오기
//			int i=0;
//			for(; i<customers.size(); i++) {
//				Customer c1 = customers.get(i);
//				if(c == c1) { //고객이 해당하는 저장소의 위치찾기
//					customers.remove(i);
//					break;
//				}
//			}
//			if(i==customers.size()) { 
//				
//			} else {
//				return c;
//			}
//		} catch (FindException e) { // 삭제 실패시 강제예외 발생
//			throw new RemoveException(e.getMessage());
//		}

	}

}
```

```java
import java.util.List;
import java.util.Scanner;

import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOList;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
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
	
	public void modify() {
		System.out.println(">>4. 고객 정보 수정<<");
		System.out.print("아이디를 입력하세요:");
		String id = sc.nextLine();
		try {
			dao.selectById(id);
			System.out.print("비밀번호를 입력하세요. 수정 안하려면 enter누르세요:");
			String pwd = sc.nextLine(); //enter인 경우 ""이 됨.
			System.out.print("이름를 입력하세요. 수정 안하려면 enter누르세요:");
			String name = sc.nextLine();
			
			Customer c = new Customer(id, pwd, name); // Customer 객체를 생성
			Customer c1 = dao.update(c); // dao.update(id,pwd,name) (X) 
			System.out.println("수정 성공!" + c1.getId() + ", " + c1.getPwd() + ", " + c1.getName());
		} catch(FindException e) {
			System.out.println(e.getMessage());
		} catch (ModifyException e) {
			System.out.println(e.getMessage());
		}
	}
	public void remove() {
		System.out.println(">>5. 고객 정보 삭제<<");
		System.out.print("삭제할 아이디를 입력하세요:");
		String id = sc.nextLine();
		try {
			dao.delete(id); //delete메소드 호출
			System.out.println("삭제성공");
		} catch (RemoveException e) {
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
			}else if("4".equals(menu)) {
				customerMain.modify();
			}else if("5".equals(menu)) {
				customerMain.remove();
			}
			else if("9".equals(menu)) {				
				return;
			}
		}
	}
}
```

제공자용 클래스의 코드가 바뀌면 사용자의 클래스도 바뀌어야 한다.

발생 가능한 예외를 선언

오버라이딩

- 상속 관계의 메서드 이름같고 매개변수 같고 매개변수 이름은 달라도 된다 자료형과 개수 순서가 모두 같아야 한다. 반환형도 같아야 한다. 상위 메서드의 throws된 예외가 하위 메소드에 throws되어야 한다. 상위 인터페이스가 갖고 있는 메서드에 throws된 예외가 하위 메서드에 throws가 되어야 한다.

---

실습)

![1](https://user-images.githubusercontent.com/63957819/103459681-69a5d780-4d54-11eb-8de9-2c9fbaa457bd.jpg)

com.my.view.CustomerMain

1- 전체조회, 2-추가, 3-ID검색, 6-로그인, 9-종료

작업: 6

>>6.로그인<<

아이디 입력하세요: id1

비번 입력하세요: pwd1

로그인 성공

고객정보: 아이디는 id1, 비밀번호는 pwd1, 이름이 n1입니다

작업구분: 1-수정, 2-삭제, 9-로그아웃

작업: 1

>>수정<<

비밀번호. 수정 안하려면 enter누르세요:

이름. 수정 안하려면 enter누르세요:

수정성공

수정실패

 작업: 2

삭제성공

삭제실패

 작업: 9

로그아웃 되었습니다.

로그인 실패

→ CustomerMain클래스만 건드려라

```java
package com.my.view;
import java.util.List;
import java.util.Scanner;

import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOList;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
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
	
	public void modify() {
		System.out.println(">>4. 고객 정보 수정<<");
		System.out.print("아이디를 입력하세요:");
		String id = sc.nextLine();
		try {
			dao.selectById(id);
			modify(id);
		}catch(FindException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void remove() {
		System.out.println(">>5. 고객 정보 삭제<<");
		System.out.print("아이디를 입력하세요:");
		String id = sc.nextLine();
		remove(id);
	}
	//------로그인 후 수정,삭제인 경우 -----------
	/**
	 * 아이디에 해당하는 고객정보 수정
	 * @param id 아이디
	 */
	public void modify(String id) {
		System.out.println("비밀번호를 입력하세요.수정안하려면 enter누르세요:");
		String pwd = sc.nextLine(); //enter인 경우 ""이 됨
		System.out.println("이름을 입력하세요.수정안하려면 enter누르세요:");
		String name = sc.nextLine();
		
		Customer c = new Customer(id, pwd, name);
		try {
			Customer c1 = dao.update(c);
			System.out.println("수정 성공!"+ c1.getId() + ", " + c1.getPwd() + ", " + c1.getName());
		} catch (ModifyException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * 아이디에 해당하는 고객을 저장소에서 삭제
	 * @param id
	 */
	public void remove(String id) {
		try {
			dao.delete(id);
			System.out.println("삭제성공");
		} catch (RemoveException e) {
			System.out.println(e.getMessage());
		}
	}
	public void login() {
		System.out.println(">>6.로그인<<");
		System.out.print("아이디를 입력하세요:");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요:");
		String pwd = sc.nextLine();
		try {
			Customer c = dao.selectById(id);
			if(c.getPwd().equals(pwd)) {
				System.out.println("로그인 성공");	
				System.out.println("고객정보:아이디는" + c.getId()+
				", 비밀번호는" + c.getPwd() + ", 이름은" + c.getName()+"입니다");
				afterLogin(id);
			}else {
				System.out.println("로그인 실패");
			}
		}catch(FindException e) {
			System.out.println("로그인 실패");
		}		
	}
	/**
	 * 로그인 성공된 후 메뉴
	 * @param id 로그인된 아이디값
	 */
	private void afterLogin(String id) {
		System.out.println(">>작업구분:1-수정, 2-삭제, 9-로그아웃");
		switch(sc.nextLine()) {
		case "1":
			System.out.println(">>수정<<");
			modify(id);
			break;
		case "2":
			System.out.println(">>삭제<<");
			remove(id);
		case "9":
			System.out.println(">>로그아웃<<");
		}
	}
	static public  void main(String[] args) {
		CustomerMain customerMain = new CustomerMain();
		while(true) {
			System.out.print("사용법: 1.고객전체조회, 2.고객추가, 3.고객 ID로 조회,");// 4.고객수정, 5.고객삭제, 
			System.out.println("6.로그인,  9.종료");
			System.out.print("작업을 선택하세요:");
			String menu = sc.nextLine();
			//System.out.println(menu);
			if("1".equals(menu)) {
				customerMain.findAll();
			}else if("2".equals(menu)) {
				customerMain.add();
			}else if("3".equals(menu)) {
				customerMain.findById();
			}else if("6".equals(menu)) {
				customerMain.login();				
			}else if("9".equals(menu)) {				
				return;
			}
		}
	}
}
```

---

![2](https://user-images.githubusercontent.com/63957819/103459683-6a3e6e00-4d54-11eb-8c09-944117e9a27d.jpg)

메인 클래스에서 하는 일의 종류가 화면에 보여줘야 하는 일도 해야 하고 키보드 값을 입력을 받아서 dao메소드 호출도 해야 하고 호출해서 가져온 결과 값을if문으로비교해야하고비교결과값을 성공인지 실패인지 보여줘야 한다 너무 많다. 

일 처리를 쪼개보자 주로 시스템을 구축할 때에는 VIEW Control Service DAO  dao가 실제 저장소와 일을 하는 거다. 시스템 안쪽에 들어가면 저장소가 있을 거고 저장소와 일을 하는 것이 dao이다. 

VIEW는 화면에 보여주는 역할을 한다. 사용자에게 보여줄 내용을 출력, 사용자로부터 값을 입력 받는 역할을 한다. dao라는 실제 백앤드에 있는 db 여려 형태의 저장소와 일을 한다.

메인 클래스에서는 VIEW역할만 해주자 Control에는 dao메소드를 직접 사용 하는게 아니라 service계층을 하나 둘 거다 . 클래스를 세분화 시킬 거다. 

메인 메소드를 가지고 있는 콘솔용  화면 단만 각각 변경하고 안쪽에 control,service,dao 코드를 새로 만들지 않고 작성할 수 있도록 

화면에 보여줘야 하는 정보와 사용자 정보 입력 받는 작업은 view에 있는 게 맞다

dao메소드 호출하는 작업들은 view가 하는 역할이 아니다.

다음에 할 일은Control을 만들건데  Serivce를 호출하고 service가 dao호출하게 만들 거다

dao에 selectById(String):Customer 갖고 있다. service에서 로그인 메소드는 login(String,String)있고 로그인 메소드에서 DAO메소드를 호출하게 할 거다. 즉 public void login(String id, String pwd){ Customer c = dao.selectById(id);if ~~~ . 

다음 서비스 단에 검색 용 메소드를 만들려 한다. findById(String):Customer. public Customer findById(String id){}  서비스 단에 만들어 놓은 두개를 selectBId메소드를 호출 할 거다. 즉 dao메서드를 서비스 단에서 여려 번 재사용할수있는거다.

DAO 하는 일은 추가,조회,수정,삭제를 한다. dao에 갖고 있어야 할 메서드 이름은 그 기능상(추가,조회, 수정,삭제)의 의미인 select, insert 등 메소드를 쓴다. longin이라는 기능이 dao에 들어가는 거는 좋은 설계가 아니다. 조회용 메소드를 재사용 하는게 좋은 설계이다. 

서비스의 메소드는 dao메서드를 사용하는데 여기저기 재 사용 된다. 서비스와 Conrol은 어찌 구분할까? Conrol layer는 용어 자체 상 제어이기 때문에 서비스의 메소드를 제어하는 거다. 

목적 자체가 layer쪼갠다. Control, Service를 재사용 할 수 있다!

```java
package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;

public interface CustomerDAO {
	//모든 메서드는 public abstract이 생략됨
	/**
	 * 저장소에 고객정보를 저장한다
	 * @param c 고객객체
	 * @throws AddException 아이디가 이미 존재하는 경우,
	 *                      저장소가 꽉찬경우 발생한다
	 *                      
	 */
	void insert(Customer c) throws AddException;
	
	/**
	 * 저장소의 모든고객을 반환한다
	 * @return 저장소의 모든고객
	 * @throws FindException 고객이 한명이 없으면 발생한다
	 */
	abstract List<Customer> selectAll() throws FindException;
	
	/**
	 * 저장소의 아이디에 해당고객을 반환한다
	 * @param id 아이디
	 * @return 고객객체
	 * @throws FindException 아이디에 해당고객이 없으면 발생한다
	 */
	public abstract Customer selectById(String id) 
			throws FindException;
	
	/**
	 * 고객정보를 수정한다. 단, 아이디는 수정할 수 없다
	 * @param c  변경될 내용이 담겨있는 고객객체
	 * @return   변경된 고객객체
	 * @throws  수정실패시 예외발생한다
	 */
	Customer update(Customer c) throws ModifyException;
	
	/**
	 * 고객정보를 삭제한다
	 * @param id 아이디
	 * @return 삭제실패시 예외발생한다
	 */
	Customer delete(String id) throws RemoveException;
}
```

```java
package com.my.service;

import java.util.List;

import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOList;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.share.CustomerShare;
import com.my.vo.Customer;
/**
서비스는 실제 비즈니스로직이 들어간다.
서비스에서는 VO, DAO, 다른서비스 모두 이용한다.
*/
public class CustomerService {
	private CustomerDAO dao = new CustomerDAOList(3);//^
	public List<Customer> findAll() throws FindException{
		List<Customer> cAll = dao.selectAll();//^	
		return cAll;
	}
	public void add(Customer c) throws AddException{
		dao.insert(c);//^
//		} catch (AddException e) {
//			System.out.println(e.getMessage());
//		}
	}
	public Customer findById(String id) throws FindException {
		 return dao.selectById(id);//^
	}
	public Customer modify(Customer c) throws ModifyException {
		// TODO Auto-generated method stub
		return dao.update(c);
	}
	public Customer remove(String id) throws RemoveException {
		return dao.delete(id);
	}
	public Customer login(String id, String pwd) throws FindException{
		try {
			Customer c = dao.selectById(id);//^
			if(c.getPwd().equals(pwd)) {//^				
				return c;
			}else {
	//			System.out.println("로그인 실패");
				throw new FindException("로그인  실패");
			}
		}catch(FindException e) {
	//		System.out.println("로그인 실패");
			throw new FindException("로그인  실패");
		}
	}
}
```

```java
package com.my.control;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.service.CustomerService;
import com.my.share.CustomerShare;
import com.my.view.CustomerSuccessView;
//import com.my.view.CustomerView;
import com.my.view.FailView;
import com.my.vo.Customer;

/**
컨트롤러는 서비스들을 이용해서 만든다.
비즈니스로직은 되도록 서비스에 몰아서 만들고, 컨트롤러는 서비스의 메소드를 호출해서 만든다.
VO는 사용가능하나, DAO는 서비스에서만 호출.
*/
public class CustomerController {
	private CustomerService service = 
			   new CustomerService();
	private CustomerSuccessView successView = 
				new CustomerSuccessView();
	private FailView failView =
			    new FailView();

	public void findAll() {
		try {
			List<Customer> cAll = 
					service.findAll();
			for(int i=0; i<cAll.size(); i++) {
				Customer c = cAll.get(i);
				successView.findView(c);
			}
		}catch(FindException e) {
			//System.out.println(e.getMessage());
			failView.printMessage(e.getMessage());
		}
	}
	public void add(Customer c) {
		try {
			service.add(c);
			successView.addView(c);
		} catch (AddException e) {
			failView.printMessage(e.getMessage());
		}
	}
	public void findById(String id) {
		try {
			Customer c = service.findById(id);
			successView.findView(c);
		} catch (FindException e) {
			failView.printMessage(e.getMessage());
		}
	}
	public void modify(Customer c) {
		try {
			Customer result = service.modify(c);
			successView.modifyView(result);
		} catch (ModifyException e) {
			failView.printMessage(e.getMessage());
		}
	}
	public void remove(String id) {
		try {
			Customer c = service.remove(id);
			successView.removeView(c);
		} catch (RemoveException e) {
			failView.printMessage(e.getMessage());
		}
	}
	public void login(String id, String pwd) {
		try {
			Customer c = service.login(id, pwd);
			//------------------------
			CustomerShare.loginedId = id;
			//------------------------
			successView.loginView(c);
		} catch (FindException e) {
			failView.printMessage(e.getMessage());
		}
	}
	public void logout() {
		CustomerShare.loginedId = null;
	}
}
```

```java
package com.my.view;
import com.my.vo.Customer;
public class CustomerSuccessView {
	
	public void findView(Customer c) {
		System.out.println("아이디는" + c.getId()+", 비밀번호는" + c.getPwd() + ", 이름은" + c.getName()+"입니다");
	}
	public void addView(Customer c) {
		System.out.println("가입성공");
		System.out.print("가입된 고객정보:");
		findView(c);
	}
	public void modifyView(Customer c) {
		System.out.println("수정 성공");
		System.out.print("수정된 고객정보:");
		findView(c);
	}
	public void removeView(Customer c) {
		System.out.println("삭제 성공");
		System.out.print("삭제된 고객정보:");
		findView(c);
	}
	public void loginView(Customer c) {
		System.out.println("로그인 성공");	
		System.out.print("로그인된 고객정보:");
		findView(c);
	}
}
```

```java
package com.my.view;

public class FailView {
	public void printMessage(String msg) {
		System.out.println(msg);
	}
}
```

```java
package com.my.share;

public class CustomerShare {
	public static String loginedId;  
}
```

```java
package com.my.view;
import java.util.Scanner;

import com.my.control.CustomerController;
import com.my.share.CustomerShare;
import com.my.vo.Customer;

public class CustomerMainView {
	//private CustomerDAO dao = new CustomerDAOList(3);//^
	//Scanner타입의 sc멤버변수 선언
	private CustomerController controller = new CustomerController();
	private static Scanner sc = new Scanner(System.in);
	
	public void findAll() {
		System.out.println(">>1. 고객 전체 조회<<");
		controller.findAll();
	}
	
	public void add() {
		System.out.println(">>2. 고객추가<<");
		System.out.print("아이디를 입력하세요:");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요:");
		String pwd = sc.nextLine();
		System.out.print("이름를 입력하세요:");
		String name = sc.nextLine();
		
//		try {
//			dao.insert(new Customer(id, pwd, name));//^
//		} catch (AddException e) {
//			System.out.println(e.getMessage());
//		}
		controller.add(new Customer(id, pwd, name));
	}
	public void findById() {
		System.out.println(">>3. 고객 ID로 조회<<");
		System.out.print("아이디를 입력하세요:");		
		String id = sc.nextLine();
//		Customer c4;
//		try {
//			c4 = dao.selectById(id);//^
//			System.out.println(c4.getId() + "," + c4.getPwd() + ", " + c4.getName());
//		} catch (FindException e) {
//			System.out.println(e.getMessage());//^
//		}		
		controller.findById(id);
	}	
	
//	public void modify() {
//		System.out.println(">>4. 고객 정보 수정<<");
//		System.out.print("아이디를 입력하세요:");
//		String id = sc.nextLine();
//		try {
//			dao.selectById(id);
//			modify(id);
//		}catch(FindException e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
//	public void remove() {
//		System.out.println(">>5. 고객 정보 삭제<<");
//		System.out.print("아이디를 입력하세요:");
//		String id = sc.nextLine();
//		remove(id);
//	}
	//------로그인 후 수정,삭제인 경우 -----------
	/**
	 * 아이디에 해당하는 고객정보 수정
	 * @param id 아이디
	 */
	public void modify(String id) {
		System.out.println("비밀번호를 입력하세요.수정안하려면 enter누르세요:");
		String pwd = sc.nextLine(); //enter인 경우 ""이 됨
		System.out.println("이름을 입력하세요.수정안하려면 enter누르세요:");
		String name = sc.nextLine();
		
		Customer c = new Customer(id, pwd, name);
		controller.modify(c);
//		try {
//			Customer c1 = dao.update(c);
//			System.out.println("수정 성공!"+ c1.getId() + ", " + c1.getPwd() + ", " + c1.getName());
//		} catch (ModifyException e) {
//			System.out.println(e.getMessage());
//		}
	}
	/**
	 * 아이디에 해당하는 고객을 저장소에서 삭제
	 * @param id
	 */
	public void remove(String id) {
		controller.remove(id);
//		try {
//			dao.delete(id);
//			System.out.println("삭제성공");
//		} catch (RemoveException e) {
//			System.out.println(e.getMessage());
//		}
	}
	public void login() {
		System.out.println(">>6.로그인<<");
		System.out.print("아이디를 입력하세요:");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요:");
		String pwd = sc.nextLine();
//		try {
//			Customer c = dao.selectById(id);//^
//			if(c.getPwd().equals(pwd)) {//^
//				System.out.println("로그인 성공");	
//				System.out.println("고객정보:아이디는" + c.getId()+
//				", 비밀번호는" + c.getPwd() + ", 이름은" + c.getName()+"입니다");
//				afterLogin(id);
//			}else {
//				System.out.println("로그인 실패");
//			}
//		}catch(FindException e) {
//			System.out.println("로그인 실패");
//		}		
		controller.login(id, pwd);
	}
	/**
	 * 로그인 성공된 후 메뉴
	 * @param id 로그인된 아이디값
	 */
	private void afterLogin(String id) {
		System.out.println(">>작업구분:1-수정, 2-삭제, 9-로그아웃");
		switch(sc.nextLine()) {
		case "1":
			System.out.println(">>수정<<");
			modify(id);
			break;
		case "2":
			System.out.println(">>삭제<<");
			remove(id);
			break;
		case "9":
			System.out.println(">>로그아웃<<");
			break;
		}
		logout();
	}
	public void logout() {
		controller.logout();
	}
	static public  void main(String[] args) {
		CustomerMainView customerMain = new CustomerMainView();
		while(true) {
			if(CustomerShare.loginedId == null) { // 로그인 상태 체크
					System.out.print("사용법: 1.고객전체조회, 2.고객추가, 3.고객 ID로 조회,");// 4.고객수정, 5.고객삭제, 
					System.out.println("6.로그인,  9.종료");
					System.out.print("작업을 선택하세요:");
					String menu = sc.nextLine();
					//System.out.println(menu);
					if("1".equals(menu)) {
						customerMain.findAll();
					}else if("2".equals(menu)) {
						customerMain.add();
					}else if("3".equals(menu)) {
						customerMain.findById();
					}else if("6".equals(menu)) {
						customerMain.login();				
					}else if("9".equals(menu)) {				
						return;
					}
			}else {
					customerMain.afterLogin(CustomerShare.loginedId);
			}
		}
	}
}
```

### 추가자료

---

```java
if(Prim타입 == null) <-- (x) Prim타입은 null이 안들어감. 항상 false, 컴파일오류.
if(어떤객체 == null) <-- (o)
if("1" == menu) <-- (x) "객체"끼리 비교는 .equals() 메소드를 사용 안할시 주소값이 같은지 비교하게됨.
if(1 == somePrimitiveTypeValue) <-- (o) Primitive타입끼리의 비교는 ==을 사용해도됨(int,float,double,byte,short,long)
```

![3](https://user-images.githubusercontent.com/63957819/103459684-6ad70480-4d54-11eb-9111-a3961af1e9ca.png)

프로그램 기준으로 데이터가 들어오는걸 입력, 프로그램 기준으로 데이터가 나가는 걸 출력

입력과 출력 양방향 못하고, 입력 따로 출력 따로 해줘야 한다. 데이터를 프로그램으로 입력 시키려는 또 다른 파이프로 연결 시킬 수 있다 출력도 또 다른 파이프를 끼울 수 있다. 자바 쪽에서 입출력 기본단위를 스트림이라 한다.  스트림이란 단 방향의 순차 데이터 흐름을 말한다.

입력 스트림의 시작 점을 자원 또는 리소스라 부르고, 출력 스트림의 도착 점을 목적지 또는 데스티네이션이라 부른다. 

![4](https://user-images.githubusercontent.com/63957819/103459685-6b6f9b00-4d54-11eb-9ff3-52d76b83f98c.png)

HW위에 OS가 탑재 되어있고 그 위에 여러 애플리케이션들이있다. 애플리케이션에서 JVM을 사용하고 있다. JVM위에서 사용되는 메모리 영역이라 보면 된다. 컴퓨터에 연결되어 있는 대표 보조장치로키보드가 있다. 키보드로부터 입력된 값이 사실상 OS와 연결되어 있긴 한데 JVM을 쓸 수 있도록 라이브러리를 제공하고 있다. java.api의 System.in이다. 또 대표 보조장치로 모니터가 있는데 JVM에서 쓸 수 있도록 라이브러리를 제공 해주고 있는데 그 라이브러리로는 System.out이다. 표준 출력 장치를 의미한다. system.in자료형은 inputStream 타입이다.

또 다른 대표 자원으로 파일 자원이 있다.(FileinputStream, FileReader) 둘의 차이점은? 자원과 연결하는 절차인데 FileinputStream은 자원을 읽을 때 바이트 단위로 읽어오고 FileReader은 읽을 때 문자 단위로 읽어온다. 한글 같은 경우는 글자 하나를 표현하기 위해서 2byte나 3byte를 확보 하잖아요 그래서 fileReader를 권장한다.

자바에는 open이라는 함수 따로 없다.

또 대표 자원으로 프로세스가 있다. 실행 중인 프로세스로부터 값을 읽어오기 위해 getInputStream메소드를 이용해서 반환 받는다.

대표 자원으로 소켓은 자원과 연결하려면 참조변수.getInputStream()을 써야 한다.

안녕하세요라고 전달이 되면 안이라는 한글 값이 전달 안이라는 전달된 값을 inputStream을 이용 

is.read()는 연결된 자원으로부터 첫 번째 바이트 값만 읽는다. 그러므로 한글은 2,3byte를 읽어 오기 때문에 안을 반환할 수 없다. 한글 값이 넘어오게 되면 스트림만 갖고 처리하기에는 한글 깨짐을 처리할 수 없다. 

![5](https://user-images.githubusercontent.com/63957819/103459686-6c083180-4d54-11eb-88a2-0fdf2541f72c.png)

자원과 최초로 만나는 스트림을 NodeStream이라 부르고 이 NodeStream을 가공하는 역할을 FilterStream이라 부른다. 자원과 만나는 첫 번째 파이프를 노드 스트림이라하고 노드 스트림을 가공해주는 역할을 FilterStream이라 한다.

InputStreamReader 두 가지 일을 다하는데 Reader의 자식으로써 문자 단위를 처리해주는 클래스이다. 바이트 단위의 스트림을 문자 단위의 스트림으로 변환 해준다. InputStream → Reader로 변환

Reader r = new inputStreamReader(is); 반환받은 객체를 Reader타입으로 upcasting

![6](https://user-images.githubusercontent.com/63957819/103459687-6c083180-4d54-11eb-94f5-f44a3ebe9cb9.png)

자원부터 고르고 그 자원과 만나는 노드스트림은 제약사항이 있다. 자원과 연결된 스트림을 이용해서  new키워드나 특정 메소드로 연결을 한다. read메소드가 호출 될 때 스트림의 종류에 따라서 읽는 방식이 다르다.  자원과 연결이 inputstream인 경우 filterstream으로 해줘야 한다.

![7](https://user-images.githubusercontent.com/63957819/103459688-6ca0c800-4d54-11eb-85b2-091ee65be4d2.png)

파일 자원으로부터 한글 깨짐 없이 하려면 Scanner사용하면 된다.

```java
package yaeman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class StreamMain {
	public static void main(String[] args) throws Exception {
		// 1. 바로 리더로 읽는법
		File f = new File("names.txt"); // File객체에 대한 수정,삭제,권한등등
		FileReader reader = new FileReader(f);
		BufferedReader readerLine = new BufferedReader(reader);
		while(readerLine.ready()) {
			System.out.println(readerLine.readLine());
		}
		
		// 2. 스트림으로 읽는법
		FileInputStream fis = new FileInputStream("names.txt");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader readerLine2 = new BufferedReader(isr);
		while(readerLine2.ready()) {
			System.out.println(readerLine2.readLine());
		}
		
		// 3. 파일 쓰는법 (Writer방법)
		FileWriter writer = new FileWriter("output.txt");
		writer.write("ABC\n");
		writer.write("DEF\n");
		writer.flush();
		writer.close();
		
		// 4. 파일 쓰는법 (Stream방법)
		FileOutputStream fos = new FileOutputStream("output2.txt");
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("ABC\n");
		bw.write("DEF\n");
		bw.flush();
		bw.close();
		
		
		// socket
		ServerSocket server = new ServerSocket(9999);
		Socket socket = server.accept();
		socket.getInputStream();
		socket.getOutputStream();
	}
}
```
