package model;


import java.util.List;

public class Borehole {

    private String id;
    private List<SoilSample> soilSamples;

    public Borehole(String id){
        this.id = id;
        soilSamples = null;
    }

    public Borehole(String id, List<SoilSample> soilSamples){
        this.id = id;
        this.soilSamples = soilSamples;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SoilSample> getSoilSamples() {
        return soilSamples;
    }

    public void setSoilSamples(List<SoilSample> soilSamples) {
        this.soilSamples = soilSamples;
    }
}
