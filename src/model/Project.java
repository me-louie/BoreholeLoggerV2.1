package model;

import network.SiteMap;

import java.util.Set;

public class Project extends Subject {

    private String number;
    private String manager;
    private String address;
    private SiteMap siteMap;
    private String randomField = "randomField";

    private Set<Borehole> boreholes;

    public String getRandomField() {
        return randomField;
    }

    public void setRandomField(String randomField) {
        this.randomField = randomField;
    }

    public Project() {
    }

    public Project(String number, String manager, String address) {
        this.number = number;
        this.manager = manager;
        this.address = address;
        System.out.println("New Project Created");
        notifyObservers();
        System.out.println("Observers notified");
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
