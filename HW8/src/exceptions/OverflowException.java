package exceptions;

public class OverflowException extends EvaluatingException {
    public OverflowException(String a, String operation, String b) {
        super("Result of subtraction occurs overflow: " + a + " " + operation + " " + b);
    }
}
