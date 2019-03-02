package expression;

import exceptions.*;

public class CheckedLog extends AbstractBinaryOperation {
    public CheckedLog(final TripleExpression first, final TripleExpression second) {
        super(first, second);
    }

    @Override
    protected int evaluateImpl(int a, int b) throws OverflowException, ForbiddenOperation {
        if (b <= 1) {
            throw new ForbiddenOperation("Log <=1: " + a + " / " + b);
        }
        int ans = 0;
        while (a > 0) {
            a/=b;
            ans++;
        }

        return ans;
    }
}
