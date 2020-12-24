package abstractfinal;

class Shape {
	//private double area;
	protected double area;
	public double getArea() {
		return area;
	}
	public void makeArea() {}
}
class Circle extends  Shape{
	private double radius;
	public Circle(double radius) {
		this.radius = radius;
	}
	public void makeArea() {
		area = Math.pow(radius, 2) * Math.PI;
	}
}
class Rectangle extends Shape{
	private double width,height;
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	public void makeArea() {
		area = width * height;
	}
}

class ShapeTest{
	public static void test(Shape s) {
		s.makeArea();
	}
	public static void main(String[] args) {
		Shape s;
		s = new Circle(5);
		test(s);
		
		s = new Rectangle(3,4);
		test(s);
	}
}





