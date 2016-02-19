package pmf.math.oaa.hw;

import java.util.Comparator;

public class Point {


	public static final Comparator<Point> BY_X = 
			(p, q) -> new Double(p.getX()).compareTo(new Double(q.getX()));
	public static final Comparator<Point> BY_Y = 
			(p, q) -> new Double(p.getY()).compareTo(new Double(q.getY()));
	public static final Comparator<Point> BY_XY = Point.BY_X.thenComparing(Point.BY_Y);
	public static final Comparator<Point> BY_YX = Point.BY_Y.thenComparing(Point.BY_X);
	
	private double x;
	private double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
