package inheritance;
public class Circle extends Shape{
	private double radius;
	public void makeArea() {
		setArea(Math.pow(radius, 2) * Math.PI);
	}
	public void display() {
		System.out.println("반지름이 "+ radius+"인 원의 면적은 " + getArea()+"입니다");
	}
	public void setRadius(double radius) {
		if(radius <=0) {
			System.out.println("반지름은 0보다 커야합니다");
			return;
		}
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;		
	}
	
	
}
