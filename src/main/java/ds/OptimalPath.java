package ds;

import java.util.LinkedList;

/**
 * Created by Ashish on 2/22/2015.
 */
public class OptimalPath<T> {

    private LinkedList<T> path;
    private Integer weight;

    public OptimalPath(LinkedList<T> path, Integer weight) {
        this.path = path;
        this.weight = weight;
    }

    public LinkedList<T> getPath() {
        return path;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        StringBuilder returnValue = new StringBuilder();
        for (T VGate : path) {
            returnValue.append(VGate + " ");
        }
        returnValue.append(": ").append(weight);
        return returnValue.toString();
    }
}
