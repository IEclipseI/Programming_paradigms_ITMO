package expression.parser;

public class Add extends AbstractBinaryOperation {
    public Add(final CommonExpression first, final CommonExpression second) {
        super(first, second);
    }

    @Override
    protected double evaluateImpl(double a, double b) {
        return a + b;
    }

    @Override
    protected int evaluateImpl(int a, int b) {
        return a + b;
    }

}
