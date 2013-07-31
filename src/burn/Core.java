package burn;

/**
 * Represents a circular core in a grain.
 * @author Tobin
 */
public class Core
{
    private double radius;
    
    public Core(double diameter)
    {
        this.radius = diameter / 2;
    }
    
    /**
     * Gets the smallest distance between the center of the core and the given
     * position.
     * @param p The position to calculate the distance from
     * @return The distance from the core to the given position
     */
    public double getDistance(Position p)
    {
        return Math.sqrt(p.getX()*p.getX() + p.getY()*p.getY());
    }
    
    /**
     * Gets the time it would take for the core to ignite the given bubble in
     * the presence of no other bubbles.
     * @param b The bubble to calculate the ignition time of
     * @param burnRate The burn rate of the grain
     * @return The time from the ignition of the core at which the bubble will
     * ignite
     */
    public double getIgnitionTime(Bubble b, double burnRate)
    {
        double d = getDistance(b.getPosition()) - radius - b.getRadius(0, burnRate);
        return d / burnRate;
    }
    
    public double getVolume(double time, double height, double burnRate)
    {
        double r = radius + burnRate * time;
        return Math.PI * r * r * height;
    }

    double getRadius()
    {
        return radius;
    }
}
