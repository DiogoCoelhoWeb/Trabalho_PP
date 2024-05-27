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
    private Measurement[] measurement;
    
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
        this.measurement = new Measurement[365]; // fazemos array de um ano??
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
       return this.measurement;
    }
    
    @Override
    public Measurement[] getMeasurements(LocalDate ld){
        
    }
    
    @Override
    public boolean addMeasurement(Measurement msrmnt) throws MeasurementException{
        
    }
}
