package expression;

import exceptions.EvaluatingException;
import exceptions.ParsingException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression {
    int evaluate(int x, int y, int z) throws EvaluatingException;
}