package expression.operation;

import exceptions.ForbiddenOperation;
import exceptions.IncorrectNumberException;
import exceptions.OverflowException;

public class LongOperations implements Operation<Long> {
    @Override
    public Long add(Long first, Long second) {
        return first + second;
    }

    @Override
    public Long sub(Long first, Long second) {
        return first - second;
    }

    @Override
    public Long div(Long first, Long second) throws ForbiddenOperation {
        if (second == 0) {
            throw new ForbiddenOperation("Division by 0: " + first + " / " + second);
        }
        return first / second;
    }

    @Override
    public Long mul(Long first, Long second) {
        return first * second;
    }

    @Override
    public Long negation(Long x) {
        return -x;
    }

    @Override
    public Long parseNumber(String s) {
        return Long.parseLong(s);
    }
}
