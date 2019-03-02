package exceptions;

public class MissingClosingBraceException extends ParsingException {
    public MissingClosingBraceException(String expression, int pos) {
        super("Missing closing brace at position: " + pos + ", at expression: " + expression);
    }
}
