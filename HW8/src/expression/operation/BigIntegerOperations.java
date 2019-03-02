package expression.operation;

import exceptions.ForbiddenOperation;
import exceptions.IncorrectNumberException;
import exceptions.OverflowException;

import java.math.BigInteger;

public class BigIntegerOperations implements Operation<BigInteger> {
    @Override
    public BigInteger add(BigInteger first, BigInteger second) throws OverflowException {
        return first.add(second);
    }

    @Override
    public BigInteger sub(BigInteger first, BigInteger second) throws OverflowException {
        return first.subtract(second);
    }

    @Override
    public BigInteger div(BigInteger first, BigInteger second) throws ForbiddenOperation, OverflowException {
        if (second.equals(BigInteger.ZERO)) {
            throw new ForbiddenOperation("Division by 0: " + first + " / " + second);
        }
        return first.divide(second);
    }

    @Override
    public BigInteger mul(BigInteger first, BigInteger second) throws OverflowException {
        return first.multiply(second);
    }

    @Override
    public BigInteger negation(BigInteger x) throws OverflowException {
        return x.negate();
    }

    @Override
    public BigInteger parseNumber(String s) throws IncorrectNumberException {
        return new BigInteger(s);
    }
}
