package model;

import network.SiteMap;

import java.util.Set;

public class Project {

    private String number;
    private String manager;
    private String address;
    private SiteMap siteMap;

    private Set<Borehole> boreholes;

    public Project(){
    }

    public Project(String number, String manager, String address) {
        this.number = number;
        this.manager = manager;
        this.address = address;
        System.out.println("New Project Created");
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
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
