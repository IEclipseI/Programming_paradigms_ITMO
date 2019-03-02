package expression.parser;

public abstract class AbstractBinaryOperation implements CommonExpression {
    protected final CommonExpression first;
    protected final CommonExpression second;

    protected AbstractBinaryOperation(final CommonExpression first, final CommonExpression second) {
        assert first != null && second != null;
        this.first = first;
        this.second = second;
    }

    public double evaluate(double variable) {
        return evaluateImpl(first.evaluate(variable), second.evaluate(variable));
    }

    protected abstract double evaluateImpl(double a, double b);

    public int evaluate(int variable) {
        return evaluateImpl(first.evaluate(variable), second.evaluate(variable));
    }

    protected abstract int evaluateImpl(int a, int b);

    public int evaluate(int x, int y, int z) {
        return evaluateImpl(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }
}
