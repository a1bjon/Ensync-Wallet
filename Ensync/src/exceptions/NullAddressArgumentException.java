package exceptions;

public class NullAddressArgumentException extends RuntimeException{

    /**
     * Constructs a NullAddressArgumentException with
     * specified message.
     * @param msg Message to display with exception.
     */
    public NullAddressArgumentException(String msg){super(msg);}
}
