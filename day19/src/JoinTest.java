class C extends Thread{
	int sum=0;
	int startNum, endNum;
	C(int startNum, int endNum){
		this.startNum = startNum;
		this.endNum = endNum;
	}
	public void run() {
		for(int i=startNum; i<=endNum; i++) {
			sum += i;
		}		
	}
}
public class JoinTest {
	public static void main(String[] args) {
		C c1, c2;
		c1 = new C(1,10);
		c2 = new C(101, 110);
		c1.start();		c2.start();
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
		System.out.println(c1.sum +"," + c2.sum);
	}
}
