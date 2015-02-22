package main;

import domain.Bag;
import domain.Flight;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Ashish on 2/23/2015.
 */
public class BaggageSystemTest {

    BaggageSystem baggageSystem;

    @Before
    public void setup() throws IOException, ParseException {
        String inputData = "#Section: Conveyor System\n" +
                "\n" +
                "Concourse_A_Ticketing A5 5\n" +
                "A5 BaggageClaim 5\n" +
                "A5 A10 4\n" +
                "A5 A1 6\n" +
                "A1 A2 1\n" +
                "A2 A3 1\n" +
                "A3 A4 1\n" +
                "A10 A9 1\n" +
                "A9 A8 1\n" +
                "A8 A7 1\n" +
                "A7 A6 1\n" +
                "\n" +
                "#Section: Departure\n" +
                "\n" +
                "UA10 A1 MIA 08:00\n" +
                "UA11 A1 LAX 09:00\n" +
                "UA12 A1 JFK 09:45\n" +
                "UA13 A2 JFK 08:30\n" +
                "UA14 A2 JFK 09:45\n" +
                "UA15 A2 JFK 10:00\n" +
                "UA16 A3 JFK 09:00\n" +
                "UA17 A4 MHT 09:15\n" +
                "UA18 A5 LAX 10:15\n" +
                "\n" +
                "#Section: Bags\n" +
                "\n" +
                "0001 Concourse_A_Ticketing UA12\n" +
                "0002 A5 UA17\n" +
                "0003 A2 UA10\n" +
                "0004 A8 UA18\n" +
                "0005 A7 ARRIVAL";
        InputStream inputStream = new ByteArrayInputStream(inputData.getBytes(StandardCharsets.UTF_8));
        baggageSystem = new BaggageSystem(inputStream);
    }

    @Test
    public void shouldGiveOptimalPathAndWieght() {
        Bag bag = new Bag("0002", "A5", "UA17");
        assertThat(baggageSystem.getShortestPath(bag).toString(), is("A5 A1 A2 A3 A4 : 9"));
    }

    @Test
    public void addFlight() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Time flightTime = new Time(dateFormat.parse("10:00").getTime());
        Flight flight = new Flight("UA09", "A1", "MIA", flightTime);
        baggageSystem.addFlight(flight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnAddingBagWithNonExistFlight(){
        Bag bag = new Bag("0006", "A5", "UA70");
        baggageSystem.addBag(bag);
    }

    @Test
    public void shouldAddBagForNewlyAddedFlight() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Time flightTime = new Time(dateFormat.parse("10:00").getTime());
        Flight flight = new Flight("UA08", "A1", "MIA", flightTime);
        baggageSystem.addFlight(flight);
        Bag bag = new Bag("0006", "A5", flight.getId());
        baggageSystem.addBag(bag);
    }
}
