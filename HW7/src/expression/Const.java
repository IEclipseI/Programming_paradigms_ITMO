package expression;

public class Const implements TripleExpression {
    private final Number value;

    public Const(int x) {
        value = x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }
}
