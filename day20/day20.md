# day20

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled.png)

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%201.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%201.png)

네트워크 프로그래밍을 하려면 client쪽 server쪽 두 개를 다 만들어야 한다. 

서버 쪽에서 주로 client요청을 기다려야 하고  client요청의 결과를 응답하는 역할을 한다. 

프로토콜이란 컴퓨터 사이나 중앙 컴퓨터와 단말기 사이에서 데이터 통신을 원활하게 하기 위해 필요한 통신 규약을 말한다.

Transport Layer에 지원하는 TCP, UDP가 있고 TCP프로토콜 기반의 대표 응용이 HTTP 프로토콜이다. HTTP 프로토콜은 Application Layer에 쓰이는 용어이다.

TCP와 UDP의 차이점? TCP 프로토콜은 안전성을 보장하나 UDP 는 데이터의 안정성을 보장하지 못한다. 하지만 장점으로는 처리 속도가 빠르다. 이에 비해 TCP 프로토콜은 데이터를 보내고 그 데이터가 제대로 보내졌는지 꼭 확인 하도록 되어 있어서 느리다. 

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%202.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%202.png)

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

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%203.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%203.png)

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%204.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%204.png)

→ 메시지 출력

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%205.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%205.png)

→ Scanner 이용하여 메시지 출력

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%206.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%206.png)

→ do~while 방식을 이용하여 여러 메시지 출력 quit만나면 종료

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%207.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%207.png)

서버를 죽이면 연결이 끊어진다. 클라이언트는 모르기 때문에 계속 보낸다. end포인트가 없기 때문에 exception발생

SocketException은 ConnectException의 부모이다. ConnectException은 자식이다. 부모, 자식 exception은 catch의 순서를 정해야 한다. 자식Exception이 먼저 catch가 되어야 하고 부모가 나중에 catch가 되어야 한다.

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%208.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%208.png)

→ 서버 강제 종료인 경우

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%209.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%209.png)

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

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2010.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2010.png)

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

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2011.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2011.png)

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

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2012.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2012.png)

출력했던 내용을 클라이언트에게 다시 보낸다 서버 쪽에서도 출력용 스트림이 만들어져 있으면 된다. 양쪽 방향 통신 구성으로 클라이언트도 입력 받는 스트림이 있어야 한다. 키보드를 입력 받은 값을 서버로 가서 서버에 다시 쓰기가 되어서 클라이언트로 오는 거다. 자기가 쓴 글이 자기에게 돌아오도록 하는 방법을 Echo 프로그램이라 부른다.

서버 쪽에서 s.close를 하면 socket이 close가 되면 socket과 연결된 스트림도 당연히 끊기는 거다.

안전하게 close를 하려면 각각 하는게 좋지만 네트워크는 결국 소켓을 통한 Input, OutPut 스트림으로 연결되는 거기 때문에 같이 close가 되는 거라 생각하면 된다.

---

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2013.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2013.png)

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

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2014.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2014.png)

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

![day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2015.png](day20%203a84069b0da2450881b91a36c1e48f31/Untitled%2015.png)

→ 멀티 스레드 환경 여러 클라이언트에서 메시지를 보낼 수 있다