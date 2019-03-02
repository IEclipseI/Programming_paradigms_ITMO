package expression.parser;

public class Multiply extends AbstractBinaryOperation {

    public Multiply(final CommonExpression first, final CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int evaluateImpl(int a, int b) {
        return a * b;
    }

    @Override
    public double evaluateImpl(double a, double b) {
        return a * b;
    }

}
