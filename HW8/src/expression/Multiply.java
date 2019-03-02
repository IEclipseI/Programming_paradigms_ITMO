package expression;

import exceptions.OverflowException;
import expression.operation.Operation;

public class Multiply<E> extends AbstractBinaryOperation<E> {

    public Multiply(final TripleExpression<E> first, final TripleExpression<E> second, final Operation<E> operationType)
    {
        super(first, second, operationType);
    }

    @Override
    protected E evaluateImpl(E a, E b) throws OverflowException {
        return operationType.mul(a, b);
    }
}
