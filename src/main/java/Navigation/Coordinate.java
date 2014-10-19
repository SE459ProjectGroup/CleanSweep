package main.java.Navigation;

public class Coordinate {

	private int x;
	
	private int y;

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
	
	public Coordinate(int x, int y) {
		this.setX(x);
		this.setY(y);
		
	}
	
	@Override
	public String toString() {
		
		return "Coordinate: x = " + this.getX() + ", y = " + this.getY();
		
	}
	
	@Override
	public boolean equals(Object obj) {
		try	{
			
			if (obj instanceof Coordinate ) {
				Coordinate co = (Coordinate)obj;
				if (this.getX() == co.getX() && this.getY() == co.getY()) {
					return true;
					
				}
				
			}
			
		} catch(Exception e) {
			//maybe a null reference exception
			
		}
		return false;
		
	}
	
}
