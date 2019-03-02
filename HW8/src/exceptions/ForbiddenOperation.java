package exceptions;

public class ForbiddenOperation extends EvaluatingException {
    public ForbiddenOperation(String s) {
        super(s);
    }
}
