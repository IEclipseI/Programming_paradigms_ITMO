package parser;

import exceptions.*;
import expression.*;
import expression.operation.Operation;

public class ExpressionParser<E> implements Parser<E> {
    private Tokenizer<E> tokenizer;
    private Operation<E> operationType;

    ExpressionParser(Operation<E> operationType) {
        this.operationType = operationType;
    }
    public TripleExpression<E> parse(final String s) throws ParsingException {
        tokenizer = new Tokenizer(s, operationType);
        return addSub();
    }

    private TripleExpression<E> unary() throws ParsingException {
        TripleExpression<E> res;
        switch (tokenizer.getNextToken()) {
            case NUMBER:
                res = new Const<E>(tokenizer.getNumber());
                tokenizer.getNextToken();
                break;
            case VARIABLE:
                checkVariable();
                res = new Variable<E>(tokenizer.getName());
                tokenizer.getNextToken();
                break;
            case MINUS:
                res = new Negate<E>(unary(), operationType);
                break;
            case OPEN_BRACE:
                res = addSub();
                tokenizer.getNextToken();
                if (tokenizer.getPreviousToken() != Token.CLOSE_BRACE) {
                    throw new MissingClosingBraceException(tokenizer.getExpression(), tokenizer.getPos() - 1);
                }
                break;
            case CLOSE_BRACE:
                throw new MissingOpeningBraceException(tokenizer.getExpression(), tokenizer.getPos());
            default:
                throw new ParsingException("Incorrect expression " + tokenizer.getExpression());
        }
        return res;
    }

    private void checkVariable() throws ParsingException {
        switch (tokenizer.getName()) {
            case "x":
                return;
            case "y":
                return;
            case "z":
                return;
            default:
                throw new UnexpectedVariableException("Unknown variable: " + tokenizer.getName());
        }
    }


    private TripleExpression<E> mulDivMod() throws ParsingException {
        TripleExpression<E> res = unary();
        do {
            switch (tokenizer.getCurrentToken()) {
                case MUL:
                    res = new Multiply<E>(res, unary(), operationType);
                    break;
                case DIV:
                    res = new Divide<E>(res, unary(), operationType);
                    break;
                default:
                    return res;
            }
        } while (true);
    }

    private TripleExpression<E> addSub() throws ParsingException {
        TripleExpression<E> res = mulDivMod();
        do {
            switch (tokenizer.getCurrentToken()) {
                case ADD:
                    res = new Add<E>(res, mulDivMod(), operationType);
                    break;
                case SUB:
                    res = new Subtract<E>(res, mulDivMod(), operationType);
                    break;
                default:
                    return res;
            }
        } while (true);
    }
}