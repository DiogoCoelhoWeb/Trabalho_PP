/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.time.LocalDateTime;

/**
 *
 * @author diogo
 */
public class Readings {
    
    
    private String codeContainer;
    
    private LocalDateTime date;
    
    private double value;
    
    
    public Readings(String codeContainer, LocalDateTime date, double value){
        this.codeContainer = codeContainer;
        this.date = date;
        this.value = value;
    }

    public String getCodeContainer() {
        return codeContainer;
    }

    public void setCodeContainer(String codeContainer) {
        this.codeContainer = codeContainer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    public String toString(){
        String s = "";
        s+= "Contentor: " + this.codeContainer + "\n";
        s += "Data: " + this.date + "\n";
        s += "Valor: " + this.value + "\n";
        return s;
    }
    
}
