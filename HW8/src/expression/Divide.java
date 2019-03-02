package expression;

import exceptions.*;
import expression.operation.Operation;

public class Divide<E> extends AbstractBinaryOperation<E> {
    public Divide(final TripleExpression<E> first, final TripleExpression<E> second,final Operation<E> operationType)
    {
        super(first, second, operationType);
    }

    @Override
    protected E evaluateImpl(E a, E b) throws OverflowException, ForbiddenOperation {
        return operationType.div(a, b);
    }
}
