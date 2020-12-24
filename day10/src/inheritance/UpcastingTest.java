package inheritance;

public class UpcastingTest {
	public static void main(String[] args) {
		Circle c = new Circle();
		c.setRadius(5.0);
		
		Shape s;
		s = c;//upcasting
		s.makeArea(); //overriding
		c.display();
	}
}
