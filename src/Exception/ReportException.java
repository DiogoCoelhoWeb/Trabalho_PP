/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author diogo
 */
public class ReportException extends Exception  {
    
    public ReportException() {
    }

    /**
     * Constructor with message
     * @param message custom message
     */
    public ReportException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause
     * @param message custom message
     * @param cause cause
     */
    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
