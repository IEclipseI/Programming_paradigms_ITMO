package exceptions;

public class IncorrectNumberException extends ParsingException {
    public IncorrectNumberException(String number) {
        super("Number can not be parsed to int: "  + number);
    }
}
