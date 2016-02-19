package pmf.math.oaa.hw;

public class Pair{
			
	private Point p;
	private Point q;
	private double distance;
	
	public Pair(Point p, Point q) {
		this.p = p;
		this.q = q;
		this.distance = -1;
	}
	
	public Pair(double px, double py, double qx, double qy) {
		this.p = new Point(px, py);
		this.q = new Point(qx, qy);
		this.distance = -1;
	}
	
	public Point getP() {
		return p;
	}
	
	public Point getQ() {
		return q;
	}
	
	public double distance() {
		if(distance > -1) {
			return distance;
		}
		return Math.hypot(q.getX() - p.getX(), q.getY() - p.getY());
	}
	
	@Override
	public String toString() {
		String repr = p.toString() + " - " + q.toString() + System.getProperty("line.separator") 
			+ "distance: " + distance();
		if(distance() > 10000) repr += System.getProperty("line.separator") + "INFINITY";
		return repr;
	}
}
