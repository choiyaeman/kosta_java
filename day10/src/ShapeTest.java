
public class ShapeTest {
	public static void main(String[] args) {
		Circle c1 = new Circle();
		c1.setRadius(5.0); //반지름설정한다
		c1.makeArea();//면적계산한다
		c1.display();//결과를 출력한다

		Rectangle r1 = 
		  new Rectangle(3, 4); //가로3, 세로4인 사각형객체를 생성한다
		r1.makeArea(); //면적계산한다
		r1.display();  //결과를 출력한다 가로3.0, 세로4.0인 사각형의 면적은 12.0입니다
	
		Triangle t1 =
		  new Triangle(2, 3); //밑변2, 높이가 3인 이등변삼각형객체를 생성한다
		t1.makeArea();
		t1.display(); //
		
	}
}