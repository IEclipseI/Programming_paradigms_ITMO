package expression.operation;

import exceptions.ForbiddenOperation;
import exceptions.IncorrectNumberException;
import exceptions.OverflowException;

public class DoubleOperations implements Operation<Double> {
    @Override
    public Double add(Double first, Double second) throws OverflowException {
        return first + second;
    }

    @Override
    public Double sub(Double first, Double second) throws OverflowException {
        return first - second;
    }

    @Override
    public Double div(Double first, Double second) throws ForbiddenOperation, OverflowException {
        return first / second;
    }

    @Override
    public Double mul(Double first, Double second) throws OverflowException {
        return first * second;
    }

    @Override
    public Double negation(Double x) throws OverflowException {
        return -x;
    }

    @Override
    public Double parseNumber(String s) throws IncorrectNumberException {
        return Double.parseDouble(s);
    }
}
