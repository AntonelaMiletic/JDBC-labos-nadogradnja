package hr.javafx.miletic7.exception;

public class SameFactoryItemException extends Exception{
    public SameFactoryItemException() {
    }

    public SameFactoryItemException(String message) {
        super(message);
    }

    public SameFactoryItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameFactoryItemException(Throwable cause) {
        super(cause);
    }
}
