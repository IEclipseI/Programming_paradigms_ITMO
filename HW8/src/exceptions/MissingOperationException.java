package exceptions;

public class MissingOperationException extends ParsingException {
    public MissingOperationException(String message, int pos) {
        super("Missed operation at position: " + pos);
    }
}
