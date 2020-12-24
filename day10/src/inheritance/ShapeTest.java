package inheritance;

public class ShapeTest {
	public static void main(String[] args) {
		Circle c = new Circle();
		c.setRadius(5);
		c.makeArea();
		c.display();
		
		Rectangle r = new Rectangle(3,4);
		r.makeArea();
		r.display();
		
		Triangle t = new Triangle(2, 3);
		t.makeArea();
		t.display();
		
		Shape []sArr = new Shape[3];
		sArr[0] = c;//upcasting
		sArr[1] = r;
		sArr[2] = t;
		
		//for(int i=0; i<sArr.length; i++) {	
		//	sArr[0].makeArea(); //컴파일 오류
		//	System.out.println(sArr[i].getArea()); //컴파일 성공
		//}
		for(Shape s: sArr) {
			System.out.println(s.getArea());
		}		
		
	}
}