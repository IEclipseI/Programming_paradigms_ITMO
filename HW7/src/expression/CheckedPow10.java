package expression;

import exceptions.OverflowException;

public class CheckedPow10 extends AbstractUnaryOperation {
    public CheckedPow10(TripleExpression expression) {
        super(expression);
    }

    @Override
    protected int evaluateImpl(int a) throws OverflowException {
        if (a > 9 || a < 0) {
            throw new OverflowException("pow10 " + a + " is too big for int");
        }
        int res = 1;
        for (int i = 0; i < a; i++) {
            res *= 10;
        }
        return res;
    }
}
