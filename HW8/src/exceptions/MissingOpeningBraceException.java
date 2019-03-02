package exceptions;

public class MissingOpeningBraceException extends ParsingException {
    public MissingOpeningBraceException(String expression, int pos) {
        super("Missing open brace for closing brace at position: " + pos + ", at expression: " + expression);
    }
}
