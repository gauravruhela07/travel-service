package exception;

public class AlreadyPresentException extends RuntimeException{
    public AlreadyPresentException(String msg) {
        super(msg);
    }
}