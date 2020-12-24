package inheritance;
public class Rectangle extends Shape{
	private double width;
	private double height;
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	public void makeArea() {
		setArea(width*height);
	}
	public void display() {
		System.out.println("가로" + width+", 세로" + height+"인 사각형의 면적은 "+ getArea()+"입니다");
	}
}
