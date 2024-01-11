package hr.javafx.miletic7.exception;

public class SameCategoryException extends RuntimeException{
    public SameCategoryException() {
    }

    public SameCategoryException(String message) {
        super(message);
    }

    public SameCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameCategoryException(Throwable cause) {
        super(cause);
    }
}
