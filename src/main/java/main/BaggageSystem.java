package main;


import domain.Bag;
import domain.Flight;
import util.InputData;
import util.InputDataFile;
import ds.BiDirectionalGraph;
import ds.DijkstraAlgorithm;
import ds.OptimalPath;
import ds.VGate;

import java.io.*;
import java.text.ParseException;
import java.util.Map;

/**
 * Created by Ashish on 2/21/2015.
 */
public class BaggageSystem {

    private Map<String, Bag> bags;
    private Map<String, Flight> flights;
    private BiDirectionalGraph conveyorSystem;
    private InputData inputData;

    public BaggageSystem(InputStream stream) throws IOException, ParseException {
        initializeConveyorSystem(stream);
    }

    public OptimalPath<VGate> getShortestPath(Bag bag){
        if(!bags.containsKey(bag.getId())) throw new IllegalArgumentException("bag with Id : " + bag.getId() + " doesn't exist");
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(conveyorSystem);
        dijkstra.execute(bag.getEntryGate());
        OptimalPath<VGate> path = dijkstra.getPath(flights.get(bag.getFlightId()).getDepratingFromGate());
        return path;
    }

    public void addFlight(Flight flight){
        flights.put(flight.getId(), flight);
    }

    public void addBag(Bag bag){
        if(!flights.containsKey(bag.getFlightId())) throw new IllegalArgumentException("No flight scheduled with Id : " + bag.getFlightId());
        bags.put(bag.getId(), bag);
    }

    private void initializeConveyorSystem(InputStream stream) throws IOException, ParseException {
        inputData = new InputDataFile();
        inputData.readData(stream);
        conveyorSystem = inputData.getConveyorSystem();
        flights = inputData.getFlights();
        bags = inputData.getBags();
    }
}
