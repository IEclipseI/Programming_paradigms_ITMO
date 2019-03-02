package expression;

import exceptions.*;

public class CheckedDivide extends AbstractBinaryOperation {
    public CheckedDivide(final TripleExpression first, final TripleExpression second) {
        super(first, second);
    }

    @Override
    protected int evaluateImpl(int a, int b) throws OverflowException, ForbiddenOperation {
        if (b == 0) {
            throw new ForbiddenOperation("Division by 0: " + a + " / " + b);
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException("Division occurs overflow: " + a + " / " + b);
        }
        return a / b;
    }
}
