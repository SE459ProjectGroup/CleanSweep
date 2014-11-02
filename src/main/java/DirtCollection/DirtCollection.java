package main.java.DirtCollection;
 

public class DirtCollection {
	
	private int result=0;
	private int dirtCount=0;
	private int dirtLimit=50;
	
	
	
	public DirtCollection()
	{
		dirtCount++;
	}

	public Boolean collectDirt() 
	{
		
		if(dirtCount <= dirtLimit)
		{
			 result= this.dirtCount;
			dirtCount++;
			return true;
		}
		else
		{
	  	return false;
		}
	
	
	}



	public int getCurrentDirt()
	{
		
		return result;
		
		
	}
	
	
	

}

