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
        startTime = -1;
        position = p;
        radius = r;
    }

    public Position getPosition()
    {
        return position;
    }

    public double getRadius(double time, double burnRate)
    {
        if(isStarted(time))
        {
            double t = time - startTime;
            return radius + burnRate * t;
        }
        else
        {
            return radius;
        }
    }
    
    public double getIgnitionTime(Bubble b, double burnRate)
    {
        // don't calculate if the start time of this bubble has not been set
        if(startTime == -1)
        {
            return -1;
        }
        
        // find the edge to edge distance between the bubbles
        double d = position.getDistance(b.getPosition());
        d -= b.getRadius(0, burnRate) + getRadius(0, burnRate);
        
        // find the time it will take to burn that distance
        double t = d / burnRate;
        
        // return the time at which this bubble will ignite the given bubble
        return t + startTime;
    }
    
    public void reduceStartTime(double startTime)
    {
        if(startTime < this.startTime || this.startTime == -1)
        {
            this.startTime = startTime;
        }
    }
    
    public boolean isStarted(double time)
    {
        return startTime != -1 && time >= startTime;
    }
}
