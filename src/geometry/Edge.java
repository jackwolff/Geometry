package geometry;

public class Edge 
{
	private final Point a, b;
	
	public Edge(Point[] point)throws GeometryException
	{
		if(point.length != 2)
			throw new GeometryException("Edge Error; Edge(IntPoint[] point) point.length "
					+ "is not two.");
		else if(point[0].equals(point[1]))
			throw new GeometryException("Edge Error; Edge(IntPoint[] point) points are equal.");
		
		a = point[0];
		b = point[1];
	}
	
	public Edge(Point point1, Point point2) throws GeometryException
	{
		this(new Point[] { point1, point2 } );
	}
	
	/**
	 * Get both of the points that make up this edge.
	 * @return An array of two that holds two <u>UNORDERED</u> points.
	 */
	public final Point[] getPoints()
	{
		return new Point[] { a, b };
	}
	
	public boolean isHorizontal()
	{
		if(a.y == b.y)
			return true;
		return false;
	}
	
	public boolean isVertical()
	{
		if(a.x == b.x)
			return true;
		return false;
	}
	
	public Point getMidPoint()
    {
        return new Point((a.x + b.x) / 2,
                (a.y + b.y) / 2);
    }
	
	public float slope()
    {
        return (b.y - a.y) /
                (b.x - a.x);
    }
	
	public Edge getPerpendicularBisector() throws GeometryException
    {
        //Get the midpoint
        Point midPoint = getMidPoint();
        //Find the slope
        float slope;
        //If this line is vertical, the perpendicular
        //bisector will have a slope of 0
        if (isVertical())
            slope = 0;
        else if (isHorizontal())//If it is horizontal, return a line that is vertical
            return new Edge(new Point[] { new Point(midPoint.intX(), midPoint.intY() - 20),
                    new Point(midPoint.intX(), midPoint.intY() + 20) } );
        else//Normal slope
            slope = -1 / slope();

        //Calculate two points on the perpendicular bisector to create an edge
        float b = midPoint.intY() - (slope * midPoint.intX());
        float leftX = midPoint.intX() - 20;
        float leftY = (slope * leftX) + b;
        float rightX = midPoint.intX() + 20;
        float rightY = (slope * rightX) + b;

        return new Edge(new Point[] { new Point(leftX, leftY),
                new Point(rightX, rightY) } );
    }
	
	public Point getIntersectPoint(Edge e)throws GeometryException
    {
		//If the two edges are equal, or have the same slope
		if(e.equals(this))
			throw new InfinitePointException("Edge Error; getInterceptPoint(Edge b), edges are equal.");
		else if (slope() == e.slope() || (e.isVertical() && isVertical()) || (e.isHorizontal() && isHorizontal()))
            throw new GeometryException("Edge Error; getInterceptPoint(Edge b), Intecerpt Point does not exist.\n" +
                    "EdgeA: " + toString() + "\nEdgeB: " + e.toString());
		
		//This line is vertical
		if(isVertical())
			return new Point(a.x, (e.slope() * a.x) + e.yIntercept());
		else if(e.isVertical())
			return new Point(e.a.x, (slope() * e.a.x) + yIntercept());

        float x = (e.yIntercept() - yIntercept()) / (slope() - e.slope());
        float y = (x * slope()) + yIntercept();
        return new Point(x, y);
    }
	
	public float yIntercept()
    {
        if (isVertical())
            return Float.NaN;

        float yInt = a.y - (slope() * a.x);

        return yInt;
    }
	
	public Point leftPoint()
	{
		if(a.x < b.x)
			return a;
		return b;
	}
	
	public Point rightPoint()
	{
		if(a.x > b.x)
			return a;
		return b;
	}
	
	public Point topPoint()
	{
		if(a.y > b.y)
			return a;
		return b;
	}
	
	public Point bottomPoint()
	{
		if(a.y < b.y)
			return a;
		return b;
	}
	
	public boolean intersects(Edge e) throws GeometryException
	{
		Point p = getIntersectPoint(e);
		
		if(p.x >= e.leftPoint().x && p.x <= e.rightPoint().x &&
			p.y >= e.bottomPoint().y && p.y <= e.topPoint().y)
			if(p.x >= leftPoint().x && p.x <= rightPoint().x &&
				p.y >= bottomPoint().y && p.y <= topPoint().y)
				return true;
		
		return false;
	}
	
	/**
	 * Check if Point p is an end point for this edge
	 * @param p point to check
	 * @return returns true if p is one of the end points
	 */
	public boolean endPoint(Point p)
	{
		if(GeometryUtility.withinDistance(p, a, .005f) || 
				GeometryUtility.withinDistance(p, b, .005f))
			return true;
		
		return false;
	}
	
	@Override
	public String toString()
	{
		return "(" + a.intX() + "," + a.intY() + ") "
				+ "(" + b.intX() + "," + b.intY() + ")";
	}
	
	@Override
	public boolean equals(Object o)
    {
        if (!(o instanceof Edge))
            return false;

        if (((Edge)o).getPoints()[0].equals(a) && ((Edge)o).b.equals(b))
            return true;    

        return false;
    }
}
