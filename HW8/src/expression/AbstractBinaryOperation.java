package expression;

import exceptions.EvaluatingException;
import expression.operation.Operation;

public abstract class AbstractBinaryOperation<E> implements TripleExpression<E> {
    protected final TripleExpression<E> first;
    protected final TripleExpression<E> second;
    protected final Operation<E> operationType;

    protected AbstractBinaryOperation(final TripleExpression<E> first, final TripleExpression<E> second, final Operation<E> operation) {
        assert first != null && second != null;
        this.first = first;
        this.second = second;
        this.operationType = operation;
    }

    protected abstract E evaluateImpl(E a, E b) throws EvaluatingException;

    public E evaluate(E x, E y, E z) throws EvaluatingException {
        return evaluateImpl(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }
}
