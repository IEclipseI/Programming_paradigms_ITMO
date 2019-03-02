package expression.parser;

public class And extends AbstractBinaryOperation {
    protected And(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected double evaluateImpl(double a, double b) {
        return 0;
    }

    @Override
    protected int evaluateImpl(int a, int b) {
        return a & b;
    }
}
