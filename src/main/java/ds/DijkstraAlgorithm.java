package ds;

import java.util.*;

/**
 * Created by Ashish on 2/21/2015.
 */
public class DijkstraAlgorithm {

    private final List<VGate> nodes;
    private final List<Edge> edges;
    private Set<VGate> settledNodes;
    private Set<VGate> unSettledNodes;
    private Map<VGate, VGate> predecessors;
    private Map<VGate, Integer> distance;

    public DijkstraAlgorithm(BiDirectionalGraph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<VGate>(graph.getGates());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(VGate source) {
        settledNodes = new HashSet<VGate>();
        unSettledNodes = new HashSet<VGate>();
        distance = new HashMap<VGate, Integer>();
        predecessors = new HashMap<VGate, VGate>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            VGate node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(VGate node) {
        List<VGate> adjacentNodes = getNeighbors(node);
        for (VGate target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(VGate node, VGate target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<VGate> getNeighbors(VGate node) {
        List<VGate> neighbors = new ArrayList<VGate>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private VGate getMinimum(Set<VGate> nodes) {
        VGate minimum = null;
        for (VGate node : nodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getShortestDistance(node) < getShortestDistance(minimum)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(VGate node) {
        return settledNodes.contains(node);
    }

    private int getShortestDistance(VGate destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public OptimalPath<VGate> getPath(VGate target) {
        OptimalPath<VGate> optimalPath = null;
        LinkedList<VGate> path = new LinkedList<VGate>();
        VGate step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }

        // Put it into the correct order
        Collections.reverse(path);

        optimalPath = new OptimalPath<VGate>(path, distance.get(target));

        return optimalPath;
    }
}


