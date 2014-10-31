package main.java.DirtCollection;
 

public class DirtCollection {
	
	private int result=0;
	private int dirtCount=0;
	private int dirtLimit=50;
	
	public DirtCollection()
	{
		dirtCount++;
	}

	public void collectDirt()
	{
	

		if (dirtCount <= dirtLimit)
		{
			 result= this.dirtCount;
			System.out.println("current dirt "+ result);
			dirtCount++;
		}
		else 
		{
			
		//container is full
		System.out.println("Empty Me..");
			
		}
		
			
	}

	
	public int getCurrentDirt()
	{
		
		return result;
		
		
	}
	
	
}
