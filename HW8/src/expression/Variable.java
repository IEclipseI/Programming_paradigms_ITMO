package expression;

import expression.operation.Operation;

public class Variable<E> implements TripleExpression<E> {
    final String name;

    public Variable(final String name){
        this.name = name;
    }

    @Override
    public E evaluate(E x, E y, E z)  {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        return null;
    }
}
