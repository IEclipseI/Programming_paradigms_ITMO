package parser;

import exceptions.*;
import expression.*;

import static parser.Token.*;

public class ExpressionParser implements Parser {
    private Tokenizer tokenizer;

    private TripleExpression unary() throws ParsingException {
        TripleExpression res;
        switch (tokenizer.getNextToken()) {
            case NUMBER:
                res = new Const(tokenizer.getNumber());
                tokenizer.getNextToken();
                break;
            case VARIABLE:
                checkVariable();
                res = new Variable(tokenizer.getName());
                tokenizer.getNextToken();
                break;
            case MINUS:
                res = new CheckedNegate(unary());
                break;
            case LOG10:
                res = new CheckedLog10(unary());
                break;
            case POW10:
                res = new CheckedPow10(unary());
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

    private TripleExpression powLog() throws ParsingException {
        TripleExpression res = unary();
        do {
            switch (tokenizer.getCurrentToken()) {
                case POW:
                    res = new CheckedPow(res, unary());
                    break;
                case LOG:
                    res = new CheckedLog(res, unary());
                    break;
                default:
                    return res;
            }
        } while (true);
    }

    private TripleExpression mulDivMod() throws ParsingException {
        TripleExpression res = unary();
        do {
            switch (tokenizer.getCurrentToken()) {
                case MUL:
                    res = new CheckedMultiply(res, unary());
                    break;
                case DIV:
                    res = new CheckedDivide(res, unary());
                    break;
                default:
                    return res;
            }
        } while (true);
    }

    private TripleExpression addSub() throws ParsingException {
        TripleExpression res = mulDivMod();
        do {
            switch (tokenizer.getCurrentToken()) {
                case ADD:
                    res = new CheckedAdd(res, mulDivMod());
                    break;
                case SUB:
                    res = new CheckedSubtract(res, mulDivMod());
                    break;
                default:
                    return res;
            }
        } while (true);
    }

    public TripleExpression parse(final String s) throws ParsingException {
        tokenizer = new Tokenizer(s);
        return addSub();
    }


}