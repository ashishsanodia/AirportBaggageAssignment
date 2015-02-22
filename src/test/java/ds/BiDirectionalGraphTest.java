package ds;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Ashish on 2/21/2015.
 */
public class BiDirectionalGraphTest {

    private BiDirectionalGraph graph;
    private DijkstraAlgorithm dijkstra;

    @Before
    public void setup(){
        graph = new BiDirectionalGraph();
        for (int i = 1; i < 11; i++) {
            VGate location = new VGate("A" + i);
            graph.addGate(location);
        }
        graph.addGate(new VGate("Concourse_A_Ticketing"));
        graph.addGate(new VGate("BaggageClaim"));

        graph.addEdge(new VGate("Concourse_A_Ticketing"), new VGate("A5"), 5);
        graph.addEdge(new VGate("A5"), new VGate("BaggageClaim"), 5);
        graph.addEdge(new VGate("A5"), new VGate("A10"), 4);
        graph.addEdge(new VGate("A5"), new VGate("A1"), 6);
        graph.addEdge(new VGate("A1"), new VGate("A2"), 1);
        graph.addEdge(new VGate("A2"), new VGate("A3"), 1);
        graph.addEdge(new VGate("A3"), new VGate("A4"), 1);
        graph.addEdge(new VGate("A10"), new VGate("A9"), 1);
        graph.addEdge(new VGate("A9"), new VGate("A8"), 1);
        graph.addEdge(new VGate("A8"), new VGate("A7"), 1);
        graph.addEdge(new VGate("A7"), new VGate("A6"), 1);

        dijkstra = new DijkstraAlgorithm(graph);
    }


    @Test
    public void shouldGiveOptimalPath() {
        dijkstra.execute(new VGate("Concourse_A_Ticketing"));
        OptimalPath<VGate> path = dijkstra.getPath(new VGate("A1"));

        assertThat("Concourse_A_Ticketing A5 A1 : 11", is(path.toString()));

        dijkstra.execute(new VGate("A5"));
        path = dijkstra.getPath(new VGate("A4"));

        assertThat("A5 A1 A2 A3 A4 : 9", is(path.toString()));

        dijkstra.execute(new VGate("A2"));
        path = dijkstra.getPath(new VGate("A1"));

        assertThat("A2 A1 : 1", is(path.toString()));

        dijkstra.execute(new VGate("A8"));
        path = dijkstra.getPath(new VGate("A5"));

        assertThat("A8 A9 A10 A5 : 6", is(path.toString()));

        dijkstra.execute(new VGate("A7"));
        path = dijkstra.getPath(new VGate("BaggageClaim"));

        assertThat("A7 A8 A9 A10 A5 BaggageClaim : 12", is(path.toString()));
    }
}