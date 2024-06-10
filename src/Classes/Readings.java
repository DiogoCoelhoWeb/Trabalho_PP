/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.time.LocalDateTime;
import java.util.Objects;

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
        final Readings other = (Readings) obj;
        return Objects.equals(this.codeContainer, other.codeContainer);
    }
    
    public String toString(){
        String s = "";
        s+= "Contentor: " + this.codeContainer + "\n";
        s += "Data: " + this.date + "\n";
        s += "Valor: " + this.value + "\n";
        return s;
    }
    
}
