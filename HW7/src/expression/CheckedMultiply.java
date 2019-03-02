package expression;

import exceptions.OverflowException;

public class CheckedMultiply extends AbstractBinaryOperation {

    public CheckedMultiply(final TripleExpression first, final TripleExpression second) {
        super(first, second);
    }

    @Override
    protected int evaluateImpl(int a, int b) throws OverflowException {
        int res = a * b;
        if (a > 0 && b > 0 && Integer.MAX_VALUE / a < b) {
            throw new OverflowException("Result of multiplication occurs overflow: " + a + " * " + b);
        }
        if (a > 0 && b < 0 && Integer.MIN_VALUE / a > b) {
            throw new OverflowException("Result of multiplication occurs overflow: " + a + " * " + b);
        }
        if (a < 0 && b > 0 && Integer.MIN_VALUE / b > a) {
            throw new OverflowException("Result of multiplication occurs overflow: " + a + " * " + b);
        }
        if (a < 0 && b < 0 && Integer.MAX_VALUE / a > b) {
            throw new OverflowException("Result of multiplication occurs overflow: " + a + " * " + b);
        }
        return res;
    }
}
