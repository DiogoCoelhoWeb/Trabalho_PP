/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.Objects;
import org.json.simple.JSONObject;

/**
 *
 * @author diogo
 */
public class Location {
    
    private String name;
    private double distance;
    private double duration;
    
    public Location(String name, double distance, double duration){
        this.name = name;
        this.distance = distance;
        this.duration = duration;
    }
    
    public Location(JSONObject obj) {
        this.name = (String) obj.get("name");
        this.distance = (Double) obj.get("distance");
        this.duration = (Double) obj.get("duration");
    }
    
    public String getName(){
        return this.name;
    }

    public double getDistance() {
        return distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        return Objects.equals(this.name, other.name);
    }
    
    
    @Override
    public String toString() {
        String s = "";
        s += "Name: " + this.name + "\n";
        s += "duration: " + this.duration + "\n";
        s += "distance: " + this.distance + "\n";

        s += "\n";
        return s;
    }
    
}
