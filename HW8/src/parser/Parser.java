package parser;

import exceptions.ParsingException;
import expression.TripleExpression;
import expression.operation.Operation;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser<E> {
    TripleExpression<E> parse(final String expression) throws ParsingException;
}
