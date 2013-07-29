package burn;

/**
 *
 * @author Tobin
 */
public class Position
{
    private double x, y, z;
    
    public Position(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    @Override
    public String toString()
    {
        return "("+x+", "+y+", "+z+")";
    }
    
    /**
     * Gets the distance between this point and the given point.
     * @param p The point to get the distance to
     * @return The distance between the two points
     */
    public double getDistance(Position p)
    {
        return Math.sqrt((p.x - x)*(p.x - x) +
                         (p.y - y)*(p.y - y) +
                         (p.z - z)*(p.z - z));
    }
}
