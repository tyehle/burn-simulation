package burn;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author tobin
 */
public class Grain
{
    private double diameter, height, burnRate;
    private Core core;
    private ArrayList<Bubble> bubbles;
    
    public Grain(double diameter, double height, Core c, double burnRate)
    {
        this.diameter = diameter;
        this.height = height;
        this.core = c;
        this.burnRate = burnRate;
        bubbles = new ArrayList<>();
    }
    
    /**
     * Adds bubbles to the grain, resulting in a reduction in volume. The
     * bubbles are placed in random locations, and their radiuses are normally
     * distributed.  To specify a target grain volume reduction of one quarter
     * pass in .75 as the targetVolumeScalar.  This would mean the grain would
     * weigh .75 of what it did before adding the bubbles.
     * @param targetVolumeScalar The ratio of the new volume of the grain to the
     * old volume
     * @param meanRadius The average radius of the bubbles added to the grain
     * @param stdevRadius The standard deviation in the radius of the bubbles
     */
    public void addBubbles(double targetVolumeScalar,
                           double meanRadius,
                           double stdevRadius)
    {
        double grainVol = Math.PI * (diameter/2) * (diameter/2) * height -
                core.getVolume(0, height, burnRate);
        double totalBubbleVol = (1-targetVolumeScalar) * grainVol;
        int numBubbles = (int)Math.ceil(totalBubbleVol /
                (4.0/3.0 * Math.PI * meanRadius*meanRadius*meanRadius));
        
        Random r = new Random();
        for(int i = 0; i < numBubbles; i++)
        {
            double radius = r.nextGaussian()*stdevRadius + meanRadius;
            if(radius <= 0)
            {
                System.out.println("Negative radius, ommitting bubble");
                continue;
            }
            
            double mag = r.nextDouble() *
                    (diameter/2 - core.getRadius()) + core.getRadius();
            double theta = r.nextDouble() * 2 * Math.PI;
            double x = mag * Math.cos(theta);
            double y = mag * Math.sin(theta);
            double z = r.nextDouble() * height;
            bubbles.add(new Bubble(new Position(x, y, z), radius));
        }
    }
    
    public double getFaceIgnitionTime(Bubble b)
    {
        // find the distance from the face of the grain to the edge of the
        // bubble
        double d = Math.min(b.getPosition().getZ(),
                height - b.getPosition().getZ());
        d -= b.getRadius(0, burnRate);
        return d / burnRate;
    }
    
    public void burn()
    {
        // set all initial start times
        for(Bubble b : bubbles)
        {
            b.reduceStartTime(getFaceIgnitionTime(b));
            b.reduceStartTime(core.getIgnitionTime(b, burnRate));
        }
        
        // n**2 on num bubbles
        Bubble nextBubble = bubbles.get(0);
        while(nextBubble != null)
        {
            // set nextBubble
            for(Bubble b : bubbles)
            {
                // this is a total fail.
                if(b.getStartTime() < nextBubble.getStartTime())
                {
                    nextBubble = b;
                }
            }
        }
    }
}
