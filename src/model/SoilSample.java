package model;

import model.enums.Colour;
import model.enums.Moisture;
import model.enums.Strat;

public class SoilSample {
    private String id;
    private Colour colour;
    private Strat strat;
    private boolean odour;
    private Moisture moisture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    SoilSample(String id, Colour colour, Strat strat, boolean odour, Moisture moisture){
        this.colour = colour;
        this.strat = strat;
        this.odour = odour;
        this.moisture = moisture;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Strat getStrat() {
        return strat;
    }

    public void setStrat(Strat strat) {
        this.strat = strat;
    }

    public boolean isOdour() {
        return odour;
    }

    public void setOdour(boolean odour) {
        this.odour = odour;
    }

    public Moisture getMoisture() {
        return moisture;
    }

    public void setMoisture(Moisture moisture) {
        this.moisture = moisture;
    }
}
