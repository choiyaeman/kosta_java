import java.text.SimpleDateFormat;
import java.util.Date;

class A extends Thread{
	public void run() {
		for(int i=1; i<=100; i++) {
			System.out.print("현재스레드이름:" + this.getName() + ">>");
			System.out.println(i);
		}
	}
}
class B extends Thread{
	//스레드가 할일 : 10번반복수행하면서 현재시간값출력한다	
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0; i<10; i++) {
			System.out.println(sdf.format(new Date()));
			//1초일시중지
			long millis = 1000; 
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
public class ThreadTest {
	public static void main(String[] args) {
		System.out.print("현재스레드이름:");
		Thread now = Thread.currentThread();
		String threadName = now.getName();
		System.out.println(threadName);
		
		B b = new B();
		b.start();
		
		A a = new A();
		A a1 = new A();
		
		a.start();
		a1.start();
		
//		a.run();
//		a1.run();
		System.out.println("main메소드의 끝입니다");
	}
}