package burn;

/**
 * Represents a circular core in a grain.
 * @author Tobin
 */
public class Core
{
    private double diameter;
    
    public Core(double diameter)
    {
        this.diameter = diameter;
    }
    
    public double getDistance(Position p)
    {
        return Math.sqrt(p.getX()*p.getX() + p.getY()*p.getY());
    }
}
