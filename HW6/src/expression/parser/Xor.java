package expression.parser;

public class Xor extends AbstractBinaryOperation {
    protected Xor(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected double evaluateImpl(double a, double b) {
        return 0;
    }

    @Override
    protected int evaluateImpl(int a, int b) {
        return a ^ b;
    }
}
