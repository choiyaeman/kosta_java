
public class Triangle {
	private double base;//밑변
	private double height;//높이
	private double area;
	public Triangle() {
		
	}
	public Triangle(double base, double height) {
		setBase(base);
		this.height = height;
	}
	public void setBase(double base) {
		if(base <= 0) {
			System.out.println("밑변은 0이상이어야합니다");
			return;
		}
		this.base = base;
	}
	public void makeArea() {
		this.area = base * height / 2;
	}
	public void display() {
		System.out.println("밑변"+base+"이고 높이"+ height+"인 이등변삼각형의 면적은" + area+"입니다" );
	}
}
