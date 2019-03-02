package expression.operation;

import exceptions.ForbiddenOperation;
import exceptions.IncorrectNumberException;
import exceptions.OverflowException;

public class IntegerOperations<E> implements Operation<Integer> {
    @Override
    public Integer add(Integer first, Integer second) throws OverflowException {
        Integer res = first + second;
        if (first >= 0 && second >= 0 && res < 0 || first < 0 && second < 0 && res >= 0) {
            throw new OverflowException(first.toString(), " - ", second.toString());
        }
        return res;
    }

    @Override
    public Integer sub(Integer first, Integer second) throws OverflowException {
        int res = first - second;
        if (first >= 0 && second <= 0 && res < 0 || first <= 0 && second >= 0 && res > 0) {
            throw new OverflowException(first.toString(), " - ", second.toString());
        }
        return res;
    }

    @Override
    public Integer div(Integer first, Integer second) throws ForbiddenOperation, OverflowException {
        if (second == 0) {
            throw new ForbiddenOperation("Division by 0: " + first + " / " + second);
        }
        if (first == Integer.MIN_VALUE && second == -1) {
            throw new OverflowException(first.toString(), " - ", second.toString());
        }
        return first / second;
    }

    @Override
    public Integer mul(Integer first, Integer second) throws OverflowException {
        if (first > 0 && second > 0 && Integer.MAX_VALUE / first < second
                || first > 0 && second < 0 && Integer.MIN_VALUE / first > second
                || first < 0 && second > 0 && Integer.MIN_VALUE / second > first
                ||first < 0 && second < 0 && Integer.MAX_VALUE / first > second) {
            throw new OverflowException(first.toString(), " - ", second.toString());
        }
        return first * second;
    }

    @Override
    public Integer negation(Integer x) throws OverflowException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException(x.toString(), " - ", "");
        }
        return x;
    }

    @Override
    public Integer parseNumber(String s) throws IncorrectNumberException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IncorrectNumberException(s);
        }
    }
}
