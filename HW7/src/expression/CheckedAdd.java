package expression;

import exceptions.EvaluatingException;
import exceptions.OverflowException;

public class CheckedAdd extends AbstractBinaryOperation {
    public CheckedAdd(final TripleExpression first, final TripleExpression second) {
        super(first, second);
    }

    @Override
    protected int evaluateImpl(int a, int b) throws OverflowException {
        int res = a + b;
        if (a >= 0 && b >= 0 && res < 0 || a < 0 && b < 0 && res >= 0) {
            throw new OverflowException("Result of addition occurs overflow: " + a + " + " + b);
        }
        return res;
    }

}
