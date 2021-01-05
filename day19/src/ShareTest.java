class Share{
	int i;
	public void plus() {
		for(int i=0; i<100; i++) {
			synchronized(this) {
				this.notify();//wait된 스레드(pop thread)를 일시중지에서 해제
				System.out.print("before plus:" + this.i);
				this.i++;
				System.out.println(", after plus:" + this.i);
			}
		}
	}
	public void minus() {		
		for(int i=0; i<100; i++) {
			synchronized(this) {
				if(this.i == 0) { //음수값이 되지 않도록 스레드를 일시중지
					try {
						this.wait();//현재스레드(pop thread)를 일시중지
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print("before minus:" + this.i);
				this.i--;
				System.out.println(", after minus:" + this.i);
			}
		}
	}
}
class Push extends Thread{
	Share s;
	public Push(Share s) {
		this.s = s;
	}
	public void run() {
		s.plus();
		
	}
}
class Pop extends Thread{
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
		Share s;
		s = new Share();
		Push push = new Push(s);
		Pop pop = new Pop(s); //pop = new Pop(); pop.s = s;
		push.start();
		pop.start();
	}
}
