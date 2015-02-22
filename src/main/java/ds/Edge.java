package ds;

/**
 * Created by Ashish on 2/21/2015.
 */
public class Edge  {
    private final VGate source;
    private final VGate destination;
    private final int weight;

    public Edge(VGate source, VGate destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public VGate getDestination() {
        return destination;
    }

    public VGate getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}
