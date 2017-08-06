package geometry;

public class Triangle 
{
	private final Point[] point;
	
	public Triangle(Point[] point) throws GeometryException
	{
		if(point.length != 3)
			throw new GeometryException("Triangle Error; Triangle(IntPoint[] point), "
					+ "point.length is not 3.");
		
		this.point = point;
	}
	
	public Point[] getPoints()
	{
		return point;
	}
}
