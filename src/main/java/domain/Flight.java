package domain;

import ds.VGate;

import java.sql.Time;

/**
 * Created by Ashish on 2/21/2015.
 */
public class Flight {
    private String id;
    private VGate depratingFromGate;
    private String destination;
    private Time time;

    public Flight(String id, String depratingFromGate, String destination, Time time) {
        this.id = id;
        this.depratingFromGate = new VGate(depratingFromGate);
        this.destination = destination;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public VGate getDepratingFromGate() {
        return depratingFromGate;
    }

    public String getDestination() {
        return destination;
    }

    public Time getTime() {
        return time;
    }
}
