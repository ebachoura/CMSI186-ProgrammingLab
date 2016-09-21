public class triangle extends Shape {
	private double x1, y1, x2, y2, x3, y3;
	private Point p1, p2, p3;

	public triangle () {
		this(new Point(0,0), new Point(1,1), new Point(1,0));
	}

	public triangle (Point p1, Point p2, Point p3) {
		this.x1 = p1.getX();
		this.y1 = p1.getY();
		this.x2 = p2.getX();
		this.y2 = p2.getY();
		this.x3 = p3.getX();
		this.y3 = p3.getY();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	public triangle (double x1, double y1, double x2, double y2, double x3, double y3) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.p1 = new Point (x1, y1);
		this.p2 = new Point (x2, y2);
		this.p3 = new Point (x3, y3);
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

	public double x3() {
		return this.x3;
	}

	public double y3() {
		return this.y3;
	}

	public Point p1() {
		return this.p1;
	}

	public Point p2() {
		return this.p2;
	}

	public Point p3() {
		return this.p3;
	}
	public double getArea() {
		return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
	}

	public boolean inTriangle(Point p) {
		triangle a = new triangle(this.p1, this.p2, p);
		triangle b = new triangle(this.p1, p, this.p3);
		triangle c = new triangle(p, this.p2, this.p3);
		return ((a.getArea() + b.getArea() + c.getArea()) == this.getArea());
	}

	public Rectangle box() {
		double largestX = 0;
        double largestY = 0;
        double smallestX= 0;
        double smallestY= 0;

        largestX = this.x1 > largestX? this.x1 : largestX;
        largestX = this.x2 > largestX? this.x2 : largestX;
        largestX = this.x3 > largestX? this.x3 : largestX;

        smallestX = this.x1 < smallestX? this.x1 : smallestX;
        smallestX = this.x2 < smallestX? this.x2 : smallestX;
        smallestX = this.x3 < smallestX? this.x3 : smallestX;

        largestY = this.y1 > largestY? this.y1 : largestY;
        largestY = this.y2 > largestY? this.y2 : largestY;
        largestY = this.y3 > largestY? this.y3 : largestY;

        smallestY = this.y1 < smallestY? this.y1 : smallestY;
        smallestY = this.y2 < smallestY? this.y2 : smallestY;
        smallestY = this.y3 < smallestY? this.y3 : smallestY;

		return new Rectangle(smallestX, largestX, smallestY, largestY);
	}

	public String toString() {
		return "Triangle: (" + this.x1 + ", " + this.y1 + "), (" + this.x2 + ", " + this.y2 + "), (" + this.x3 + ", " + this.y3 + ")";
	}
}