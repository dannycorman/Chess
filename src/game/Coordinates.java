package game;

import java.io.Serializable;

public class Coordinates implements Serializable {
	//Cordinates are what pieces use to keep track of where they exist on the board
	
	private final int x;
	private final int y;
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getx() {return x; }
	public int gety() {return y; }
	
	//public void setx(int x) {this.x = x;}
	//public void sety(int y) {this.y = y;}
	
	public boolean outOfBounds(){
		if (this.x > 8 || this.x <1 || this.y > 8 || this.y <1){
			return true;
		}
		return false;
	}
	
	public Coordinates addCords(Coordinates b){
		int abx = this.x+b.getx();
		int aby = this.y+b.gety();
		
		Coordinates c = new Coordinates(abx,aby);
		
		
		return c;
	}

	public Coordinates cloneCord (){
		Coordinates clonedCord = new Coordinates(this.x, this.y);
		return clonedCord;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	

}
