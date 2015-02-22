package domain;

import ds.VGate;

/**
 * Created by Ashish on 2/21/2015.
 */
public class Bag {
    private String id;
    private VGate entryGate;
    private String flightId;

    public Bag(String id, String entryGate, String flightId) {
        this.id = id;
        this.entryGate = new VGate(entryGate);
        this.flightId = flightId;
    }

    public String getId() {
        return id;
    }

    public VGate getEntryGate() {
        return entryGate;
    }

    public String getFlightId() {
        return flightId;
    }
}
