/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementations;
import com.estg.pickingManagement.Report;
import java.time.LocalDateTime;
/**
 *
 * @author diogo
 */
public class ReportImp implements Report{
    
    private int nUsedVehicles;
    private int nPickedContainers;
    private double totalDistance;
    private double totalDuration;
    private int notPickedContainers;
    private int notUsedVehicles;
    private LocalDateTime date;

    public ReportImp(int nUsedVehicles, int nPickedContainers, double totalDistance, double totalDuration, int nonPickedContainers, int nonUsedVehicles, LocalDateTime date) {
        this.nUsedVehicles = nUsedVehicles;
        this.nPickedContainers = nPickedContainers;
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;
        this.notPickedContainers = nonPickedContainers;
        this.notUsedVehicles = nonUsedVehicles;
        this.date = date;
    }
    
    // FAZER O CONSTRUTOR AUTOMATICO ??? FAZER GETS DE VEICULOS QUE ESTAO A SER USADOS ETC ??

    @Override
    public int getUsedVehicles() {
        return nUsedVehicles;
    }

    @Override
    public int getPickedContainers() {
        return nPickedContainers;
    }

    @Override
    public double getTotalDistance() {
        return totalDistance;
    }

    @Override
    public double getTotalDuration() {
        return totalDuration;
    }

    @Override
    public int getNonPickedContainers() {
        return notPickedContainers;
    }

    @Override
    public int getNotUsedVehicles() {
        return notUsedVehicles;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }

    public void setUsedVehicles(int nUsedVehicles) {
        this.nUsedVehicles = nUsedVehicles;
    }

    public void setPickedContainers(int nPickedContainers) {
        this.nPickedContainers = nPickedContainers;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setTotalDuration(double totalDuration) {
        this.totalDuration = totalDuration;
    }

    public void setNotPickedContainers(int notPickedContainers) {
        this.notPickedContainers = notPickedContainers;
    }

    public void setNotUsedVehicles(int notUsedVehicles) {
        this.notUsedVehicles = notUsedVehicles;
    }
    
    public void setDate(LocalDateTime date){
        this.date = date;
    }
    
}
