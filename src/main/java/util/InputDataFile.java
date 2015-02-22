package util;

import domain.Bag;
import domain.Flight;
import ds.BiDirectionalGraph;
import ds.VGate;

import java.io.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ashish on 2/21/2015.
 */
public class InputDataFile implements InputData {

    public static final String SECTION_IDENTIFIER = "#Section";

    private BiDirectionalGraph conveyorSystem;
    private BufferedReader bufferedReader;

    private Map<String, Flight> flights;
    private Map<String, Bag> bags;

    private static final String DATA_SEPARATOR = " ";
    private static final String NEW_LINE = "\n";
    private static final String SECTION_CONVEYOR_SYSTEM = "#Section: Conveyor System";
    private static final String SECTION_DEPARTUURE = "#Section: Departure";
    private static final String SECTION_BAGS = "#Section: Bags";

    private static final int SRC_GATE_INFO_INDEX = 0;
    private static final int DEST_GATE_INFO_INDEX = 1;
    private static final int SRC_DEST_DISTANCE_INFO_INDEX = 2;

    private static final int FLIGHT_ID_INFO_INDEX = 0;
    private static final int FLIGHT_DEPARTURE_GATE_INFO_INDEX = 1;
    private static final int FLIGHT_DEST_INFO_INDEX = 2;
    private static final int FLIGHT_TIME_INFO_INDEX = 3;

    private static final int BAG_ID_INFO_INDEX = 0;
    private static final int BAG_ENTRY_GATE_INFO_INDEX = 1;
    private static final int BAG_FLIGHT_ID_INFO_INDEX = 2;

    public InputDataFile(){
        conveyorSystem = new BiDirectionalGraph();
        flights = new HashMap<String, Flight>();
        bags = new HashMap<String, Bag>();
    }

    @Override
    public Map<String, Flight> getFlights() {
        return flights;
    }

    @Override
    public Map<String, Bag> getBags() {
        return bags;
    }

    @Override
    public BiDirectionalGraph getConveyorSystem() {
        return conveyorSystem;
    }

    public void readData(InputStream stream) throws IOException, ParseException {
        if (stream == null) throw new IllegalArgumentException("Stream can not be null");
        readSections(stream);
    }

    private void readSections(InputStream stream) throws IOException, ParseException {
        bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line;

        line = bufferedReader.readLine();
        while (line != null) {
            if (line.contains(SECTION_CONVEYOR_SYSTEM))
                line = readConveyorSystemSection();
            else if (line.contains(SECTION_BAGS))
                line = readBagsSection();
            else if (line.contains(SECTION_DEPARTUURE))
                line = readDepartureSection();
        }
    }

    private String readConveyorSystemSection() throws IOException {
        String dataString = null;
        String[] conveyorSystemData = null;

        while ((dataString = bufferedReader.readLine()) != null) {
            if (dataString.trim().equals("")) continue;
            if (dataString.contains(SECTION_IDENTIFIER)) break;

            conveyorSystemData = dataString.split(DATA_SEPARATOR);

            VGate srcGate = new VGate(conveyorSystemData[SRC_GATE_INFO_INDEX]);
            VGate destGate = new VGate(conveyorSystemData[DEST_GATE_INFO_INDEX]);

            conveyorSystem.addGate(srcGate);
            conveyorSystem.addGate(destGate);
            conveyorSystem.addEdge(srcGate, destGate, Integer.parseInt(conveyorSystemData[SRC_DEST_DISTANCE_INFO_INDEX]));
        }
        return dataString;
    }

    private String readDepartureSection() throws IOException, ParseException {
        String dataString = null;
        String[] departureData = null;
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Time flightTime = null;
        Flight flight = null;

        while ((dataString = bufferedReader.readLine()) != null) {
            if (dataString.trim().equals("")) continue;
            if (dataString.contains(SECTION_IDENTIFIER)) break;

            departureData = dataString.split(DATA_SEPARATOR);

            flightTime = new Time(timeFormat.parse(departureData[FLIGHT_TIME_INFO_INDEX]).getTime());

            flight = new Flight(departureData[FLIGHT_ID_INFO_INDEX],
                    departureData[FLIGHT_DEPARTURE_GATE_INFO_INDEX],
                    departureData[FLIGHT_DEST_INFO_INDEX],
                    flightTime);

            flights.put(departureData[FLIGHT_ID_INFO_INDEX],
                    flight);
        }
        return dataString;
    }

    private String readBagsSection() throws IOException {
        String dataString = null;
        String[] bagsData = null;
        Bag bag = null;

        while ((dataString = bufferedReader.readLine()) != null) {
            if (dataString.trim().equals("")) continue;
            if (dataString.contains(SECTION_IDENTIFIER)) break;

            bagsData = dataString.split(DATA_SEPARATOR);

            bag = new Bag(bagsData[BAG_ID_INFO_INDEX],
                    bagsData[BAG_ENTRY_GATE_INFO_INDEX],
                    bagsData[BAG_FLIGHT_ID_INFO_INDEX]);

            bags.put(bagsData[BAG_ID_INFO_INDEX], bag);
        }
        return dataString;
    }

}
