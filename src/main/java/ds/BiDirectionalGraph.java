package ds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish on 2/21/2015.
 */
public class BiDirectionalGraph {
    private final List<VGate> gates;
    private final List<Edge> edges;

    public BiDirectionalGraph() {
        this.gates = new ArrayList<VGate>();
        this.edges = new ArrayList<Edge>();
    }

    public BiDirectionalGraph(List<VGate> gates, List<Edge> edges) {
        this.gates = gates;
        this.edges = edges;
    }

    public List<VGate> getGates() {
        return gates;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(VGate srcGate, VGate destGate, int travelTime){
        Edge edge1 = new Edge(srcGate, destGate, travelTime);
        this.edges.add(edge1);
        Edge edge2 = new Edge(destGate, srcGate, travelTime);
        this.edges.add(edge2);
    }

    public void addGate(VGate gate){
        if(!gates.contains(gate))
            this.gates.add(gate);
    }
}