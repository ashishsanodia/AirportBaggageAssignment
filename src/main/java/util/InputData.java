package util;

import domain.Bag;
import domain.Flight;
import ds.BiDirectionalGraph;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Map;

/**
 * Created by Ashish on 2/21/2015.
 */
public interface InputData {

    Map<String, Flight> getFlights();

    Map<String, Bag> getBags();

    BiDirectionalGraph getConveyorSystem();

    void readData(InputStream stream) throws IOException, ParseException;
}
