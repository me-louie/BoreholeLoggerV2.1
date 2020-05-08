package model;

import network.SiteMap;

import java.util.HashSet;
import java.util.Set;

public class Project extends Subject {

    private String number;
    private String manager;
    private String address;
    private SiteMap siteMap;

    private Set<Borehole> boreholes;


    public Project() {
        boreholes = new HashSet<>();
    }

    public Project(String number, String manager, String address) {
        this.number = number;
        this.manager = manager;
        this.address = address;
        boreholes = new HashSet<>();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
        notifyObservers();
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Borehole> getBoreholes() {
        return this.boreholes;
    }

    public boolean isBhIdUnique(String id) {
        for (Borehole bh : boreholes) {
            if (bh.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public void setBoreholes(Set<Borehole> boreholes) {
        this.boreholes = boreholes;
    }

    public SiteMap getSiteMap() {
        return siteMap;
    }

    public void setSiteMap(SiteMap siteMap) {
        this.siteMap = siteMap;
    }

    public void addBorehole(Borehole bh) {
        this.boreholes.add(bh);
    }

    public void removeBorehole(Borehole bh) {
        if (this.boreholes.contains(bh)) {
            boreholes.remove(bh);
        }
    }

    public void removeBhById(String id){
        for (Borehole bh: boreholes){
            if (bh.getId().equals(id)){
                boreholes.remove(bh);
            }
        }
    }
}
