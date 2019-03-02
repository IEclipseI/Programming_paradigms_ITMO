package expression.parser;

public class ExpressionParser implements Parser {
    private String expression;
    private int pos;
    private char ch;

    @Override
    public TripleExpression parse(String expression) {
        this.expression = expression.replaceAll("\\s", "") + "\0\0\0";
        pos = 0;
        nextChar();
        CommonExpression res = parseOr();
        return res;
    }

    private CommonExpression parseOr() {
        CommonExpression result = parseXor();
        while (check('|')) {
            result = new Or(result, parseXor());
        }
        return result;
    }

    private CommonExpression parseXor() {
        CommonExpression result = parseAnd();
        while (check('^')) {
            result = new Xor(result, parseAnd());
        }
        return result;
    }

    private CommonExpression parseAnd() {
        CommonExpression result = parseAddOrSub();
        while (check('&')) {
            result = new And(result, parseAddOrSub());
        }
        return result;
    }

    private CommonExpression parseAddOrSub() {
        CommonExpression result = parseMulOrDiv();
        while (check('+') || check('-')) {
            if (expression.charAt(pos - 2) == '+') {
                result = new Add(result, parseMulOrDiv());
            } else {
                result = new Subtract(result, parseMulOrDiv());
            }
        }
        return result;
    }

    private CommonExpression parseMulOrDiv() {
        CommonExpression result = parseUnaryMinus();
        while (check('*') || check('/')) {
            if (expression.charAt(pos - 2) == '*') {
                result = new Multiply(result, parseUnaryMinus());
            } else {
                result = new Divide(result, parseUnaryMinus());
            }
        }
        return result;
    }

    private CommonExpression parseUnaryMinus() {
        if (check('-')) {
            return new Negative(parseUnaryMinus());
        }
        if (check('(')) {
            CommonExpression tmp = parseOr();
            nextChar();
            return tmp;
        }
        return parseVariableOrConst();
    }

    private CommonExpression parseVariableOrConst() {
        if (Character.isLetter(ch)) {
            StringBuilder sb = new StringBuilder();
            while (Character.isLetter(ch)) {
                sb.append(ch);
                nextChar();
            }
            return new Variable(sb.toString());
        } else {
            int c = 0;
// aa+bb
//            int startPos = pos - 1;
//            while(Character.isDigit(ch)) {
//                nextChar();
//            }
            while (Character.isDigit(ch)) {
                c = c * 10 + Character.getNumericValue(ch);
                nextChar();
            }
            return new Const(c);
        }
    }

    private void skipWhispaces() {
        while (Character.isWhitespace(ch)) {
            nextChar();
        }
    }

    private boolean check(char ch) {
        if (this.ch == ch) {
            nextChar();
            return true;
        }
        return false;
    }

    private void nextChar() {
        ch = expression.charAt(pos++);
    }


}
