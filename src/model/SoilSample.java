package model;

import model.enums.Colour;
import model.enums.Moisture;
import model.enums.Strat;

public class SoilSample {
    private String id;
    private int begDepth;
    private int endDepth;
    private Colour colour;
    private Strat strat;
    private Moisture moisture;
    private boolean odour;

    public SoilSample(String id, int begDepth, int endDepth, Colour colour, Strat strat, Moisture moisture, boolean odour){
        this.id = id;
        this.begDepth = begDepth;
        this.endDepth = endDepth;
        this.colour = colour;
        this.strat = strat;
        this.odour = odour;
        this.moisture = moisture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getBegDepth() {
        return begDepth;
    }

    public void setBegDepth(int begDepth) {
        this.begDepth = begDepth;
    }

    public int getEndDepth() {
        return endDepth;
    }

    public void setEndDepth(int endDepth) {
        this.endDepth = endDepth;
    }
}
