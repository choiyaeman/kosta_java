# day18

```java
public class CustomerController { 
	private CustomerService service = 
			   new CustomerService();
	private CustomerSuccessView successView = 
				new CustomerSuccessView();
	private FailView failView =
				new FailView();
}
```

CustmerController has a CustomerSerivce, has a CustomerSuccessView, has a FailView → CustomerController가 멤버 변수로 CustomerSerivce, CustomerSuccessView, FailView를 가지고 있다라는 의미

![day18%206382b20b8c654ce285eb6a1a78d2da03/20210104_094120.jpg](day18%206382b20b8c654ce285eb6a1a78d2da03/20210104_094120.jpg)

사원은 사람이다. 고객은 사람이다. 근데 계좌는 사람이다? 논리적인 성립이 안된다. 성립 되지 않는 관계는 상속 관계로 만들면 안된다. IS A 관계가 안됨. HAS A 관계로 풀면 된다. 그러면 멤버 변수로 Person 을 가지면 된다.

![day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled.png](day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled.png)

List 저장소를 삼아서 쓰고 있고 로그인 메뉴를 선택을 하게 되면 Contorller의 login 메소드에서 service의 login 메소드를 호출하고 dao의 selectById메소드 호출 그리고 Service login 메소드로 반환 후 가공한다. Id같으면 Controller에게 전달하고 같지 않다면 예외 발생을 한다.

```java
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
```

정상 처리 된 경우 id값을 CustomerShare.LoginedId 변수에 대입 된다. 클래스이름.으로 사용되고 있으니까 static 으로 선언 되어 있을 거다.

Controller가 하는 일은 일을 시키는 놈이다. 제어기의 역할만 한다.

실제 작업은 Service가 결정하고 실제 일 처리를 할 수 있도록 제어하는 놈이 Controller인 것이다.

Service에는 사용자 요구 사항 별로 메소드가 만들어져 있으면 된다. 앞 단에 메뉴가 요구 사항인 것 이다. 메뉴 별로 서비스 메소드를 만들면 된다.

CustomerShare는 로그인 성공 된 아이디가 저장될 공간으로 지정했다. 

```java
package com.my.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;

public class CustomerDAOFile implements CustomerDAO {
	private String fileName = "customers.txt";  
	private String delim = ":"; // 구분자
	@Override
	public void insert(Customer c) throws AddException { // insert는 파일 저장소에 쓰기이므로 출력스트림을 써야한다.
		// customers.txt 파일에 고객정보 투가
		// 파일 문자단위 출력스트림
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName, true); // 기존 파일이 존재하면 기존 파일을 끝 부분에 append한다. 
			String id = c.getId();
			String pwd = c.getPwd();
			String name = c.getName();
			fw.write(id+delim+pwd+delim+name); // == fw.write(id); fw.write(delim); 각각 따로 써도 된다.
			fw.write("\n");
		} catch(IOException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			try {
				fw.close();	
			} catch(IOException e) {
			}			
		}
	}

	@Override
	public List<Customer> selectAll() throws FindException {
		//FileReader fr = null;
		FileInputStream fis = null;
		List<Customer> list = new ArrayList<>();
		Scanner sc = null;
		try {
			//fr = new FileReader(fileName);
			fis = new FileInputStream(fileName);
			sc = new Scanner(fis);
			while(true) {
				//char c = (char)fr.read(); String s = String.valueOf(c);
				String line = sc.nextLine();
				String [] arr = line.split(":");
				String id = arr[0];
				String pwd = arr[1];
				String name = arr[2];	
				Customer c = new Customer(id, pwd, name);
				list.add(c);
			}
		} catch(NoSuchElementException e) {
		} catch(IllegalStateException e) {
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
			try {
				fis.close();	
			} catch(IOException e) {
			}
		}
		return list;
	}

	@Override
	public Customer selectById(String id) throws FindException {
		// TODO Auto-generated method stub
		
		}
		@Override
		public Customer update(Customer c) throws ModifyException {
			String msg = "수정할 내용이 없습니다.";
			return null;
		}
	
		@Override
		public Customer delete(String id) throws RemoveException {
			// TODO Auto-generated method stub
			return null;
		}
	
	}
```

```java
package com.my.service;

import java.util.List;

import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOFile;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;

public class CustomerService {
	//private CustomerDAO dao = new CustomerDAOList(3);//^
	**private CustomerDAO dao = new CustomerDAOFile();**
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

임시 저장소가 아닌 영구 저장소로 바꿔보자! 영구 저장소가 되려면  dao 로부터 구현된 하위클래스를 하나 더 만들어보자

파일 이름부터 결정, 파일의 고객 한명에 대한 한 줄 씩 저장. 두 번째 고객은 다음 줄에 저장..

이 파일은 현재 사용 중인 프로젝트에 저장 되도록 해본다.

![day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%201.png](day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%201.png)

---

```java
FileOutputStream fos = new FileOutputStream("a.txt");
DataOutputStream dos = new DataOutputStream(fos);
dos.writeshort(1); //2bytes
dos.writeInt(2); //4bytes
dos.writeLong(3); //8bytes
dos.close();
fos.close();
```

데이터 타입 별로  DataOutputStream에서 메소드중 writeInt(), writeLong(), writeDouble() 메소드가 제공된다. 여기서는 총 14bytes 크기가 되버리고 이 내용은 텍스트로 저장 되는게 아니라 일반 편집기로는 열기 하면 글자 깨짐 현상이 발생한다. 쓰기 된 데이터 타입 별로  순서를 알고 있는 사람들만 읽게 하려고 쓴 것이다.

```java
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIO {
	
	public static void main(String[] args) {
		String fileName = "a.dat";
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		try {
			fos = new FileOutputStream(fileName);
			dos = new DataOutputStream(fos);
			dos.writeShort(1);
			dos.writeInt(2);
			dos.writeLong(3);
			dos.writeUTF("HELLO"); //문자의 형태로 쓰기
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(dos != null) {
				try{
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			short s = dis.readShort();
			int i = dis.readInt();
			long l = dis.readLong();
			String str = dis.readUTF();
			System.out.println(s + ":" + i + ":" + l + ":" + str);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
```

![day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%202.png](day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%202.png)

![day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%203.png](day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%203.png)

크기가 14byte인 것을 볼 수 있다.

![day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%204.png](day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%204.png)

순서를 제대로 모르는 사람은 읽기를 하게 되면 전혀 뜬 끔 없는 자료가 만들어진다.

![day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%205.png](day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%205.png)

데이터 타입 별로 순서를 알고 있는 사람은 볼 수 있다.

---

![day18%206382b20b8c654ce285eb6a1a78d2da03/20210104_154621.jpg](day18%206382b20b8c654ce285eb6a1a78d2da03/20210104_154621.jpg)

```java
ObjectInputStream
ObjectOutputStream
--> 객체 단위로 읽고 쓰기가 가능하다.

FileOutputStream fos = new FileOutputStream("a.ser");
ObjectOutputStream oos = new ObjectOutputStream(fos);

oos.writeObject(new Date()); // 객체 내용을 파일을 쓸 수 있는 상태로 만드는데 이것은 객체 직렬화(얼린다)라 한다.  
														 // JVM에서 만들어낸 객체 내용을 JVM 밖에 있는 목적지로 보내기 위해서 일렬로 나열 시키는 것. 
														 // 일반 편집기로는 파일을 못 읽는다. 
                             // 이 내용을 정확히 읽으려면 거꾸로 읽기용 메소드를 사용해야한다. 
							               // FileInputStream, ObjectInputStream, oos.readObject를 해야 읽을 수 있다. 
														 // 이것을 객체 역직렬화(녹인다)라 한다. JVM 안쪽으로 만드는 작업을 말한다.
```

```java
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class ObjectIO {
	public static void main(String[] args) {
		String fileName = "a.ser";
//		FileInputStream fis = null;
//		ObjectInputStream ois = null;
//		
//		try {
//			fis = new FileInputStream(fileName);
//			ois = new ObjectInputStream(fis);
//			Object obj = ois.readObject();
//			System.out.println(obj); // println에서 참조형인 경우에는 참조형 객체의  obj.toString()자동 호출됨 
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if(ois != null) {
//				try {
//					ois.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if(fis != null) {
//				try {
//					fis.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(new Date()); //객체 직렬화
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(oos != null) { // 항상 filter스트림부터 close하고 그 다음에 node스트림을 close하는 것이다.
				try {
					oos.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
```

![day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%206.png](day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%206.png)

![day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%207.png](day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%207.png)

얼려진 객체의 상태 값을 녹여서 얼려진 시간 값을 보여주는 거다 → 객체 역직렬화

```java
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class ObjectIO {
		public static void read(String fileName) {
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			
			try {
				fis = new FileInputStream(fileName);
				ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				System.out.println(obj); // println에서 참조형인경우에는  참조형 객체의  obj.toString()자동 호출됨 : 날짜객체
				
				obj = ois.readObject();
				System.out.println(obj); //obj.toString()자동 호출됨 : 고객객체
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		public static void write(String fileName) {
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;
			try {
				fos = new FileOutputStream(fileName);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(new Date()); //객체 직렬화
				oos.writeObject(new com.my.vo.Customer("id1","p1", "n1")); //serializable 인터페이스를 구현하지 않는 객체일 경우 예외발생 Customer클래스가 serializable를 구현하면 된다.
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(oos != null) { // 항상 filter스트림부터 close하고 그 다음에 node스트림을 close하는 것이다.
					try {
						oos.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
				if(fos != null) {
					try {
						fos.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	public static void main(String[] args) {
			String fileName = "a.ser";
			//write(fileName);
			read(fileName);
	}
}
```

```java
package com.my.vo;

import java.io.Serializable;

public class Customer extends Person implements Serializable{
	private String id;
	transient private String pwd; // 직렬화 안하고싶을때는 transient라는 예약어를 쓰면된다. 객체 직렬화에서 제외
	//private String name; 
		
	public Customer() {
		super();
	}
	public Customer(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	public Customer(String id, String pwd, String name) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", pwd=" + pwd + ", name=" + name + "]";
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

serializable 인터페이스를 구현하지 않는 객체일 경우 예외발생! Customer클래스가 serializable를 구현하면 된다.

```java
package com.my.vo;

public class Person {
	protected String name; //protected로 선언된 멤버변수는 자식쪽에서 충분히 접근가능.
	//매개변수없는 생성자
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//name을 초기화하는 생성자
	public Person(String name) {
		super();
		this.name = name;
	}
	
	//set,get메서드
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//toString메서드
	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}	
}
```

![day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%208.png](day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%208.png)

toString메소드 오버라이딩 안 할 경우 @ 등 보인다.

![day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%209.png](day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%209.png)

고객의 toString메소드 오버라이딩해서 모두 출력 되도록

![day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%2010.png](day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%2010.png)

객체 내용이 아이디 비번이 다 있는데 직렬화 할 때만 제외하고 싶다 하면 멤버변수 앞에 transient예약어를 붙이면 된다. 보안상 문제가 될 때 쓰인다. 스택틱 멤버필드들은 객체 직렬화의 대상이 안된다. 스택틱은 객체랑 무관하여 객체 내의 저장 되지 않아 포함이 안된다. 

![day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%2011.png](day18%206382b20b8c654ce285eb6a1a78d2da03/Untitled%2011.png)

name은 왜 객체 직렬화 못했는가? customer가 갖고 있는 id만 직렬화. name은 부모인 Person이 갖고 있고 Serializable 구현이 안되어있으므로.. 즉 Serializable 인터페이스를 구현 클래스만 직렬화 할 수 있다.

Serializable ←(implement)- Person ←(extend)- Customer 형식으로 바꿔보자

```java
package com.my.vo;

import java.io.Serializable;

public class Customer extends Person {
	private String id;
	transient private String pwd; // 직렬화 안하고싶을때는 transient라는 예약어를 쓰면된다. 객체 직렬화에서 제외
	//private String name; 
		
	public Customer() {
		super();
	}
	public Customer(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	public Customer(String id, String pwd, String name) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", pwd=" + pwd + ", name=" + name + "]";
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
package com.my.vo;

import java.io.Serializable;

public class Person implements Serializable{
	protected String name; //protected로 선언된 멤버변수는 자식쪽에서 충분히 접근가능.
	//매개변수없는 생성자
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//name을 초기화하는 생성자
	public Person(String name) {
		super();
		this.name = name;
	}
	
	//set,get메서드
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//toString메서드
	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}	
}
```