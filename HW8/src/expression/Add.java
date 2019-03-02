package expression;

import exceptions.OverflowException;
import expression.operation.IntegerOperations;
import expression.operation.Operation;

public class Add<E> extends AbstractBinaryOperation<E> {
    public Add(final TripleExpression<E> first, final TripleExpression<E> second, final Operation<E> operationType)
    {
        super(first, second, operationType);
    }

    Number a;
    Integer b;
    @Override
    protected E evaluateImpl(E a, E b) throws OverflowException {
        return operationType.add(a, b);
    }

}
