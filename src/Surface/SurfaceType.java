package Surface;

public class SurfaceType {
	
	
	public static final int BareFloor =1;
	public static final int LowPileCarpet =2;
	public static final int HighPileCarpet =3;
	
	
	/*to Do
	 * read xml file
	 * convert surface type to enum
	 * 
	 */

	public static int  getSurfaceType()
	{
		if (xmlfile.SS == 1)
		{ 
			return SurfaceType.BareFloor;
		}
		
		else if (xmlfile.SS ==2) 
		{
			return SurfaceType.LowPileCarpet;
		} 
		
		else if (xmlfile.SS ==3) 
		{
			return SurfaceType.HighPileCarpet;
		}
		
		else
		{	return 0;
		
		}
		return 0;
		
	}
}
