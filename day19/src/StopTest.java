import java.util.Scanner;

class D extends Thread{
	boolean flag = true;
	public void run() {
		while(flag) {
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
		d.start();
		//d.stop();
		System.out.print("종료하시려면 quit을 입력하세요:");
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		if(line.equals("quit")) {
			d.flag = false;
		}
	}

}
