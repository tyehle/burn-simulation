package burn;

/**
 *
 * @author Tobin
 */
public class BurnSimulation
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("Hello world");
        Grain g = new Grain(3, 5, new Core(5.0/8.0), 1);
        g.addBubbles(.95, .01, .003);
        g.burn();
    }
}
