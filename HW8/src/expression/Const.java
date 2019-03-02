package expression;

public class Const<E> implements TripleExpression<E> {
    private final E value;

    public Const(E x) {
        value = x;
    }

    @Override
    public E evaluate(E x, E y, E z)
    {
        return value;
    }
}
