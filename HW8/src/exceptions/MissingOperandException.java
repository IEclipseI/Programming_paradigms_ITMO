package exceptions;

public class MissingOperandException extends ParsingException {
    public MissingOperandException(String expression, int pos) {
        super("Missed operand at position: " + pos + ",  at expression: " + expression);
    }
}
