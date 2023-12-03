package exceptions;

public class BadNetworkException extends Exception{

    /**
     * Constructs a BadNetworkException with
     * specified message.
     * @param msg Message to display with exception.
     */
    public BadNetworkException(String msg){super(msg);}
}
