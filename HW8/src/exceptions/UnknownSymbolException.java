package exceptions;

public class UnknownSymbolException extends ParsingException {
    public UnknownSymbolException(String message) {
        super(message);
    }
}
