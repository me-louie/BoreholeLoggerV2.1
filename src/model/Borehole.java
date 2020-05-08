package model;


import java.util.ArrayList;
import java.util.List;

public class Borehole {

    private String id;
    private List<SoilSample> soilSamples;

    public Borehole(String id){
        this.id = id;
        soilSamples = new ArrayList<>();
    }

    public Borehole(String id, List<SoilSample> soilSamples){
        this.id = id;
        this.soilSamples = new ArrayList<>();
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

    public void addSample(SoilSample soilSample){
        soilSamples.add(soilSample);
    }
}
