package exceptions;

public class UnexpectedVariableException extends ParsingException {
    public UnexpectedVariableException(String s) {
        super(s);
    }
}
