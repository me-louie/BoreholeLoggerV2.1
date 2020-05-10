package model;

import ui.old.Observer;

import java.util.HashSet;
import java.util.Set;

public class Subject {
    private Set<Observer> observers = new HashSet<>();

    public void addObserver(Observer o){
        observers.add(o);
    }

    public void removeObserver(Observer o){
        if (observers.contains(o)){
            observers.remove(o);
        }
    }

    void notifyObservers(){
        for (Observer o: observers){
            o.update();
        }
    }

}
