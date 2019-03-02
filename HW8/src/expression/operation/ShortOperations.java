package expression.operation;

import exceptions.ForbiddenOperation;
import exceptions.IncorrectNumberException;
import exceptions.OverflowException;

public class ShortOperations implements Operation<Short> {
    @Override
    public Short add(Short first, Short second) {
        return (short) (first + second);
    }

    @Override
    public Short sub(Short first, Short second) {
        return (short) (first - second);
    }

    @Override
    public Short div(Short first, Short second) throws ForbiddenOperation {
        if (second == 0) {
            throw new ForbiddenOperation("Division by 0: " + first + " / " + second);
        }
        return (short) (first / second);
    }

    @Override
    public Short mul(Short first, Short second) {
        return (short) (first * second);
    }

    @Override
    public Short negation(Short x) {
        return (short) -x;
    }

    @Override
    public Short parseNumber(String s) {
        return (short)Integer.parseInt(s);
    }
}
