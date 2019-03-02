package expression.operation;

import exceptions.ForbiddenOperation;
import exceptions.IncorrectNumberException;
import exceptions.OverflowException;

public interface Operation<E> {
    E add(E first, E second) throws OverflowException;
    E sub(E first, E second) throws OverflowException;
    E div(E first, E second) throws ForbiddenOperation, OverflowException;
    E mul(E first, E second) throws OverflowException;
    E negation(E x) throws OverflowException;
    E parseNumber(String s) throws IncorrectNumberException;
}
