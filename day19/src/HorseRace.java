import java.awt.Canvas;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class Horse extends Canvas implements Runnable{
	int x = 150;
	int y = 10;
	@Override
	public void paint(Graphics g) {
		g.drawString("말", x, y);
	}
	@Override
	public void run() {
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
class Start implements ActionListener{
	Horse[] horses;
	Start(Horse[] horses){
		this.horses = horses;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(Horse h:horses) {
//			for(int i=0; i<10; i++) {
//				h.x = 5*i;
//				h.repaint();				
//			}
			Thread t = new Thread(h);
			t.start();
		}
	}
}
class Ready implements ActionListener{
	Horse[] horses;
	Ready(Horse[] horses){
		this.horses = horses;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(Horse h : horses) {
			h.x = 0;
			h.repaint();
		}
//		System.out.println("준비버튼이 클릭됨!");
	}
}
public class HorseRace {
	JFrame frame; //액자
	JButton btStart, btReady; //버튼
	Horse []horses = new Horse[3];
	public HorseRace() {
		frame = new JFrame("달리기");
		btStart = new JButton("달려");
		btStart.addActionListener(new Start(horses));
		btReady = new JButton("준비");
		//btReady.addActionListener(new ActionListener());
		//btReady.addActionListener(new Ready(horses));
//		btReady.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				for(Horse h : horses) {
//					h.x = 0;
//					h.repaint();
//				}
//			}
//		});
		btReady.addActionListener((e)->{
			for(Horse h : horses) {
				h.x = 0;
				h.repaint();
			}
		});
		for(int i=0; i<horses.length; i++) {
			horses[i] = new Horse();			
		}
		Container c = frame.getContentPane(); //액자뒷종이판
		c.setLayout(new GridLayout(5, 1)); //액자뒷종이판배치방식을 5행1열로 배치
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