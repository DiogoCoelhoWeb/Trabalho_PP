/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import com.estg.core.ItemType;
import Enums.ContainerType;
import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.Measurement;
import com.estg.core.exceptions.MeasurementException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diogo
 */
public class ContainerImp implements Container {

    /**
     * The constant factor for the measurement array
     */
    private static final int MEASUREMENT_FACTOR = 10;

    /**
     * The code of the container
     */
    private String code;

    /**
     * The capacity of the container
     */
    private double capacity;

    /**
     * The max capacity of the container
     */
    private double maxCapacity;

    /**
     * The type of the container
     */
    private ItemType containerType;

    /**
     * The measurement of the container daily
     */
    private Measurement[] measurements;

    private int nMeasurements;

    /**
     * Constructor for the Container
     *
     * @param code code of the Container
     * @param maxCapacity max capacity in kg of the container
     * @param containerType the type of items that belongs to the container
     */
    public ContainerImp(String code, double maxCapacity, ItemType containerType) {
        this.code = code;
        this.capacity = 0;
        this.maxCapacity = maxCapacity;
        this.containerType = containerType;
        this.nMeasurements = 0;
        this.measurements = new Measurement[MEASUREMENT_FACTOR];

    }

    /**
     * Gets the code of the container
     *
     * @return String - the code
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the capacity of the container
     *
     * @return double - the capacity
     */
    @Override
    public double getCapacity() {
        return this.capacity;
    }

    /**
     * Gets the type of the items that the container accepts
     *
     * @return ItemType - the containerType
     */
    @Override
    public ItemType getType() {
        return this.containerType;
    }

    /**
     * Gets the max capacity of the container
     *
     * @return double - the max capacity
     */
    public double getMaxCapacity() {
        return this.maxCapacity;
    }

    /**
     * Gets all measurements of the container
     *
     * @return Measurement[] - the array of measurement
     */
    @Override
    public Measurement[] getMeasurements() {
        return this.measurements;
    }

    /**
     * Gets all measurements of the container from a specific date
     *
     * @param ld
     * @return
     */
    @Override
    public Measurement[] getMeasurements(LocalDate ld) {

        int count = 0;

        for (int i = 0; i < this.measurements.length; i++) {
            if (this.measurements[i].getDate().toLocalDate().equals(ld)) {
                count++;
            }
        }

        Measurement[] aux = new Measurement[count];
        int j = 0;
        for (int i = 0; i < this.measurements.length; i++) {
            if (this.measurements[i].getDate().toLocalDate().equals(ld)) {
                try {
                    aux[j] = ((MeasurementImp)this.measurements[i]).clone();
                    j++;
                } catch (CloneNotSupportedException e) {
                    // ACABAR
                }
            }
        }
        return aux;
    }

    /**
     * This method expands the array of measurements
     */
    private void expandMeasurements() {
        Measurement aux[] = new Measurement[this.measurements.length + MEASUREMENT_FACTOR];

        for (int i = 0; i < this.measurements.length; i++) {
            aux[i] = this.measurements[i];
        }

        this.measurements = aux;
    }

    /**
     * This method add a new measurement to the container
     *
     * @param msrmnt
     * @return boolean - true if the measurement was inserted in the collection
     * storing all measurements false if the measurement already exists for a
     * given date.
     *
     * @throws MeasurementException - if the measurement is null if the value is
     * lesser than 0 if the date is before the existing last measurement date if
     * the for a given date the measurement already exists but the values are
     * different
     */
    @Override
    public boolean addMeasurement(Measurement msrmnt) throws MeasurementException {

        //TODO: Perguntar ao Stor
        if (msrmnt == null) {
            throw new MeasurementException("Cant add a measurement that is null");
        }

        if (msrmnt.getValue() < 0) {
            throw new MeasurementException("Cannot add a measurement less than 0");
        }

        if (msrmnt.getDate().isBefore(this.measurements[this.nMeasurements - 1].getDate())) {
            throw new MeasurementException("Measurement cannot be before the last measurement");
        }

        // TO DO : perguntar a 4 !
        for (int i = 0; i < this.measurements.length; i++) {
            if (this.measurements[i].getDate() == msrmnt.getDate()) {
                return false; // VERIFICAR ESTA PARTE POR FAVOR
            }
        }
        if (this.nMeasurements == this.measurements.length) {
            expandMeasurements();
        }

        this.measurements[this.nMeasurements++] = msrmnt;

        return true;

    }

    /**
     * This method verifies if there is a specific ItemType in a specific Aidbox
     *
     * @param it the ItemType that we want to search
     * @param aidbox the aidbox we will search
     * @return true if there is the specific itemtype in that aidbox , false
     * otherwise
     */
    protected boolean hasContainer(ItemType it, AidBox aidbox) { // PROTECTED OU PRIVATED??

        Container[] aux = aidbox.getContainers();
        for (int i = 0; i < aux.length; i++) {
            if (aux[i].getType() == it) {
                return true;
            }
        }
        return false;
    }

    protected boolean checkTypeContainer(Container[] cntnr, ItemType it) {

        for (int i = 0; i < cntnr.length; i++) {
            if (cntnr[i].getType() == it) {
                return true;
            }
        }
        return false;
    }

    protected double lastMeasurement() {
        return this.measurements[this.nMeasurements - 1].getValue(); // 
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
