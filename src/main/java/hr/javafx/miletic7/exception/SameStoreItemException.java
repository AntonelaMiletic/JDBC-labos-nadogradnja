package hr.javafx.miletic7.exception;

public class SameStoreItemException extends Exception{
    public SameStoreItemException() {
    }

    public SameStoreItemException(String message) {
        super(message);
    }

    public SameStoreItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameStoreItemException(Throwable cause) {
        super(cause);
    }
}
