/* 
 * AUTHOR: Dhanush Giriyan
 * DESCRIPTION: This is the cell node class that is used to represent each cell in the grid.
 * CLASS: CSE 205 - Summer 2020
 * NAME: ASSIGNMENT #4
 * DATE OF COMPLETION: 05/07/2020
 * 
 */

public class CellNode implements Comparable <CellNode>{
	
	private CellNode parent;
	private int x;
	private int y;
	
	private double g;
	private double h;
	private double f;
	
	public CellNode (int x, int y, CellNode myParent) {
		this.parent = myParent;
		this.x = x;
		this.y = y;
		
		this.g = 0;
		this.h = 0;
		this.f = 0;
	}
	
	public int getX() {
		return this.x;	
	}
	
	public int getY () {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY (int y) {
		this.y = y;
	}
	
	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getF() {
		return f;
	}

	public void setF(double f) {
		this.f = f;
	}
	
	public CellNode getParent() {
		return parent;
	}

	public void setParent(CellNode parent) {
		this.parent = parent;
	}
	
	@Override
	public int compareTo (CellNode node) {
		if ((this.x == node.getX()) && (this.y == node.getY())) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public String toString () {
		return (this.x + ", " + this.y);
	}
	
}
