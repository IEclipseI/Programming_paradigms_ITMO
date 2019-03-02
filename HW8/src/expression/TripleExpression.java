package expression;

import exceptions.EvaluatingException;

public interface TripleExpression<E> {
    E evaluate(E x, E y, E z) throws EvaluatingException;
}