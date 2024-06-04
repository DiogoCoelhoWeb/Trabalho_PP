/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.Objects;

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
    
    public String getName(){
        return this.name;
    }

    public double getDistance() {
        return distance;
    }

    public double getDuration() {
        return duration;
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
    
    
}
