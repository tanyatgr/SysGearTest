package task4;

public class Point {

	private int x;	//x coordinate
	private int y; //y coordinate

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(String x, String y) {
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/*
	 * Returns the angle in radians between this point and that point
	 */
	public double getAngle(Point that) {
		double dx = that.x - this.x;
        double dy = that.y - this.y;
        return Math.atan2(dy, dx);
	}

}
