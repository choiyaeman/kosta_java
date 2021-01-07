# day20

![1](https://user-images.githubusercontent.com/63957819/103778227-4b521b80-5075-11eb-867c-c591215990fd.png)

![2](https://user-images.githubusercontent.com/63957819/103778233-4d1bdf00-5075-11eb-85d4-75f26c3de4fb.png)

네트워크 프로그래밍을 하려면 client쪽 server쪽 두 개를 다 만들어야 한다. 

서버 쪽에서 주로 client요청을 기다려야 하고  client요청의 결과를 응답하는 역할을 한다. 

프로토콜이란 컴퓨터 사이나 중앙 컴퓨터와 단말기 사이에서 데이터 통신을 원활하게 하기 위해 필요한 통신 규약을 말한다.

Transport Layer에 지원하는 TCP, UDP가 있고 TCP프로토콜 기반의 대표 응용이 HTTP 프로토콜이다. HTTP 프로토콜은 Application Layer에 쓰이는 용어이다.

TCP와 UDP의 차이점? TCP 프로토콜은 안전성을 보장하나 UDP 는 데이터의 안정성을 보장하지 못한다. 하지만 장점으로는 처리 속도가 빠르다. 이에 비해 TCP 프로토콜은 데이터를 보내고 그 데이터가 제대로 보내졌는지 꼭 확인 하도록 되어 있어서 느리다. 

![3](https://user-images.githubusercontent.com/63957819/103778235-4db47580-5075-11eb-8b47-a5a16566eeef.png)

---

- Server
1. 서버가 포트를 열고 기다린다. ServerSocket 객체 생성
2. 서버가 클라이언트 접속을 기다려야 한다.

 4.  소켓 생성됨.

 7. 데이터 수신 Receive

 8. 데이터 송신

 10. 접속 해제

---

- Client

 3. 클라이언트가 서버 접속한다. 

 5. 소켓 생성됨.

 6. 데이터 송신 Send

 9. 데이터 수신

---

PORT의 전체 범위는 0~65535 이며,  0~1024 범위는 예약 포트로 그 이상의 사용자 포트를 사용하는 거를 권장한다.

소켓이란 Connection end point를 의미한다.

소켓과 연결된 스트림은 OutPutStream을 통해 데이터 쓰기를 할 수 있는데 이것만 쓰기 불편하다 하면 filterStream으로 DataOutputStream을 사용해 가공해서 쓰면 된다.

Socket과 연결된 InputStream 이것만 가지고 read하기 불편하면 DataInputStream 으로 가공해서 쓰면 된다.  네트워크, 웹에서 쓰일 것이므로 read UTF(). 또 데이터 송신을 하려면 OutputStream 하고 DataOutputStream으로 가공해서 쓰고 write Int(1) 하면 된다.

ConnectException 는 서버가 켜져 있지 않은 상태에서 실행 할 때 발생하는 오류

ConnectException의 부모가 IOException이다. 

```java
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {
	public static void main(String[] args) {
		ServerSocket ss = null; //ServerSocket 객체생성
		int port = 5432;
		Socket s = null; //접속해제 s변수가 try블록 안에서 선언 되어있으면 finally 안에 못쓴다. 그러므로 try밖으로 선언위치를 바꿔야한다.
		DataInputStream dis = null;
		try {
			ss = new ServerSocket(port);
			ss.accept(); //콘솔창이 계속 살아 있는 것을 볼 수 있다. 클라이언트 접속 기다리는 중이라 보면 된다. 
			s = ss.accept(); //accept 반환형으로 Socket을 받을 수 있다.
			System.out.println("클라이언트가 접속됨!");
			dis = new DataInputStream(s.getInputStream());
// 1.
//			while(true) {
//				String receiveMsg = dis.readUTF();
//				if(receiveMsg.equals("quit")) {
//					break;
//				}
//				System.out.println(receiveMsg);	
//			}
// 2.연산자 우선순위 높여주기
			String receiveMsg = "quit";
			while(!(receiveMsg = dis.readUTF()).equals("quit")) { //
				System.out.println("클라이언트가 보낸 msg >" + receiveMsg);
			}
		} catch (EOFException e) { //클라이언트가 강제종료한 경우 -> 혼자 서버 클라이언트 경우에서 발생 
		} catch (SocketException e) { //클라이언트가 강제종료한 경우 -> 서버 클라이언트가 다른경우에서 발생
		} catch (IOException e) { // 사용할수 없는 포트번호를 쓸때 예외 발생. 사용할수 없는 포트번호란? 포트범위를 벗어났거나 이미 사용중인 포트번호를 사용할때
			e.printStackTrace();
		} finally { // Socket부터 닫고 ServerSocket port를 닫는다.
			System.out.println("클라이언트가 접속 해제 했습니다.");
			if(s !=null ) {
				try {
					s.close(); //접속해제 s변수가 try블록 안에서 선언 되어있으면 finally 안에 못쓴다. 그러므로 try밖으로 선언위치를 바꿔야한다.
				} catch (IOException e) {
					e.printStackTrace();
				} 	
			}
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			
		}
	}
}
```

```java
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
	public static void main(String[] args) {
		Socket s = null;
		String serverIP = "127.0.0.1"; //192.168.x.x 상대방 ip주소 //127.0.0.1 자기 local 컴퓨터의 서버 접속.
		int serverPort = 5432;
		DataOutputStream dos = null;
		Scanner sc = null; // finally에서 close해야 하기 때문에 try밖에서 선언
		try {
			s = new Socket(serverIP, serverPort); //직접 client가 Socket을 생성 //여기서 ConnectException발생 가능할 경우 IOException으로 가므로 ConnectException을 줘서 메시지를 보여주자
			dos = new DataOutputStream(s.getOutputStream());
			//dos.writeUTF("choiyaeman");
			//1)while ->(X) 반복 수행할때마다 scanner수행 그럴필요가없다
			sc = new Scanner(System.in);
// 1. while 방식
			//2)while -> (O)
//			while(true) {
//				System.out.print("메시지:");
//				String keyboardLine = sc.nextLine();
//			//3)while ->(X) 서버로 송신하는 역할만 반복하는 거므로 좋은 위치가 아니다
//				dos.writeUTF(keyboardLine);
//				if(keyboardLine.equals("quit")) {
//					break;
//				}
//			}
// 2. do~while 방식
			String keyboardLine = "quit";
			do {
				keyboardLine = sc.nextLine(); //키보드 입력하기
				dos.writeUTF(keyboardLine);
			}while(!keyboardLine.equals("quit")); //quit이 아닌경우에만 반복해라 의미
			
		} catch (UnknownHostException e) { //없는 ip에 접속할때 발생
			e.printStackTrace();
		} catch (ConnectException e) {
			System.out.println("서버가 켜졌는지, 또는 포트번호가 정확한지 확인하세요.");
		} catch (SocketException e) {
			System.out.println("서버와의 연결이 끊겼습니다.");
		} catch (IOException e) { //5432포트가 안열려있을경우 발생
			e.printStackTrace();
		} finally {
			if(sc != null) {
				sc.close(); //try~catch 안쓴다.
			}
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) { //예외까지 굳이 보지말자
				}
			}
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
```

![4](https://user-images.githubusercontent.com/63957819/103778239-4e4d0c00-5075-11eb-8297-3f2a8ebeec76.png)

![5](https://user-images.githubusercontent.com/63957819/103778241-4ee5a280-5075-11eb-9690-26e710407a28.png)

→ 메시지 출력

![6](https://user-images.githubusercontent.com/63957819/103778243-4ee5a280-5075-11eb-8d51-150deb69697e.png)

→ Scanner 이용하여 메시지 출력

![7](https://user-images.githubusercontent.com/63957819/103778246-4f7e3900-5075-11eb-91f6-4e461bc01484.png)

→ do~while 방식을 이용하여 여러 메시지 출력 quit만나면 종료

![8](https://user-images.githubusercontent.com/63957819/103778248-4f7e3900-5075-11eb-9c66-4be61649b6aa.png)

서버를 죽이면 연결이 끊어진다. 클라이언트는 모르기 때문에 계속 보낸다. end포인트가 없기 때문에 exception발생

SocketException은 ConnectException의 부모이다. ConnectException은 자식이다. 부모, 자식 exception은 catch의 순서를 정해야 한다. 자식Exception이 먼저 catch가 되어야 하고 부모가 나중에 catch가 되어야 한다.

![9](https://user-images.githubusercontent.com/63957819/103778249-5016cf80-5075-11eb-9cfa-278bd46afe8b.png)

→ 서버 강제 종료인 경우

![10](https://user-images.githubusercontent.com/63957819/103778250-5016cf80-5075-11eb-8010-8678fbb8254c.png)

→ 클라이언트 강제 종료인 경우

---

```java
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {
	public static void main(String[] args) {
		ServerSocket ss = null; //ServerSocket 객체생성
		int port = 5432;
		Socket s = null; //접속해제 s변수가 try블록 안에서 선언 되어있으면 finally 안에 못쓴다. 그러므로 try밖으로 선언위치를 바꿔야한다.
		DataInputStream dis = null;
		try { // **바깥쪽 try~catch 구문** 
			ss = new ServerSocket(port);
			ss.accept(); //콘솔창이 계속 살아 있는 것을 볼 수 있다. 클라이언트 접속 기다리는 중이라 보면 된다. 
			while(true) {
				s = ss.accept(); //accept 반환형으로 Socket을 받을 수 있다.
				try { **// 안쪽 try~catch 구문. 한 클라이언트와 반복하다가 문제발생하면 예외 처리**
					
					System.out.println("클라이언트가 접속됨!");
					dis = new DataInputStream(s.getInputStream());
		
					String receiveMsg = "quit";
					while(!(receiveMsg = dis.readUTF()).equals("quit")) { //
						System.out.println("클라이언트가 보낸 msg >" + receiveMsg);
					}
				} catch (EOFException e) { //클라이언트가 강제종료한 경우
				} catch (SocketException e) { //클라이언트가 강제종료한 경우
				} catch (IOException e) { // 사용할수 없는 포트번호를 쓸때 예외 발생. 사용할수 없는 포트번호란? 포트범위를 벗어났거나 이미 사용중인 포트번호를 사용할때
					e.printStackTrace();
				} finally {
					System.out.println("클라이언트가 접속 해제 했습니다.");
					if(s !=null ) {
						try {
							s.close(); //접속해제 s변수가 try블록 안에서 선언 되어있으면 finally 안에 못쓴다. 그러므로 try밖으로 선언위치를 바꿔야한다.
						} catch (IOException e) {
							e.printStackTrace();
						} 	
					}
				}
			} //while	
		} catch (IOException e) { 
			e.printStackTrace();
		} finally { // Socket부터 닫고 ServerSocket port를 닫는다.
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			
		}
	}
}
```

서버에서 계속해서 클라이언트의 메시지를 받아서 출력 해주고 있다. 이 서버는 한 클라이언트 읽고 죽는 성질이 있다. 접속 해제가 된 다음에는 다시 클라이언트 접속을 또 기다리는 작업을 해보자

바깥쪽 try~while 안쪽 try~while 구문의 차이점을 잘 보자

![11](https://user-images.githubusercontent.com/63957819/103778253-50af6600-5075-11eb-9cf4-92b2893df95c.png)

→ Server가 죽지 않고 접속 해제가 된 다음에 다시 클라이언트에 접속되는 것을 볼 수 있다.

```java
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {
	public static void main(String[] args) {
		ServerSocket ss = null; //ServerSocket 객체생성
		int port = 5432;
		Socket s = null; //접속해제 s변수가 try블록 안에서 선언 되어있으면 finally 안에 못쓴다. 그러므로 try밖으로 선언위치를 바꿔야한다.
		DataInputStream dis = null;
		try { // 바깥쪽 try~catch 구문 
			ss = new ServerSocket(port);
			ss.accept(); //콘솔창이 계속 살아 있는 것을 볼 수 있다. 클라이언트 접속 기다리는 중이라 보면 된다. 
			while(true) {
				s = ss.accept(); //accept 반환형으로 Socket을 받을 수 있다.
				**String clientName = s.getInetAddress().getHostAddress();** // 접속한 클라이언트 정보와 id를 얻어온다.
				try { // 안쪽 try~catch 구문. 한 클라이언트와 반복하다가 문제발생하면 예외 처리
					System.out.println(clientName + "클라이언트가 접속됨!");
					dis = new DataInputStream(s.getInputStream());
					String receiveMsg = "quit";
					while(!(receiveMsg = dis.readUTF()).equals("quit")) { //
						System.out.println(clientName + "클라이언트가 보낸 msg >" + receiveMsg);
					}
				} catch (EOFException e) { //클라이언트가 강제종료한 경우
				} catch (SocketException e) { //클라이언트가 강제종료한 경우
				} catch (IOException e) { // 사용할수 없는 포트번호를 쓸때 예외 발생. 사용할수 없는 포트번호란? 포트범위를 벗어났거나 이미 사용중인 포트번호를 사용할때
					e.printStackTrace();
				} finally {
					System.out.println(clientName + "클라이언트가 접속 해제 했습니다.");
					if(s !=null ) {
						try {
							s.close(); //접속해제 s변수가 try블록 안에서 선언 되어있으면 finally 안에 못쓴다. 그러므로 try밖으로 선언위치를 바꿔야한다.
						} catch (IOException e) {
							e.printStackTrace();
						} 	
					}
				}
			} //while	
		} catch (IOException e) { 
			e.printStackTrace();
		} finally { // Socket부터 닫고 ServerSocket port를 닫는다.
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			
		}
	}
}
```

![12](https://user-images.githubusercontent.com/63957819/103778256-5147fc80-5075-11eb-8e95-e22bd96f2855.png)

접속한 클라이언트의 정보가 서버 쪽의 소켓에 저장이 되어있다. 이거를 이용해서 누가 접속했는지 알아낼 수 있다.

```java
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {
	public static void main(String[] args) {
		ServerSocket ss = null; //ServerSocket 객체생성
		int port = 5432;
		Socket s = null; //접속해제 s변수가 try블록 안에서 선언 되어있으면 finally 안에 못쓴다. 그러므로 try밖으로 선언위치를 바꿔야한다.
		DataInputStream dis = null;
		**DataOutputStream dos = null;**
		try { // 바깥쪽 try~catch 구문 
			ss = new ServerSocket(port);
			ss.accept(); //콘솔창이 계속 살아 있는 것을 볼 수 있다. 클라이언트 접속 기다리는 중이라 보면 된다. 
			while(true) {
				s = ss.accept(); //accept 반환형으로 Socket을 받을 수 있다.
				String clientName = s.getInetAddress().getHostAddress(); // 접속한 클라이언트 정보와 id를 얻어온다.
				try { // 안쪽 try~catch 구문. 한 클라이언트와 반복하다가 문제발생하면 예외 처리
					System.out.println(clientName + "클라이언트가 접속됨!");
					dis = new DataInputStream(s.getInputStream());
					**dos = new DataOutputStream(s.getOutputStream());**
					
					String receiveMsg = "quit";
					while(!(receiveMsg = dis.readUTF()).equals("quit")) { //
						System.out.println(clientName + "클라이언트가 보낸 msg >" + receiveMsg);
						**dos.writeUTF(receiveMsg);** // client쪽으로 출력하게 다시 돌려보내자
					}
				} catch (EOFException e) { //클라이언트가 강제종료한 경우
				} catch (SocketException e) { //클라이언트가 강제종료한 경우
				} catch (IOException e) { // 사용할수 없는 포트번호를 쓸때 예외 발생. 사용할수 없는 포트번호란? 포트범위를 벗어났거나 이미 사용중인 포트번호를 사용할때
					e.printStackTrace();
				} finally {
					System.out.println(clientName + "클라이언트가 접속 해제 했습니다.");
					**if(dos != null) {
						try {
							dos.close();
						} catch (IOException e) { //예외까지 굳이 보지말자
						}
					}**
					if(s != null ) {
						try {
							s.close(); //접속해제 s변수가 try블록 안에서 선언 되어있으면 finally 안에 못쓴다. 그러므로 try밖으로 선언위치를 바꿔야한다.
						} catch (IOException e) {
							e.printStackTrace();
						} 	
					}
				}
			} //while	
		} catch (IOException e) { 
			e.printStackTrace();
		} finally { // Socket부터 닫고 ServerSocket port를 닫는다.
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			
		}
	}
}
```

```java
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
	public static void main(String[] args) {
		Socket s = null;
		String serverIP = "127.0.0.1"; //192.168.x.x 상대방 ip주소 //127.0.0.1 자기 local 컴퓨터의 서버 접속.
		int serverPort = 5432;
		DataOutputStream dos = null;
		**DataInputStream dis = null;**
		Scanner sc = null; // finally에서 close해야 하기 때문에 try밖에서 선언
		try {
			s = new Socket(serverIP, serverPort); //직접 client가 Socket을 생성 //여기서 ConnectException발생 가능할 경우 IOException으로 가므로 ConnectException을 줘서 메시지를 보여주자
			dos = new DataOutputStream(s.getOutputStream());
			**dis = new DataInputStream(s.getInputStream());**
			//dos.writeUTF("choiyaeman");
			//1)while ->(X)반복수행할때마다 scanner수행 그럴필요가없다
			sc = new Scanner(System.in);
// 1. while 방식
			//2)while -> (O)
//			while(true) {
//				System.out.print("메시지:");
//				String keyboardLine = sc.nextLine();
//			//3)while ->(X) 서버로 송신하는 역할만 반복하는 거므로 좋은 위치가 아니다
//				dos.writeUTF(keyboardLine);
//				if(keyboardLine.equals("quit")) {
//					break;
//				}
//			}
// 2. do~while 방식
			String keyboardLine = "quit";
			do {
				keyboardLine = sc.nextLine(); //키보드 입력하기
				dos.writeUTF(keyboardLine); //송신
				**String receiveMsg = dis.readUTF();** //수신
				**System.out.println("ECHO>" + receiveMsg);**	
			}while(!keyboardLine.equals("quit")); //quit이 아닌경우에만 반복해라 의미
			
		} catch (UnknownHostException e) { //없는 ip에 접속할때 발생
			e.printStackTrace();
		} catch (ConnectException e) {
			System.out.println("서버가 켜졌는지, 또는 포트번호가 정확한지 확인하세요.");
		} catch (SocketException e) {
			System.out.println("서버와의 연결이 끊겼습니다.");
		} catch (IOException e) { //5432포트가 안열려있을경우 발생
			e.printStackTrace();
		} finally {
			if(sc != null) {
				sc.close(); //try~catch 안쓴다.
			}
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) { //예외까지 굳이 보지말자
				}
			}
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
```

![13](https://user-images.githubusercontent.com/63957819/103778259-5147fc80-5075-11eb-9aa7-a30fdb6161fb.png)

출력했던 내용을 클라이언트에게 다시 보낸다 서버 쪽에서도 출력용 스트림이 만들어져 있으면 된다. 양쪽 방향 통신 구성으로 클라이언트도 입력 받는 스트림이 있어야 한다. 키보드를 입력 받은 값을 서버로 가서 서버에 다시 쓰기가 되어서 클라이언트로 오는 거다. 자기가 쓴 글이 자기에게 돌아오도록 하는 방법을 Echo 프로그램이라 부른다.

서버 쪽에서 s.close를 하면 socket이 close가 되면 socket과 연결된 스트림도 당연히 끊기는 거다.

안전하게 close를 하려면 각각 하는게 좋지만 네트워크는 결국 소켓을 통한 Input, OutPut 스트림으로 연결되는 거기 때문에 같이 close가 되는 거라 생각하면 된다.

---

![14](https://user-images.githubusercontent.com/63957819/103778260-51e09300-5075-11eb-8271-a24b47b47e5d.png)

```java
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
class ClientThread extends Thread {
	Socket s;
	DataInputStream dis = null;
	public ClientThread(Socket s) throws IOException {
		this.s = s ;
		dis = new DataInputStream(s.getInputStream()); //변수에 대입하는 코드는 반드시 생성자나 메소드 안에 들어가야한다.
	}
	public void run() { // throws IOException하면 오류발생! 부모의 스레드가 갖고있는 run메서드에는 throws IOEXception이 없으므로
		try {
			while(true) { // 소켓은 while 반복문 안에 readUTF 예외 발생시 복구가 되지 않는다. 무한 반복...그러므로 try~catch를 while위에 선언
				String receiveMsg = dis.readUTF(); //수신
				System.out.println("ECHO>" + receiveMsg);	
			}
		} catch (IOException e) {
		} finally {
		}
	}
}
public class TCPClientThread {
	public static void main(String[] args) {
		Socket s = null;
		String serverIP = "192.168.0.7";//192.168.x.x 상대방 ip주소 //127.0.0.1 자기 local 컴퓨터의 서버 접속.
		int serverPort = 5432;
		DataOutputStream dos = null;
		
		Scanner sc = null; // finally에서 close해야 하기 때문에 try밖에서 선언
		try {
			s = new Socket(serverIP, serverPort); //직접 client가 Socket을 생성 //여기서 ConnectException발생 가능할 경우 IOException으로 가므로 ConnectException을 줘서 메시지를 보여주자
			**ClientThread t = new ClientThread(s);**
			**t.start();**
			dos = new DataOutputStream(s.getOutputStream());
			sc = new Scanner(System.in);	
			String keyboardLine = "quit";
			do {
				keyboardLine = sc.nextLine(); //키보드 입력하기
				dos.writeUTF(keyboardLine); //송신
				
			}while(!keyboardLine.equals("quit")); //quit이 아닌경우에만 반복해라 의미
			
		} catch (UnknownHostException e) { //없는 ip에 접속할때 발생
			e.printStackTrace();
		} catch (ConnectException e) {
			System.out.println("서버가 켜졌는지, 또는 포트번호가 정확한지 확인하세요.");
		} catch (SocketException e) {
			System.out.println("서버와의 연결이 끊겼습니다.");
		} catch (IOException e) { //5432포트가 안열려있을경우 발생
			e.printStackTrace();
		} finally {
			if(sc != null) {
				sc.close(); //try~catch 안쓴다.
			}
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) { //예외까지 굳이 보지말자
				}
			}
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
```

보내기만 계속 아니면 받기만 계속 아니면 보내고 받기 계속. 메인 스레드가 할 일은 쓰기만 하자

Input 스트림 객체가 잘못됐다 라는 거는 연결된 Output 스트림과도 문제가 있는 거다. 

안쪽에서 아무리 예외 처리를 한다 해도 다음 구문에서 예외가 날 수 있다. 

생성자 안쪽에서 try~catch 할 필요 없이 throws로 예외를 떠넘기자

---

![15](https://user-images.githubusercontent.com/63957819/103778262-52792980-5075-11eb-8841-a94398b94ec9.png)

서버에 입장에서 접속한 클라이언트가 한 명 있다 하자 서버 프로그램에서는 가장 먼저 포트 열고 기다린다. 그 다음 클라이언트 접속을 기다리고 그 다음 소켓을 생성하는 역할하고 그 다음 송 수신을 한다. 한 클라이언트와 일을 하는 서버에서 데이터에서 fo 하면 다음 client가 접속할 기회가 없다. 이 환경을 멀티 스레드 환경으로 바꾸자. 먼저 서버에서는 포트를 열고 기다리고 그리고 소켓 생성 하는거 까지가 메인스레드가 하고 송수신 데이터 입력 받고 출력 하는 거는 새로운 스레드가 하는 거다.  

새로운 스레드는 데이터 송수신 역할만 담당하면 되고 메인스레드는 새로운 스레드를 시작하면 된다.

```java
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
class ServerThread extends Thread {
	Socket s = null; 
	DataInputStream dis = null;
	DataOutputStream dos = null;
	String clientName;
	ServerThread(Socket s) throws IOException{ //Socket타입의 인자를 받아서
		this.s = s;
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
		clientName = s.getInetAddress().getHostAddress(); // 접속한 클라이언트 정보와 id를 얻어온다.
		System.out.println(clientName + "클라이언트가 접속됨!");
	}
	public void run() { //소켓은 한번 망가지면 복구되지 않는다. 그래서 소켓이 예외가 발생할때는 반복문 밖에서 try블록을 열고 반복문 밖에서 catch블록을 닫아야한다.
		String receiveMsg = "quit";
		try {
			while(!(receiveMsg = dis.readUTF()).equals("quit")) { 
				System.out.println(clientName + "클라이언트가 보낸 msg >" + receiveMsg);
				dos.writeUTF(receiveMsg); // client쪽으로 출력하게 다시 돌려보내자
			}
		} catch (EOFException e) { //클라이언트가 강제종료한 경우
		} catch (SocketException e) { //클라이언트가 강제종료한 경우
		} catch (IOException e) { // 사용할수 없는 포트번호를 쓸때 예외 발생. 사용할수 없는 포트번호란? 포트범위를 벗어났거나 이미 사용중인 포트번호를 사용할때
			e.printStackTrace();
		} finally {
			System.out.println(clientName + "클라이언트가 접속 해제 했습니다.");
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) { //예외까지 굳이 보지말자
				}
			}
			if(s != null ) {
				try {
					s.close(); //접속해제 s변수가 try블록 안에서 선언 되어있으면 finally 안에 못쓴다. 그러므로 try밖으로 선언위치를 바꿔야한다.
				} catch (IOException e) {
					e.printStackTrace();
				} 	
			}
		}
	} 	
}
public class TCPServerThread {
	public static void main(String[] args) {
		ServerSocket ss = null; //ServerSocket 객체생성
		int port = 5432;
		Socket s = null; //접속해제 s변수가 try블록 안에서 선언 되어있으면 finally 안에 못쓴다. 그러므로 try밖으로 선언위치를 바꿔야한다.
		//DataInputStream dis = null;
		//DataOutputStream dos = null;
		try { // 바깥쪽 try~catch 구문 
			ss = new ServerSocket(port);
			while(true) {
				s = ss.accept(); //accept 반환형으로 Socket을 받을 수 있다.
				ServerThread t = new ServerThread(s);
				t.start();
				//try {
				//	System.out.println(clientName + "가 접속됨!");
				//dis = new DataInputStream(s.getInputStream());	
				//dos = new DataOutputStream(s.getOutputStream());

				//String receiveMsg = "quit";
				//while( !(receiveMsg = dis.readUTF()).equals("quit")){
				//	System.out.println(clientName + "가 보낸 msg >" + receiveMsg);
				//	dos.writeUTF(receiveMsg);
				//}
				//} catch(EOFException e) {//클라이언트가 강제종료한 경우
				//} catch(SocketException e) {//클라이언트가 강제종료한 경우	
				//} catch (IOException e) {
				//	e.printStackTrace();
				//} finally {					
				//	System.out.println(clientName +"가 접속해제했습니다");
				//	if(dos != null) {
				//						try {
				//							dos.close();
				//						} catch (IOException e) {					
				//						}
				//					}
				//					if(s != null) {
				//						try {
				//							s.close();
				//						} catch (IOException e) {
				//							e.printStackTrace();
				//						}
				//					}
				//				}
			}//while	
		} catch (IOException e) { 
			e.printStackTrace();
		} finally { // Socket부터 닫고 ServerSocket port를 닫는다.
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		  	
		}
	}
}
```

![16](https://user-images.githubusercontent.com/63957819/103778263-52792980-5075-11eb-9e49-d8e16be27d32.png)

→ 멀티 스레드 환경 여러 클라이언트에서 메시지를 보낼 수 있다
<<<<<<< HEAD

---

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2016.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2016.png)

1521포트 번호는 oracle에서 사용하는 기본 세팅 번호이다. 5432포트가 의미 상 서버 하나가 건물에 대한 주소라고 보면 된다.

서버 쪽에서 포트를 열고 기다린다. 그 다음 할 일은 클라이언트 접속을 기다린다. 클라이언트가 접속을 하고 new 키워드로 직접 Socket 객체를 생성한다. 양방향 프로그램으로 서버 쪽에서도 소켓이 만들어진다.  의미 상 종이컵 전화기처럼 실로 연결된 전화기가 만들어진 거다. 네트워크 연결 endpoint 역할을 한다. Stream처리로 데이터를 서로 주고 받는다. 소켓과 연결된 출력 스트림(OutputStream) 데이터 쓰기가 불편하면 OutputStream을 가공할 수 있는 DataOutputStream 을 통해 출력을 한다.

여기에 다가 글을 쓰는데 숫자 값을 출력하겠다 하면 dos.writeInt(1); 문자열을 출력하겠다 dos.writeUTF("hello"); 해주면 된다. 서버에서도 마찬가지로 Socket과 연결된 DataIputStream을 통해 입력을 한다. 전달 흐름은 hello문자열이 DataOutputStream을 거치고 OutputStream을 거친다음 서버 쪽으로 전달이 된다. 소켓과 연결을 끊는 절차는 close 메소드이다. 

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2017.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2017.png)

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2018.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2018.png)

에코 프로그램을 만든다 하면 클라이언트 쪽에서 소켓을 통해서 메시지를 보낸다. 서버에서 다시 메시지를 돌려 받는 거를 말한다.

InputStream, OutputStream과 네트워크 연결해 주는 놈들이 Socket이라 보면 된다. 클라이언트가 메시지를 보낼 때 OutputStream을 통해 보낸다. 서버에 도착을 하고  이 Stream들이 서버 쪽에서 똑같이 연결이 되어 있다고 하자. 서버 쪽에서는 거꾸로 OutputStream, InputStream 배열이 있고 그걸 가공한 필터 스트림들이 있다. hello라는 문자열이 스트림을 통해 서버 쪽으로 전달하고 그걸 InputStream이 읽어 들이고 쓰기를 해서 Outputstream을 통해 다시 클라이언트 쪽으로 전달하여 InputStream으로 읽어 들인다.  다른 메시지들이 서버로 간다고 예상 해보면 이 클라이언트와 일을 하느라 다른 클라이언트와 일을 할 여지가 없다. 여러 번 writeUTF를 해줬으면 서버에서도 readUTF 여러 번 작성 해줘야 한다. 스레드를 적용해서 일 처리를 나눈다. 서버 쪽에서는 main 스레드는 초록색 영역만 할 거다. 하는 일은 1. 포트열기, 2. 클라이언트 기다리기, 3. 소켓 생성하기, 4.thread-n 시작하기 이다. 그리고 새로운 스레드가 시작이 되면 이 보라색 영역에서 InputStream 으로부터 데이터를 수신을 받는다. 두 번째로 하는 작업이 수신 받은 데이터를 다시 송신을 하는 거다. 한 클라이언트와 일을 하는 스레드인거다. 메인 스레드는 다시 2번 절차로 올라가서 클라이언트 기다리고 소켓 생성한 다음 새로운 스레드 시작하는 절차를 하고 새로운 스레드는 데이터 송신, 수신을 계속 반복할 거다.  메인 스레드가 CPU를 점유하고 있다면 클라이언트의 접속을 기다리고 있을 거다. 클라이언트 하나가 접속을 하면 소켓이 하나 새로 만들어질 거고 메인 스레드는 새로운 스레드 시작한다. 일 처리 하는 스레드가 또 새로 만들어지는 거다. 그 사이에 메인 스레드가 또 CPU를 점유하면 클라이언트가 또 소켓을 만들어 낼 수 있는 거다. 이렇게 되면 총 3개의 스레드가 만들어지게 되는거다.

클라이언트와 메인 스레드 그리고 새로운 스레드로  이루어 져 있다. 글도 쓰면서 읽기를 할 수 있도록 한 거다. 즉 송신 하는 와중에 수신, 수신하는 와중에 송신 할 수 있도록 한 거다.

---

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2019.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2019.png)

접속한 클라이언트가 현재 3명이다. 두 번째라는 메시지를 보냈다 해보자. 소켓을 통해서 서버에 가고 서버에서 읽고 다시 자기한테 돌려보내는 에코가 아니고 도착을 하게 되면 두 번째 메시지를 접속한 첫 번째, 두 번째, 세 번째 클라이언트에게도 쓰기를 하는 것 즉 모든 클라이언트들에게 다 보내는 것 그러면 모든 클라이언트들이 읽을 수 있다. 이런 방법을 Broadcasting이라 한다.

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2020.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2020.png)

클라이언트는 건들지 말고 서버 쪽만 바꿔보자 접속한 클라이언들이 누구인지 알아야 BroadCast를 한다.  서버 입장에서 소켓이 클라이언트 정보를 담고 있는 end-point인 거다. 이 소켓이 기억이 되어 있어야 한다. 서버에서는 소켓을 만들 거고 그 만들어낸 소켓을 기억 저장소에 저장을 시켜 놔야 한다. 저장소 하나 필요하다. 

1. 포트열기

2. 클라이언트 기다리기 

3. 소켓 생성

4.thread-n 객체생성

5.  리스트 저장하기

배열?리스트?셋?맵? 

→ 배열은 크기가 고정되어 있다. 접속한 클라이언트 개수를 예측하지 못한 경우에는 배열 사용 불가  이므로 적합하지 않다. 

→ 클라이언트들이 접속했다 해제하는 일이 빈번할 거다. 영구 저장소 적합하지 않다.

→ 순서 없이 관리하고 싶다. set을 써도 된다. 근데 접속한 순서대로 관리하고 싶다면 set은 적합하지 않다. 접속한 순서대로 관리하고 싶으면 List를 사용

→ 맵은 key,value로 저장할 때에는 맵을 쓰는데 여기선 스레드 객체 자체가 저장되기 때문에 List를 쓰는 것이 적합.

6. thread-n 시작하기

메인 스레드에 리스트를 하나 만들어두고 이 리스트에 차례대로 0번방~ 

새로운 스레드에서도 List를 사용하는 거다. 스레드들 사이에서 공유 객체가 되는 거다.

Lsit중에 ArrayList, Vector가 있는데 Vector를 쓰는 게 안전하다

```java
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
class ServerThreadBroadCast extends Thread {
	Socket s = null; 
	DataInputStream dis = null;
	DataOutputStream dos = null;
	String clientName;
	ArrayList<ServerThreadBroadCast> list;
	ServerThreadBroadCast(Socket s, ArrayList<ServerThreadBroadCast> list) throws IOException{ //Socket타입의 인자를 받아서
		this.s = s;
		this.list = list;
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
		clientName = s.getInetAddress().getHostAddress(); 
		System.out.println(clientName + "클라이언트가 접속됨!");
		broadcast(clientName + "가 접속됨!");
	}
	public void broadcast(String receiveMsg) { // broadcast메서드 안쪽으로 반복문 for문을 넣음 // 반복문 안쪽으로 try~catch가 적절 . 한 스레드에게 문제가 발생하면 다음 스레드를 진행을 안할것이냐 차이
		for(ServerThreadBroadCast t : list) { // :앞에 변수명 t
			try { // 한 스레드에서 쓰기를 하다 문제가 발생하면 catch가서 처리하고 그 다음에 반복문을 빠져나오지 않고 다음 스레드에게 writeUTF해야되서 적절(O). 밖에서 try하면 반복문 빠져 나오므로 적절x 
				t.dos.writeUTF(receiveMsg); // 이 스레드가 받아온 메시지를 접속한 모든 스레드에게 쓰기를 한다 통신을 한다는 의도
			}
			} catch (IOException e) {
				e.printStackTrace();
			} 
	}
	public void run() { 
		String receiveMsg = "quit";
		try {
			while(!(receiveMsg = dis.readUTF()).equals("quit")) { 
				System.out.println(clientName + "클라이언트가 보낸 msg >" + receiveMsg);
				//this.dos.writeUTF(receiveMsg); // 현재 객체의 멤버변수
				//for(int i=0; i<list.size(); i++) {
				//	ServerThreadBroadCast t = list.get(i);
				broadcast(clientName + ">" + receiveMsg);
			}
		} catch (EOFException e) { //클라이언트가 강제종료한 경우
		} catch (SocketException e) { //클라이언트가 강제종료한 경우
		} catch (IOException e) { // 사용할수 없는 포트번호를 쓸때 예외 발생. 사용할수 없는 포트번호란? 포트범위를 벗어났거나 이미 사용중인 포트번호를 사용할때
			e.printStackTrace();
		} finally {
			//System.out.println(clientName + "클라이언트가 접속해제 했습니다.");
			broadcast(clientName + ">" + "가 접속해제 했습니다.");
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) { //예외까지 굳이 보지말자
				}
			}
			if(s != null ) {
				try {
					s.close(); //접속해제 s변수가 try블록 안에서 선언 되어있으면 finally 안에 못쓴다. 그러므로 try밖으로 선언위치를 바꿔야한다.
				} catch (IOException e) {
					e.printStackTrace();
				} 	
			}
		}
	} 	
}
public class TCPServerThreadBroadCast {
	public static void main(String[] args) {
		ServerSocket ss = null; //ServerSocket 객체생성
		int port = 5432;
		Socket s = null; 
		ArrayList<ServerThreadBroadCast> list = new ArrayList<>(); // 생성자를 통해서 충분히 전달 가능
		try { // 바깥쪽 try~catch 구문 
			ss = new ServerSocket(port);
			while(true) {
				s = ss.accept(); //accept 반환형으로 Socket을 받을 수 있다.
				ServerThreadBroadCast t = new ServerThreadBroadCast(s, list);
				list.add(t);
				t.start(); // 호출이 되면 새로운 스레드와 일 처리를 하려고 다툼을 한다.
								
			}//while	
		} catch (IOException e) { 
			e.printStackTrace();
		} finally { // Socket부터 닫고 ServerSocket port를 닫는다.
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		  	
		}
	}
}
```

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2021.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2021.png)

---

리스트 요소를 제거하는 와중에 새로운 클라이언트가 접속했다 라는 시나리오이다. 새로운 스레드가 만들어지고 list에 저장이 되어야 한다. 공유 객체를 한 스레드에는 리스트의 자기 객체를 삭제를 하고 메인 스레드에는 list를 추가. list를 추가나 삭제하는  와중에 cpu를 뺐기면 안된다. 

호출할 때마다 synchronize를 설정 해줘야 한다.

그러므로 ArrayList말고 안전한 Vector를 사용하자 

```java
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
class ServerThreadBroadCast extends Thread {
	Socket s = null; 
	DataInputStream dis = null;
	DataOutputStream dos = null;
	String clientName;
	Vector<ServerThreadBroadCast> list;
	ServerThreadBroadCast(Socket s, Vector<ServerThreadBroadCast> list) throws IOException{ //Socket타입의 인자를 받아서
		this.s = s;
		this.list = list;
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
		clientName = s.getInetAddress().getHostAddress(); 
		System.out.println(clientName + "클라이언트가 접속됨!");
		broadcast(clientName + "가 접속됨!");
	}
	public void broadcast(String receiveMsg) { // broadcast메서드 안쪽으로 반복문 for문을 넣음 // 반복문 안쪽으로 try~catch가 적절 . 한 스레드에게 문제가 발생하면 다음 스레드를 진행을 안할것이냐 차이
		for(ServerThreadBroadCast t : list) { // :앞에 변수명 t
			try { // 한 스레드에서 쓰기를 하다 문제가 발생하면 catch가서 처리하고 그 다음에 반복문을 빠져나오지 않고 다음 스레드에게 writeUTF해야되서 적절(O). 밖에서 try하면 반복문 빠져 나오므로 적절x 
				t.dos.writeUTF(receiveMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 이 스레드가 받아온 메시지를 접속한 모든 스레드에게 쓰기를 한다 통신을 한다는 의도
		}
	}
	public void run() { 
		String receiveMsg = "quit";
		try {
			while(!(receiveMsg = dis.readUTF()).equals("quit")) { 
				System.out.println(clientName + "클라이언트가 보낸 msg >" + receiveMsg);
				//this.dos.writeUTF(receiveMsg); // 현재 객체의 멤버변수
				//for(int i=0; i<list.size(); i++) {
				//	ServerThreadBroadCast t = list.get(i);
				broadcast(clientName + ">" + receiveMsg);
			}
		} catch (EOFException e) { //클라이언트가 강제종료한 경우
		} catch (SocketException e) { //클라이언트가 강제종료한 경우
		} catch (IOException e) { // 사용할수 없는 포트번호를 쓸때 예외 발생. 사용할수 없는 포트번호란? 포트범위를 벗어났거나 이미 사용중인 포트번호를 사용할때
			e.printStackTrace();
		} finally {
			//System.out.println(clientName + "클라이언트가 접속 해제 했습니다.");
			broadcast(clientName + ">" + "가 접속해제 했습니다.");
			
			**list.remove(this);** // list에서 자신의 객체를 빼버리는 것 뿐이다.
			
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) { //예외까지 굳이 보지말자
				}
			}
			if(s != null ) {
				try {
					s.close(); //접속해제 s변수가 try블록 안에서 선언 되어있으면 finally 안에 못쓴다. 그러므로 try밖으로 선언위치를 바꿔야한다.
				} catch (IOException e) {
					e.printStackTrace();
				} 	
			}
		}
	} 	
}
public class TCPServerThreadBroadCast {
	public static void main(String[] args) {
		ServerSocket ss = null; //ServerSocket 객체생성
		int port = 5432;
		Socket s = null; 
		Vector<ServerThreadBroadCast> list = new Vector<>(); // 생성자를 통해서 충분히 전달 가능
		try { // 바깥쪽 try~catch 구문 
			ss = new ServerSocket(port);
			while(true) {
				s = ss.accept(); //accept 반환형으로 Socket을 받을 수 있다.
				ServerThreadBroadCast t = new ServerThreadBroadCast(s, list);
				list.add(t);
				t.start(); // 호출이 되면 새로운 스레드와 일 처리를 하려고 다툼을 한다.
								
			}//while	
		} catch (IOException e) { 
			e.printStackTrace();
		} finally { // Socket부터 닫고 ServerSocket port를 닫는다.
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		  	
		}
	}
}
```
=======
>>>>>>> bf6515b3069c7d7fc9ecfd91ed35ce3518c257c5
