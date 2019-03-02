package expression.operation;

import exceptions.ForbiddenOperation;
import exceptions.IncorrectNumberException;
import exceptions.OverflowException;

public class UOperations implements Operation<Integer> {

    @Override
    public Integer add(Integer first, Integer second) {
        return first + second;
    }

    @Override
    public Integer sub(Integer first, Integer second) {
        return first - second;
    }

    @Override
    public Integer div(Integer first, Integer second) throws ForbiddenOperation {
        if (second == 0) {
            throw new ForbiddenOperation("Division by 0: " + first + " / " + second);
        }
        return first / second;
    }

    @Override
    public Integer mul(Integer first, Integer second) {
        return first * second;
    }

    @Override
    public Integer negation(Integer x) {
        return -x;
    }

    @Override
    public Integer parseNumber(String s) throws IncorrectNumberException {
        return Integer.parseInt(s);
    }
}
