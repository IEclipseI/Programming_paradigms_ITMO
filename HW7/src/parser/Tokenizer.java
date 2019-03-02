package parser;

import exceptions.*;

import static parser.Token.*;

public class Tokenizer {
    private Token currentToken;
    private Token previousToken;
    private String expression;
    private String name;
    private int number;
    private int pos;
    private char ch;
    private int balance;

    Tokenizer(String s) {
        expression = s + "\0\0\0";
        pos = 0;
        ch = expression.charAt(0);
        currentToken = BEGIN;
        name = "";
        number = 0;
        balance = 0;
    }

    public Token getCurrentToken() {
        return currentToken;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Token getPreviousToken() {
        return previousToken;
    }

    public int getPos() {
        return pos;
    }

    public String getExpression() {
        return expression;
    }

    public Token getNextToken() throws ParsingException {
        previousToken = currentToken;
        skipWhitespaces();
        switch (ch) {
            case 0:
                if (balance > 0) {
                    throw new MissingClosingBraceException(expression, pos);
                }
                if (currentToken.isOperation()) {
                    throw new MissingOperandException(expression, pos);
                }
                return currentToken = END;
            case '*':
                checkOperation();
                if( expression.charAt(pos + 1) == '*') {
                    nextChar();
                    nextChar();
                    return currentToken = POW;
                }
                nextChar();
                return currentToken = MUL;
            case '/':
                checkOperation();
                if( expression.charAt(pos + 1) == '/') {
                    nextChar();
                    nextChar();
                    return currentToken = LOG;
                }
                nextChar();
                return currentToken = DIV;
            case '+':
                checkOperation();
                nextChar();
                return currentToken = ADD;
            case '-':
                nextChar();
                return currentToken = checkForMinus();
            case '(':
                balance++;
                checkOperand();
                nextChar();
                return currentToken = OPEN_BRACE;
            case ')':
                if (--balance < 0) {
                    throw new MissingOpeningBraceException(expression, pos);
                }
                checkOperation();
                nextChar();
                return currentToken = CLOSE_BRACE;
            case 'l':
                if (expression.substring(pos, pos + 5).equals("log10")) {
                    checkOperand();
                    pos += 4;
                    nextChar();
                    return currentToken = LOG10;
                }
                if (expression.substring(pos, pos + 3).equals("log")) {
                    checkOperation();
                    pos += 2;
                    nextChar();
                    return currentToken = LOG;
                }
            case 'p':
                if (expression.substring(pos, pos + 5).equals("pow10")) {
                    checkOperand();
                    pos += 4;
                    nextChar();
                    return currentToken = POW10;
                }
                if (expression.substring(pos, pos + 3).equals("pow")) {
                    checkOperation();
                    pos += 2;
                    nextChar();
                    return currentToken = POW;
                }
            default:
                checkOperand();
                int position = pos;
                if (Character.isDigit(ch)) {
                    while (Character.isDigit(ch)) {
                        nextChar();
                    }
                    try {
                        number = Integer.parseInt(expression.substring(position, pos));
                    } catch (NumberFormatException e) {
                        throw new IncorrectNumberException(expression.substring(position, pos));
                    }
                    return currentToken = NUMBER;
                } else {
                    if (Character.isLetter(ch)) {
                        while (Character.isLetter(ch)) {
                            nextChar();
                        }
                        name = expression.substring(position, pos);
                        return currentToken = VARIABLE;
                    } else {
                        throw new UnknownSymbolException("Unknown symbol: " + ch);
                    }
                }
        }
    }

    private void checkOperation() throws ParsingException {
        if (!previousToken.isOperand()) {
            throw new MissingOperandException(expression, pos);
        }
    }

    private void checkOperand() throws ParsingException {
        if (!previousToken.isOperation()) {
            throw new MissingOperationException("", pos - 1);
        }
    }

    private Token checkForMinus() throws ParsingException {
        if (previousToken.isOperand()) {
            return SUB;
        } else {
            if (Character.isDigit(expression.charAt(pos))) {
                int position = pos - 1;
                nextChar();
                while (Character.isDigit(ch)) {
                    nextChar();
                }
                try {
                    number = Integer.parseInt(expression.substring(position, pos));
                } catch (NumberFormatException e) {
                    throw new IncorrectNumberException(expression.substring(position, pos));
                }
                return NUMBER;
            }
            return MINUS;
        }
    }

    private void skipWhitespaces() {
        while (Character.isWhitespace(ch)) {
            nextChar();
        }
    }


    private void nextChar() {
        ch = expression.charAt(++pos);
    }
}
