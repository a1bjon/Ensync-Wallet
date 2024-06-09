package exceptions;

public class InvalidCharsetArgumentException extends RuntimeException{

    /**
     * Constructs a NullCharsetArgumentException with
     * specified message.
     * @param msg Message to display with exception.
     */
    public InvalidCharsetArgumentException(String msg){super(msg);}
}
