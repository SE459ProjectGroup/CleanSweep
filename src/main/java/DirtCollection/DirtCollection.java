package main.java.DirtCollection;
 

public class DirtCollection {
	
	private int result=0;
	private int dirtCount=0;
	private int dirtLimit=50;

	
	public DirtCollection()
	{
		//dirt collection count should start at 0
		//dirtCount++;
	}

	public Boolean collectDirt() 
	{
		if(this.isFull() == false)
		{
			 
			dirtCount++;
			result= this.dirtCount;
			return true;
		}
		else
		{
			indicateCleanSweepIsFull();
			return false;
		}
	
	
	}



	private void indicateCleanSweepIsFull() {
		System.out.println("Clean Sweep Is Full!");
	}

	public int getCurrentDirt()
	{
		
		return result;
		
		
	}

	public int getDirtCount() {
		return dirtCount;
	}

	public void setDirtCount(int dirtCount) {
		this.dirtCount = dirtCount;
	}

	/*
	 * Empty the contents of the dirt collection.
	 * 
	 */
	public void empty() {
		/*
		 * An alternative approach would be to have a delegate
		 * call empty(because really a dirt collector can't really empty itself). It would
		 * call an object capable of taking the dirt and putting it somewhere else. 
		 * 
		 * This is the most simple implementation though.
		 */
		this.dirtCount = 0;
		
	}

	/**
	 * Get a boolean indicating if the dirt collector is currently full
	 * @return true if full, false if not
	 */
	public boolean isFull() {
		return (this.dirtCount >= dirtLimit);
	}
	
	
	

}

