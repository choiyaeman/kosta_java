/**
 * 원객체용 클래스입니다
 * @author 오문정
 * @version 1.0
 */
class Circle{
	/**
	 * 반지름
	 */
	double radius;
	
	/**
	 * 매개변수 반지름값이 0미만이면 "0이상이어야합니다"메시지가 콘솔에 출력되고 
	 * 객체의 멤버필드에 대입되지 않는다
	 * @param radius 반지름
	 * 
	 */
	void setRadius(double radius) {
//		if(r < 0) {
//			System.out.println("0이상이어야합니다");
//			return;
//		}else {
//			radius = r;
//		}
		if(radius < 0) {
			System.out.println("0이상이어야합니다");
			return;
		}
		this.radius = radius;		
	}
}
public class ThisTest {
	public static void main(String[] args) {
		Circle c1; 
		c1 = new Circle();
		//c1.radius = 5.7;
		c1.setRadius(5.7);
		c1.setRadius(-1.2);
		
		Circle c2 = new Circle();
		c2.setRadius(2.3);
		
	}

}
