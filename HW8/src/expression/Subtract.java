package expression;

import exceptions.OverflowException;
import expression.operation.Operation;

public class Subtract<E> extends AbstractBinaryOperation<E> {
    public Subtract(final TripleExpression<E> first, final TripleExpression<E> second, final Operation<E> operationType)
    {
        super(first, second, operationType);
    }

    @Override
    protected E evaluateImpl(E a, E b) throws OverflowException
    {
        return operationType.sub(a, b);
    }

}
