package expression;

import exceptions.OverflowException;

public class CheckedSubtract extends AbstractBinaryOperation {
    public CheckedSubtract(final TripleExpression first, final TripleExpression second) {
        super(first, second);
    }

    @Override
    protected int evaluateImpl(int a, int b) throws OverflowException {
        int res = a - b;
        if (a >= 0 && b <= 0 && res < 0 || a <= 0 && b >= 0 && res > 0) {
            throw new OverflowException("Result of subtraction occurs overflow: " + a + " - " + b);
        }
        return res;
    }

}
