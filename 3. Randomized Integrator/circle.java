public class circle extends Shape {
	private double x1, y1, r;

	public circle () {
		this(new Point(0, 0), 1.0);
	}
	public circle (Point p1, double r) {
		this.x1 = p1.getX();
		this.y1 = p1.getY();
		this.r = r;
	}
	public circle (double x1, double y1, double r) {
		this.x1 = x1;
		this.y1 = y1;
		this.r = r;
	}

	public double x1() {
		return this.x1;
	}

	public double y1() {
		return this.y1;
	}

	public double r() {
		return this.r;
	}

	public double getArea() {
		return Math.PI * r * r;
	}

	public boolean inCircle(Point p) {
		return (Math.sqrt((p.getX() - this.x1)*(p.getX() - this.x1) + (p.getY() - this.y1)*(p.getY() - this.y1)) < this.r);
	}

	public Rectangle box() {
        return new Rectangle(this.x1() + this.r(), this.x1() - this.r(), this.y1() + this.r(), this.y1() - this.r());
    }

	public String toString() {
		return "Circle: (" + this.x1 + ", " + this.y1 + ") r: " + this.r;
	}
}