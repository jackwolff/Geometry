package geometry;

public class GeometryUtility 
{
	public static float getDistance(float x1, float y1, float x2, float y2)
	{
		return (float)Math.sqrt(Math.pow(x1 - x2, 2) + 
				Math.pow(y1 - y2, 2));
	}
	
	public static boolean withinDistance(float x1, float y1, float x2, float y2, float distance)
	{
		if(getDistance(x1, y1, x2, y2) <= distance)
			return true;
		return false;
	}
	
	public static boolean withinDistance(Point p1, Point p2, float distance)
	{
		if(getDistance(p1.x, p1.y, p2.x, p2.y) <= distance)
			return true;
		return false;
	}
}
