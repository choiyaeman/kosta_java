public class Rectangle {
	private double width;
	private double height;
	private double area;
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	public void makeArea() {
		area = width*height;
	}
	public void display() {
		System.out.println("가로" + width+", 세로" + height+"인 사각형의 면적은 "+ area+"입니다");
	}
}
