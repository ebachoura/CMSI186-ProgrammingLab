import java.util.Random;

public class Point {
	private double x, y;

	public Point (Rectangle rect) {
		this.x = Math.random() * rect.getWidth()+rect.x1();
		this.y = Math.random() * rect.getHeight()+rect.y1();
	}

	public Point (double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}

	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}