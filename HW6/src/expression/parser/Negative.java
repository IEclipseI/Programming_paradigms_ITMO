package expression.parser;

public class Negative extends AbstractUnaryOperation {

    protected Negative(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected double evaluateImpl(double a) {
        return -a;
    }

    @Override
    protected int evaluateImpl(int a) {
        return -a;
    }
}
