package expression;

import exceptions.OverflowException;
import expression.operation.Operation;

public class Negate<E> extends AbstractUnaryOperation<E> {

    public Negate(final TripleExpression<E> expression, final Operation<E> operationType)
    {
        super(expression, operationType);
    }

    @Override
    protected E evaluateImpl(E a) throws OverflowException {
        return operationType.negation(a);
    }
}
