# day19

![1](https://user-images.githubusercontent.com/63957819/103671925-ecca6600-4fbe-11eb-84e8-61e9edfcce36.jpg)

Thread를 실이라고 생각하면 된다.

Process란 application이 실행된 상태를 의미한다. thread가 process를 구성하는 작업 단위 구성 요소이다. 

한 개의 스레드로 구성된 프로세스를 단일 스레드, 여러 개의 스레드로 구성된 프로세스를 멀티스레드라 한다.

t1, t2, t3 스레드 하는 일이 각각 다르다. 이 일 처리를 동시에 수행하는 것처럼 보이게 할 수 있다. t1스레드가 일 처리가 다 끝난 다음에 t2 스레드가 일 처리를 하는 게 아니고 t1 스레드가 어느 정도 일을 하다가 t2, t3에게 뺏길 수가 있다. 이렇게 다른 스레드에 CPU를 뺏껴서 CPU를 점유를 한다. 이런 것이 바로 멀티스레드의 환경이다. 시간을 잘게 잘게 쪼개보면 세 가지의 스레드가 마치 동시에 수행하는 것처럼 보인다. 

자바 스레드는 간단하다? 절대 그렇지 않다. 자바가 포커스이다 자바로 표현하기 간단한 거지 스레드는 복잡한 개념이다.

java -cjp bin A

1. A.class파일 찾기
2. 바이트 코드 검증(byteCodeVerify)
3. JVM에 로드(실행 준비를 하겠다는 의도)
4. byte → binary(0,1)로 재해석
5. static 변수 초기화
6. main-Thread가 자동 만들어짐
7. main()메소드 호출(main-Thread에 의해서 min 메소드 호출)

![2](https://user-images.githubusercontent.com/63957819/103671927-edfb9300-4fbe-11eb-9711-71d2ab26e428.jpg)

thread 1가 가장 먼저 소멸이 되고 main 스레드와 thread 0중에 실제 메모리에서 사라지는 거는 thread 0가 먼저다 왜 이런가 하면 메인 스레드가 있고 스레드 0을 파생시킨 것은 메인 스레드가 새로운 스레드를 파생 시킨 거다. 자기가 파생시킨 스레드가 소멸 할 때까지 기다린다. 

컴퓨터의 상황에 따라서 어떤 스레드가 cpu를 점유할지 모르기 때문에 시간을 잘게 잘게 쪼개 쓰게 되면 마치 동시에 수행되는 것처럼 보이기 되는 방식을 멀티 스레드라 한다.

new는 단지 객체 생성일 뿐 스레드로써의 효과가 나려면 start메소드가 호출이 되어야 스레드 효과가 난다. 스레드를 반드시 시작 시켜야 한다. A a = new A();  a. start();

```java
import java.text.SimpleDateFormat;
import java.util.Date;

class A extends Thread{
	public void run() { // 해당 스레드가 시작되면 run메소드 자동 호출 된다. run메소드 오버라이딩
		for(int i=1; i<100; i++) {
			System.out.print("현재 스레드 이름:" + this.getName() + ">>"); // 부모인 thread에는 getName메소드가 있다
			System.out.println(i);
		}
	}
}
class B extends Thread{
	//스레드가 할일 : 10번 반복 수행하면서 현재 시간값 출력한다.
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0; i<10; i++) {
			//System.out.println(new Date());
			System.out.println(sdf.format(new Date())); // 늦은 메모리를 많이 쓰는 메소드이다. 이거 하려하면 다른 스레드에게 뺐긴다
			// 1초 일시 중지
			long millis = 1000; // 1초 1000
			try {
				Thread.sleep(millis); // 1초씩 제어. 시간이 지나면 다시 Runable상태로 돌아간다. 실행 준비상태에서 다시 Run되는 시간이 있으므로 1초 이상이 될 수 있다.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
public class ThreadTest {
	public static void main(String[] args) {
		System.out.print("현재 스레드 이름:");
		Thread now = Thread.currentThread(); // 지금 사용중인 현재 스레드를 반환
		String threadName = now.getName();
		System.out.println(threadName); // main. main스레드에 의해서 main이 호출
		
		A a = new A(); // 객체 생성 한다고 스레드 효과가 나는 것이 아니므로 start메소드 필요
		A a1 = new A();
		a.start(); // cup를 점유할 가능성이 있는 스레드가 총 2개가 있는 것 이다. main, A 스레드 중 cpu를 먼저 점유할지 아무도 모른다.
		a1.start(); // start메소드를 호출해서 준비를 하게하고 자동 run메소드를 호출하게 하는 것이다.
//		a.run(); // 스레드용 객체만 만들어놓고 시작을 안하기 때문에 메인 스레드의 역할만 한다. 싱글 스레드
//		a1.run();
		
		B b = new B();
		b.start();
		
		System.out.println("main메소드의 끝입니다.");
	}
}
```

![3](https://user-images.githubusercontent.com/63957819/103671930-edfb9300-4fbe-11eb-8ed8-e655e5800b3d.jpg)

스레드가 start()메소드를 호출 하는 것은 스레드를 준비 상태를 만든다. Runnable 상태로 들어온다.

Run 상태란 CPU를 점유하는 상태를 말한다.  Run메소드 자동 호출이 되고 어느 정도 호출이 되다가 Run상태에서 Runable상태로 이동이 될 수 있다.  Run상태에서 계속 머무는 게 아니라 내쳐지면 Runable상태로 왔다 갔다 한다. 이런 결정을 CPU가 하는 일이다.  Run메소드 끝까지 가면 할 일이 없어서 dead상태에 빠져버린다. 이것을 종료 상태라 말한다. 객체가 없어지는 게 아니라 스레드의 상태가 그때그때 바뀌는 것 이다. sleep메소드 호출하게 되면 해당 스레드를 잠깐 재울 수 있다. 이 상태를 sleep 상태라 부른다. sleep 상태가 된 스레드는 시간이 결정이 되어있다. 시간 값을 이용해서 깨어나게 되어 있다. 깨어나서 직접 Run상태로 가는 게 아니라 Runable상태로 가서 기다려야 한다. 준비가 되어야 한다. 

```java
class C extends Thread {
	int sum = 0;
	int startNum, endNum;
	C(int startNum, int endNum) {
		this.startNum = startNum;
		this.endNum = endNum;
	}
	public void run() {
		for(int i=startNum; i<=endNum; i++) {
			sum +=i;
		}
	}
}
public class JointTest {
	
	public static void main(String[] args) {
		C c1, c2;
		c1 = new C(1,10);
		c2 = new C(101, 110);
		c1.start();
		c2.start();
		// c1,c2스레드가 서로 다투게 냅두고 메인스레드가 끝날때까지 기다린다.
		try {
			c1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			c2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(c1.sum + ", " + c2.sum); // join을 안할 경우 -> 정확히 합이 구하지 않는상태에서 sum변수의 옳지 않는 값이 들어가 있을거다.
	}
}
```

join 메소드는 스레드가 죽을 때까지 기다리는 메소드이다. 누가 기다리지? 그 메소드를 호출하는 스레드가 기다리는 거다. 해당 스레드가 죽을 때까지 기다리는 메소드로써 일시 중지용 메소드이다.

start 메소드로 스레드가 메인, c1, c2용 스레드 총 3개 스레드가 다툼할 것이다.  join 메소드를 써야 하는 경우는 언제 써먹어 하는 지가 중요하다. 메인 스레드에서 c1 스레드의 결과, c2 스레드의 결과 값을 가져와서 사용해야 될 때 c1과c2 서로 다툼하게 내버려 두되 한 스레드가 다른 스레드의 결과값을 가져와서 써야 할 때만 join 메소드를 써야 한다.

![4](https://user-images.githubusercontent.com/63957819/103671931-ee942980-4fbe-11eb-8a1d-3b44eabbc987.jpg)

스레드가 반드시 시작이 되어야 하고 실행 준비 상태가 되어야 한다. 실행 준비 상태에서 sleep이라는 메소드를 호출하면 timed waiting(일시중지)상태가 된다. 시간이 지나면 다시 Runable상태로 빠진다. 또한 대표 메소드로 blocked상태로 빠뜨리는 메소드가 join이라는 메소드가 있다. 

메인 스레드 같은 경우 가장 먼저 cpu를 점유하는 스레드이다. 

메인 메소드의 안쪽에서 join을 만났다 하게 되면 main스레드가 블럭 상태로 빠지게 된다. a스레드가 블럭 상태로 빠지게 되는게 아니다!  

```java
import java.util.Scanner;

class D extends Thread {
	boolean flag = true;
	public void run() {
		while(flag) { //true를 빼고 위에 변수주어서 대입
			//System.out.println("반복");
			try {
				Thread.sleep(1000); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
public class StopTest {

		public static void main(String[] args) {
			D d = new D();
			d.start(); //메인 스레드가 d스레드를 파생 시켰기 때문에 죽을때까지 기다리지만 , 무한 반복을 하므로 메인 스레드가 안끝나는 거다.
			//d.stop(); // 안 쓰는게 좋겠다 권고
			System.out.print("종료하시려면 quit을 입력하세요:");
			Scanner sc = new Scanner(System.in);
			String line = sc.nextLine();
			if(line.equals("quit")) {
				System.out.println("quit입력됨!");
				d.flag = false;
			}
		}
		
}
```

stop메소드를 안 쓰면 강제로 run메소드를 자연스럽게 빠져나오는 방법밖에 없다. 

![5](https://user-images.githubusercontent.com/63957819/103671934-ee942980-4fbe-11eb-9934-63559d692910.jpg)

인터페이스 메소드는 추상 메소드로 반드시 오버라이딩이 되어야 한다.

부모쪽 Thread에 start메소드를 갖고 있기때문에 a.start()를 쓸 수 있는 것이다.

start메소드는 Thread클래스가 제공하는 메소드이다. B클래에서는 b.start를 못한다 b가 스레드를 상속 받지 않고 Runable 직접 구현했기 때문에 thread타입 객체를 생성시 생성자 인자로 b객체 참조 변수를 넣어주고(Thread t = new Thread(b);)  t.start()를 해주면 된다. b클래스에 오버라이딩 된 run메소드가 호출이 된다.

```java
import java.awt.Canvas;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class Horse extends Canvas implements Runnable{ //액자 뒷 종이에 올라 갈 수 있다. // 말 자체가 경쟁을 해야하므로 Canvas를 상속 받으면서 인터페이스로부터도 구현이 되어야한다. 오버라이딩 해야한다.
	int x = 150;
	int y = 10;
	@Override
	public void paint(Graphics g) {
		g.drawString("말", x, y);
	}
	@Override
	public void run() { // 말들 자체가 각각 달리기를 해야 한다. 
		for(int i=0; i<50; i++) {
			this.x = 10*i; 
			this.repaint();
			long millis = (long)(Math.random()*500);
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	} 
	
}
class Ready implements ActionListener{
	Horse[] horses;
	Ready(Horse[] horses) {
		this.horses = horses;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		horses[0].x = 0;
		horses[0].repaint(); //첫번째 말만 x포지션을 바꾼다
		horses[1].x = 0;
		horses[1].repaint(); //두번째 말만 x포지션을 바꾼다
		horses[2].x = 0;
		horses[2].repaint(); //세번째 말만 x포지션을 바꾼다
		//System.out.println("준비 버튼이 클릭됨!");
	}
	
}
class Start implements ActionListener {

	Horse[] horses;
	Start(Horse[] horses) {
		this.horses = horses;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(Horse h:horses) { // h는 첫번째 말 이 h를 10번 반복수행하면서 5px씩 뛰기.10번이 다 끝나고나면 두번째 반복문으로 올라가면 두번째 말이..똑같은 과정으로 세번째 말이.. 즉 첫번째 말이 1등이 될 수 밖에 없는 구조 이럴러면 스레드가 필요
//			for(int i=0; i<10; i++) {
//				h.x = 5*i; //5픽셀씩 10번 뛰겠다
//				h.repaint();
//			}
			Thread t = new Thread(h);
			t.start();
		}
	}
	
}
public class HorseRace {
	JFrame frame; //액자
	JButton btStart, btReady; //버튼
	Horse[] horses = new Horse[3]; //3개짜리 방만 만든거다
	public HorseRace() {
		frame = new JFrame("달리기");
		btStart = new JButton("달려");
		btStart.addActionListener(new Start(horses));
		btReady = new JButton("준비"); 
		//btReady.addActionListener(new ActionListener()); //준비 버튼이 클릭 되었을때 할 일을 적어줘야한다 // addActionListener타입은 interface로 new키워드로 X 객체 생성직접x
		btReady.addActionListener(new Ready(horses)); //준비 버튼이 클릭 되었을때 할 일을 적어줘야한다. addationListenr인자로 지정된 actionPerformed메소드가 자동 호출. 말 세마리를 넘기므로 받아오면 된다
		for(int i=0; i<horses.length; i++) {
			horses[i] = new Horse();
		}
		Container c = frame.getContentPane(); //액자 뒷 종이판
		c.setLayout(new GridLayout(5, 1)); //액자 뒷 종이판 배치 방식을 5행1열로 배치아웃
		c.add(horses[0]);
		c.add(horses[1]);
		c.add(horses[2]);
		c.add(btReady);
		c.add(btStart);
		frame.setSize(500, 300); //액자크기
		frame.setVisible(true); //화면에 나타남
	}
	
	public static void main(String[] args) {
		new HorseRace();
	}
	
}
```

---

![6](https://user-images.githubusercontent.com/63957819/103671936-ef2cc000-4fbe-11eb-9791-92f32322c215.png)

```java
class Share {
	int i; // 초기화 안해도 0값으로 초기화
	public void plus() {
		for(int i=0; i<100; i++) {
			this.i++;
		}
		
	}
	public void minus() {
		for(int i=0; i<100; i++) {
			this.i--;
		}
	}
	
}
class Push extends Thread {
	Share s;
	public Push(Share s) {
		this.s = s;
	}
	public void run() {
		s.plus();
	}
	
}
class Pop extends Thread {
	Share s;
	public Pop(Share s) {
		this.s = s;
	}
	public void run() {
		s.minus();
	}
	
}
public class ShareTest {
	
	public static void main(String[] args) {
		
		Share s; // Share 타입의 객체 생성
		s = new Share();
		Push push = new Push(s);
		Pop pop = new Pop(s); // -> pop = new Pop(); pop.s = s;
		push.start();
		pop.start();
		
	}

}
```

```java
class Share {
	int i; // 초기화 안해도 0값으로 초기화 
	public void plus() {
		for(int i=0; i<100; i++) {
			synchronized(this) { //임계 영역을 설정하는 작업 즉 쓰레드의 동기화. ()안에는 공유객체를 넣어줘야 한다. share클래스 객체자체가 공유객체니깐 this. 단, 공유객체를 사용하지 않는 다른 스레드에게는 CPU를 뺏긴다.
				this.notify(); //wait된 스레드(pop thread)를 일시중지에서 해제 //공유객체를 사용하는 일시 중지된 pop thread를 해제하는 방법
				System.out.print("befor plus:" + this.i);
				this.i++;
				System.out.println(", after plus:" + this.i);	
			}
			
		}
		
	}
	public void minus() { // 메소드 앞에 동기화 처리하지 마라! 굉장히 퍼모먼스가 저하된다.
		for(int i=0; i<100; i++) {
			synchronized (this) { // synchronized의 블록 처리를 분절을 짧게 짧게 해줘야한다. 긴 구문의 synchronized를 사용하면 퍼포먼스가 떨어질 수 있다.
				if(this.i == 0) { //음수값이 되지 않도록 스레드를 일시중지
					try {
						this.wait(); //공유객체를 사용할때 현재 스레드(pop thread)를 일시 중지. pop스레드를 일시 중지 시킨 코드. wait메소드는 최상위 클래스 Object가 갖고있고 현재 스래드를 일시 중지 시키는 메소드이므로
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print("befor minus:" + this.i);
				this.i--; // CPU가 thread를 내칠 수 있다. this.i = this.i-1; ->대입할때 아니면 -1할때 내쳐질 수 있다.
				System.out.println(", after minus:" + this.i);	
			}
			
		}
	}
	
}
class Push extends Thread {
	Share s;
	public Push(Share s) {
		this.s = s;
	}
	public void run() {
		s.plus();
	}
	
}
class Pop extends Thread {
	Share s;
	public Pop(Share s) {
		this.s = s;
	}
	public void run() {
		s.minus();
	}
	
}
public class ShareTest {
	
	public static void main(String[] args) {
		
		Share s; // Share 타입의 객체 생성
		s = new Share();
		Push push = new Push(s);
		Pop pop = new Pop(s); // -> pop = new Pop(); pop.s = s;
		push.start();
		pop.start();
		
	}

}
```

만약 i변수가 중요한 자료의 잔액이라 해보자! 잔액이 감소 됐다가 CPU에서 내쳐 지게 되면 올바른 잔액 값으로 유지 못한다. 입금할 때, 출금할 때 정확히 유지가 되어야 한다. 이 위험한 자료들은 lock처리 해줘야 한다. 영역을 지정해줘야 하는데 이 영역을 임계 영역이라 한다.  synchronized는 공유 자원을 사용하는 다른 자원에게 CUP를 뺏기지 않도록 하겠다.

![7](https://user-images.githubusercontent.com/63957819/103671938-ef2cc000-4fbe-11eb-9a40-8746e6912afe.png)

ArrayList클래스 vs Vector클래스 or Hashtable vs HashMap

→ 동기화 문제로 스레드 동기화 처리되어 있는 놈은 처리 속도가 느리다.

![8](https://user-images.githubusercontent.com/63957819/103671940-efc55680-4fbe-11eb-94bd-6f8c3adb7763.png)

wait 라는 메소드를 호출하면 블록킹 상태로 빠진다. 이 wait 된 스레드는 특정 메소드를 통해서만 깨어날 수 있다. 반드시 notify, notifyAll 이라는 메소드를 통해서만 깨어날 수 있다.

그 객체에서 wait 메소드를 호출하면 기다린 데 객체가 기다리는 게 아니라 이 객체를 사용하는 현재 스레드가 notify, notifyAll 메소드가 호출될 때 까지 기다리는 거다. 다른 스레드가 현재 객체의 notify,notifyall 호출될 때까지 기다린다. 즉 그때 깨어난다.

```java
class Share {
	int i; // 초기화 안해도 0값으로 초기화 
	public void plus() {
		for(int i=0; i<100; i++) {
			synchronized(this) { //임계 영역을 설정하는 작업 즉 쓰레드의 동기화. ()안에는 공유객체를 넣어줘야 한다. share클래스 객체자체가 공유객체니깐 this. 단, 공유객체를 사용하지 않는 다른 스레드에게는 CPU를 뺏긴다.
				this.notify(); //wait된 스레드(pop thread)를 일시중지에서 해제 //공유객체를 사용하는 일시 중지된 pop thread를 해제하는 방법
				System.out.print("befor plus:" + this.i);
				this.i++;
				System.out.println(", after plus:" + this.i);	
			}
			
		}
		
	}
	public void minus() { // 메소드 앞에 동기화 처리하지 마라! 굉장히 퍼모먼스가 저하된다.
		for(int i=0; i<100; i++) {
			synchronized (this) { // synchronized의 블록 처리를 분절을 짧게 짧게 해줘야한다. 긴 구문의 synchronized를 사용하면 퍼포먼스가 떨어질 수 있다.
				if(this.i == 0) { //음수값이 되지 않도록 스레드를 일시중지
					try {
						this.wait(); //공유객체를 사용할때 현재 스레드(pop thread)를 일시 중지. pop스레드를 일시 중지 시킨 코드. wait메소드는 최상위 클래스 Object가 갖고있고 현재 스래드를 일시 중지 시키는 메소드이므로
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print("befor minus:" + this.i);
				this.i--; // CPU가 thread를 내칠 수 있다. this.i = this.i-1; ->대입할때 아니면 -1할때 내쳐질 수 있다.
				System.out.println(", after minus:" + this.i);	
			}
			
		}
	}
	
}
class Push extends Thread {
	Share s;
	public Push(Share s) {
		this.s = s;
	}
	public void run() {
		s.plus();
	}
	
}
class Pop extends Thread {
	Share s;
	public Pop(Share s) {
		this.s = s;
	}
	public void run() {
		s.minus();
	}
	
}
public class ShareTest {
	
	public static void main(String[] args) {
		
		Share s; // Share 타입의 객체 생성
		s = new Share();
		Push push = new Push(s);
		Pop pop = new Pop(s); // -> pop = new Pop(); pop.s = s;
		push.start();
		pop.start();
		
	}

}
```

![9](https://user-images.githubusercontent.com/63957819/103671945-f05ded00-4fbe-11eb-8077-bd70ca76ac80.png)

차곡차곡 쌓인다는 개념은 자료 구조의 형태가 큐. Runnable상태에서  큐 자료구조를 이용해서 먼저 들어온 놈이 먼저 나감으로 push가 CPU 점유했다가 나갔다 다시 점유 할 수 있다. 그리고 pop 스레드가 CPU를 점유하고 wait가 되면 Block상태가 되고 notify, notifyAll 메소드를 만나게 되면 wait된 상태에 빠지던 것이 Runnable상태로 깨어나게 된다. notify, notifyAll은 스레드의 공유, 동기화일 때만 사용한다.

---

```java
import java.awt.Canvas;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class Horse extends Canvas implements Runnable{ //액자 뒷 종이에 올라 갈 수 있다. // 말 자체가 경쟁을 해야하므로 Canvas를 상속 받으면서 인터페이스로부터도 구현이 되어야한다. 오버라이딩 해야한다.
	int x = 150;
	int y = 10;
	@Override
	public void paint(Graphics g) {
		g.drawString("말", x, y);
	}
	@Override
	public void run() { // 말들 자체가 각각 달리기를 해야 한다. 10px씩 10번 반복 수행 
		for(int i=0; i<50; i++) {
			this.x = 10*i; 
			this.repaint();
			long millis = (long)(Math.random()*500);
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	} 
	
}
class Start implements ActionListener {
	Horse[] horses;
	Start(Horse[] horses) {
		this.horses = horses;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(Horse h:horses) { // h는 첫번째 말이 h를 10번 반복수행하면서 5px씩 뛰기.10번이 다 끝나고나면 두번째 반복문으로 올라가면 두번째 말이..똑같은 과정으로 세번째 말이.. 즉 첫번째 말이 1등이 될 수 밖에 없는 구조 이럴러면 스레드가 필요
//			for(int i=0; i<10; i++) {
//				h.x = 5*i; //5픽셀씩 10번 뛰겠다
//				h.repaint();
//			}
			Thread t = new Thread(h);
			t.start();
		}
	}
	
}
class Ready implements ActionListener{ // 이 클래스가 재사용이 떨어진다하면 굳이 클래스를 안만들어도 된다.
	Horse[] horses;
	Ready(Horse[] horses) {
		this.horses = horses;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(Horse h : horses) {
			h.x = 0;
			h.repaint();
		}
		//System.out.println("준비 버튼이 클릭됨!");
	}
	
}

public class HorseRace {
	JFrame frame; //액자
	JButton btStart, btReady; //버튼
	Horse[] horses = new Horse[3]; //3개짜리 방만 만든거다
	public HorseRace() {
		frame = new JFrame("달리기");
		btStart = new JButton("달려");
		btStart.addActionListener(new Start(horses));
		btReady = new JButton("준비"); 
		//btReady.addActionListener(new ActionListener()); //준비 버튼이 클릭 되었을때 할 일을 적어줘야한다 // addActionListener타입은 interface로 new키워드로 X 객체 생성직접x
		//btReady.addActionListener(new Ready(horses)); //준비 버튼이 클릭 되었을때 할 일을 적어줘야한다. addationListenr인자로 지정된 actionPerformed메소드가 자동 호출. 말 세마리를 넘기므로 받아오면 된다
        //1. 익명 구현 객체
//		btReady.addActionListener(new ActionListener() { // ActionListener 인터페이스를 구현한 하위 클래스이긴 한데 이름없는 객체를 생성 -> 무명 클래스. 재사용성이 떨어질 경 우 쓴다.
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				horses[0].x = 0;
//				horses[0].repaint(); //첫번째 말만 x포지션을 바꾼다
//				horses[1].x = 0;
//				horses[1].repaint(); //두번째 말만 x포지션을 바꾼다
//				horses[2].x = 0;
//				horses[2].repaint(); //세번째 말만 x포지션을 바꾼다
//			}
//		});
		//2. 람다식 표현
		btReady.addActionListener((e)-> { //람다식 표현
			for(Horse h : horses) {
				h.x = 0;
				h.repaint();
			}
		});
		for(int i=0; i<horses.length; i++) {
			horses[i] = new Horse();
		}
		Container c = frame.getContentPane(); //액자 뒷 종이판
		c.setLayout(new GridLayout(5, 1)); //액자 뒷 종이판 배치 방식을 5행1열로 배치아웃
		c.add(horses[0]);
		c.add(horses[1]);
		c.add(horses[2]);
		c.add(btReady);
		c.add(btStart);
		frame.setSize(500, 300); //액자크기
		frame.setVisible(true); //화면에 나타남
	}
	
	public static void main(String[] args) {
		new HorseRace();
	}
	
}
```
