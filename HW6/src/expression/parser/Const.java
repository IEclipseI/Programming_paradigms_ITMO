package expression.parser;

public class Const implements CommonExpression {
    private final Number value;

    Const(int x) {
        value = x;
    }

    @Override
    public int evaluate(int variable) {
        return value.intValue();
    }

    @Override
    public double evaluate(double variable) {
        return value.doubleValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }
}
