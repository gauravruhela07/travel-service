package exception;

public class BalanceValidationException extends RuntimeException{
    public BalanceValidationException(String msg) {
        super(msg);
    }
}
