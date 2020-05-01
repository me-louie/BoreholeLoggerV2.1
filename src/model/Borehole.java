package model;


import java.util.List;

public class Borehole {

    private String id;
    private List<SoilSample> soilSamples;

    Borehole(String id, List<SoilSample> soilSamples){
        this.id = id;
        this.soilSamples = soilSamples;
    }
}
