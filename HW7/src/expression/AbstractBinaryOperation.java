package expression;

import exceptions.EvaluatingException;

public abstract class AbstractBinaryOperation implements TripleExpression {
    protected final TripleExpression first;
    protected final TripleExpression second;

    protected AbstractBinaryOperation(final TripleExpression first, final TripleExpression second) {
        assert first != null && second != null;
        this.first = first;
        this.second = second;
    }

    protected abstract int evaluateImpl(int a, int b) throws EvaluatingException;

    public int evaluate(int x, int y, int z) throws EvaluatingException {
        return evaluateImpl(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }
}
