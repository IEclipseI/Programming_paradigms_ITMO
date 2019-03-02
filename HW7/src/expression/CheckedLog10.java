package expression;

import exceptions.ForbiddenOperation;

public class CheckedLog10 extends AbstractUnaryOperation {
    public CheckedLog10(TripleExpression expression) {
        super(expression);
    }

    @Override
    protected int evaluateImpl(int a) throws ForbiddenOperation {
        if (a <=0) {
            throw new ForbiddenOperation("log10 of negative is undefined: " + a);
        }

        int ans = 0;
        int rhs = 10;
        while (a >= rhs) {
            ans++;
            a/=rhs;
        }
        return ans;
    }
}
