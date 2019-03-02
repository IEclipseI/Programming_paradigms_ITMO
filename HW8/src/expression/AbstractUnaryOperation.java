package expression;

import exceptions.EvaluatingException;
import expression.operation.Operation;

public abstract class AbstractUnaryOperation<E> implements TripleExpression<E> {
    protected final TripleExpression<E> expression;
    protected final Operation<E> operationType;

    protected AbstractUnaryOperation(final TripleExpression<E> expression, Operation<E> operation) {
        this.expression = expression;
        this.operationType = operation;
    }

    protected abstract E evaluateImpl(E a) throws EvaluatingException;

    public E evaluate(E x, E y, E z) throws EvaluatingException {
        return evaluateImpl(expression.evaluate(x, y, z));
    }
}
