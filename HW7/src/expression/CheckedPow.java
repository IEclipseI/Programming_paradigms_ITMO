package expression;

import exceptions.OverflowException;

public class CheckedPow extends AbstractBinaryOperation {

    public CheckedPow(final TripleExpression first, final TripleExpression second) {
        super(first, second);
    }

    @Override
    protected int evaluateImpl(int a, int b) throws OverflowException {
        int ans = 1;
        for (int i = 0; i < b; i++) {
            ans*=a;
        }
        int tmp = ans;
        for (int i = b; i > 0; i++) {
            tmp/= a;
        }
        if (a != tmp) {
            throw new OverflowException("overflow");
        }
        return ans;
    }
}
