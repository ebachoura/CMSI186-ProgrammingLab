public class Rectangle {
	private double x1, y1, x2, y2;

	public Rectangle () {
		this(new Point(0,0), new Point(1,1));
	}
	public Rectangle (Point p1, Point p2) {
		this.x1 = p1.getX();
		this.y1 = p1.getY();
		this.x2 = p2.getX();
		this.y2 = p2.getY();
	}
	public Rectangle (double x1, double x2, double y1, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public double x1() {
		return this.x1;
	}

	public double y1() {
		return this.y1;
	}

	public double x2() {
		return this.x2;
	}

	public double y2() {
		return this.y2;
	}

	public double getWidth() {
		return Math.abs(this.x2 - this.x1);
	}
	
	public double getHeight() {
		return Math.abs(this.y2 - this.y1);
	}

	public double getArea() {
		return this.getWidth() * this.getHeight();
	}

	public String toString() {
		return "Rectangle: (" + this.x1 + ", " + this.y1 + ")" + " (" + this.x2 + ", " + this.y2 + ")";
	}
}