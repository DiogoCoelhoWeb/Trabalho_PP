/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementations;
import java.time.LocalDateTime;
import com.estg.core.Measurement;
/**
 *
 * @author diogo
 */
public class MeasurementImp implements Measurement{
    
    /**
     * The value in kilos
     */
    private double value;
    
    /**
     * The date of the measurement
     */
    private LocalDateTime date;
    
    
    /**
     * Constructor for the Route
     * @param value value in kgs  
     * @param date the date
     */
    public MeasurementImp(double value, LocalDateTime date){
        this.value = value;
        this.date = date;
    }
    
    /**
     * Gets the date 
     * @return LocalDateTime - the date
     */
    @Override
    public LocalDateTime getDate(){
        return this.date;
    }

    /**
     * Gets the value in kilos
     * @return double - the value
     */
    @Override
    public double getValue(){
        return this.value;
    }
    
    @Override
    public Measurement clone() throws CloneNotSupportedException {
        try {
            return (Measurement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); 
        }
    }
    
    @Override
    public String toString(){
        String s = "";
        s += "Value: " + this.value + "\n";
        s += "Date: " + this.date + "\n";
        return s;
    }
}