/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;
import com.estg.core.ItemType;
import Enums.ContainerType;
import com.estg.core.Container;
import com.estg.core.Measurement;
import com.estg.core.exceptions.MeasurementException;
import java.time.LocalDate;
/**
 *
 * @author diogo
 */
public class ContainerImp implements Container{
    
    private final int MEASUREMENT_FACTOR = 365;
    private int measurementCounter = 0;
    
    /**
     * The code of the container
     */
    private String code;
    
    /**
     * The capacity of the container
     */
    private double capacity;
    
    /**
     * The type of the container
     */
    private ItemType containerType;
    
    /**
     * The measurement of the container daily
     */
    private Measurement[] measurements;
    
     /**
     * Constructor for the Container
     * @param code code of the Container 
     * @param capacity capacity in kg of the container
     * @param containerType the type of items that belongs to the container
     */
    public ContainerImp(String code, double capacity, ItemType containerType){
        this.code = code;
        this.capacity = capacity;
        this.containerType = containerType;
        this.measurements = new Measurement[MEASUREMENT_FACTOR]; // fazemos array de um ano??
    }
                        
    /**
     * Gets the code of the container
     * @return the code
     */
    @Override
    public String getCode(){
        return this.code;
    }
    
     /**
     * Gets the capacity of the container
     * @return the capacity
     */
    @Override
    public double getCapacity(){
        return this.capacity;
    }
    
    /**
     * Gets the type of the items that the container accepts
     * @return the containerType
     */
    @Override
    public ItemType getType(){
        return this.containerType;
    }
    
    /**
     * Gets all measurements of the container 
     * @return the array of measurement
     */
    @Override
    public Measurement[] getMeasurements(){
       return this.measurements;
    }
    
    @Override
    public Measurement[] getMeasurements(LocalDate ld){
        
    }
    
    private void expandMeasuremnts() throws OutOfMemoryError{
        Measurement aux[] = new Measurement[this.measurements.length + MEASUREMENT_FACTOR];
            
        for(int i = 0; i < this.measurements.length; i++){
            aux[i] = this.measurements[i];
        }

        this.measurements = aux;
    }
    
    @Override
    public boolean addMeasurement(Measurement msrmnt) throws MeasurementException{
        
        //TODO: Perguntar ao Stor
        
        if(this.measurementCounter == this.measurements.length){
            expandMeasuremnts();
        }

        this.measurements[this.measurementCounter++] = msrmnt;
        
        return true;
    }
}
