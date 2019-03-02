package expression.parser;

public abstract class AbstractUnaryOperation implements CommonExpression {
    protected final CommonExpression expression;

    protected AbstractUnaryOperation(final CommonExpression expression) {
        assert expression != null;
        this.expression = expression;
    }

    public double evaluate(double variable) {
        return evaluateImpl(expression.evaluate(variable));
    }

    protected abstract double evaluateImpl(double a);

    public int evaluate(int variable) {
        return evaluateImpl(expression.evaluate(variable));
    }

    protected abstract int evaluateImpl(int a);

    public int evaluate(int x, int y, int z) {
        return evaluateImpl(expression.evaluate(x, y, z));
    }
}
