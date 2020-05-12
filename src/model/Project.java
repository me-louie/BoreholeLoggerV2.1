package model;

import network.SiteMap;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Project {

    private String number;
    private String manager;
    private String address;
    private SiteMap siteMap;
    private LocalDate date;

    private Set<Borehole> boreholes ;


    public Project() {
        boreholes = new HashSet<>();
    }

    public Project(String number, String manager, String address) {
        this.number = number;
        this.manager = manager;
        this.address = address;
        this.date = null;
        this.boreholes = new HashSet<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManager() {
        return manager;
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

    public Borehole getBorehole(String id){
        for (Borehole bh: boreholes){
            if (bh.getId().equals(id)){
                return bh;
            }
        }
        return null;
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

    public void save(){


    }
}
