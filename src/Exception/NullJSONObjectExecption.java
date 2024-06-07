/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author diogo
 */
public class NullJSONObjectException extends Exception  {
    
    public NullJSONObjectException() {
    }

    /**
     * Constructor with message
     * @param message custom message
     */
    public NullJSONObjectException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause
     * @param message custom message
     * @param cause cause
     */
    public NullJSONObjectException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

