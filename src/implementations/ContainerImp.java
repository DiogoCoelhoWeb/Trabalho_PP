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
import java.util.Objects;

/**
 *
 * @author diogo
 */
public class ContainerImp implements Container {
    
    /**
     * The constant factor for the measurement array
     */
    private final int MEASUREMENT_FACTOR = 10;
    
    /**
     * The counter for the measurements
     */
    private int measurementCounter;

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
     *
     * @param code code of the Container
     * @param capacity capacity in kg of the container
     * @param containerType the type of items that belongs to the container
     */
    public ContainerImp(String code, double capacity, ItemType containerType) {
        this.code = code;
        this.capacity = capacity;
        this.containerType = containerType;
        this.measurementCounter = 0;
        this.measurements = new Measurement[MEASUREMENT_FACTOR]; // fazemos array de um ano??
    }

    /**
     * Gets the code of the container
     * @return the code
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the capacity of the container
     * @return the capacity
     */
    @Override
    public double getCapacity() {
        return this.capacity;
    }

    /**
     * Gets the type of the items that the container accepts
     * @return the containerType
     */
    @Override
    public ItemType getType() {
        return this.containerType;
    }

    /**
     * Gets all measurements of the container
     *
     * @return the array of measurement
     */
    @Override
    public Measurement[] getMeasurements() {
        return this.measurements;
    }

    @Override
    public Measurement[] getMeasurements(LocalDate ld) {
        
        Measurement[] aux = new Measurement[1];
        for (int i = 0 ; i < aux.length; i++){
            
        }
    }

    private void expandMeasuremnts(){
        Measurement aux[] = new Measurement[this.measurements.length + MEASUREMENT_FACTOR];

        for (int i = 0; i < this.measurements.length; i++) {
            aux[i] = this.measurements[i];
        }

        this.measurements = aux;
    }

    @Override
    public boolean addMeasurement(Measurement msrmnt) throws MeasurementException {

        //TODO: Perguntar ao Stor
        if (msrmnt == null) {
            throw new MeasurementException("Cant add a measurement that is null");
        }

        if (msrmnt.getValue() < 0) {
            throw new MeasurementException("Cannot add a measurement less than 0");
        }

        if (msrmnt.getDate().isBefore(this.measurements[this.measurementCounter - 1].getDate())) {
            throw new MeasurementException("Measurement cannot be before the last measurement");
        }

        // TO DO : perguntar a 4 !
        if (this.measurementCounter == this.measurements.length) {
            expandMeasuremnts();
        }

        this.measurements[this.measurementCounter++] = msrmnt;

        return true;
        
        // to do : Perguntar o return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final ContainerImp other = (ContainerImp) obj;
        return Objects.equals(this.code, other.code);
    }

}
