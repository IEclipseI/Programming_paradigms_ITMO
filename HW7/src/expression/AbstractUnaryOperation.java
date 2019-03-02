package expression;

import exceptions.EvaluatingException;

public abstract class AbstractUnaryOperation implements TripleExpression {
    protected final TripleExpression expression;

    protected AbstractUnaryOperation(final TripleExpression expression) {
        assert expression != null;
        this.expression = expression;
    }

    protected abstract int evaluateImpl(int a) throws EvaluatingException;

    public int evaluate(int x, int y, int z) throws EvaluatingException {
        return evaluateImpl(expression.evaluate(x, y, z));
    }
}
