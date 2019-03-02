package expression;

import exceptions.OverflowException;

public class CheckedNegate extends AbstractUnaryOperation {

    public CheckedNegate(TripleExpression expression) {
        super(expression);
    }

    @Override
    protected int evaluateImpl(int a) throws OverflowException {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException(Integer.MIN_VALUE + " can not be negated with out overflow");
        }
        return -a;
    }
}
