package burn;

/**
 *
 * @author Tobin
 */
public class Bubble
{
    private double radius, startTime;
    private Position position;
    
    public Bubble(Position p, double r)
    {
        position = p;
        radius = r;
    }

    public Position getPosition()
    {
        return position;
    }

    public double getRadius()
    {
        return radius;
    }
}
