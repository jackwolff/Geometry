package geometry;

public class Point 
{
	public final float x;
	public final float y;
	
	public enum Orientation
	{
		Colinear,
		Clockwise,
		CounterClockwise
	}
	
	public Point(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int intX()
	{
		return (int)x;
	}
	
	public int intY()
	{
		return (int)y;
	}
	
	@Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Point))
            return false;

        if(((Point)obj).x == x && ((Point)obj).y == y)
            return true;
        return false;
    }
	
	@Override
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
	
	/*public static Orientation getOrientation(Point a, Point b, Point c) 
	{
		int val = (int)((b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y));
		
		if(val == 0)
			return Colinear;
		return (val > 0) ? Clockwise : CounterClockwise;
	}*/
}
