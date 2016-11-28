package entities;

public class TilePair {

	protected Location location;
	protected int orientation;
	
	
	TilePair() { 
		
	}
	
	TilePair(Location location, int orientation) { 
		this.location = location;
		this.orientation = orientation;
	}
	
	public Location getLocation() { 
		return location;
	}
	
	public int getOrientation() { 
		return orientation;
	}
	
	public void setLocation(Location location) { 
		this.location = location;
	}
	
	public void setOrientation(int orientation) { 
		this.orientation = orientation;
	}

	@Override
	public String toString() {
		
		return location + ": " + orientation;
		
	}
}
