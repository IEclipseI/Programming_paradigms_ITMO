package parser;

import exceptions.EvaluatingException;
import exceptions.ParsingException;
import expression.TripleExpression;

public class test {
    public static void main(String[] args) throws ParsingException, EvaluatingException {
        ExpressionParser p = new ExpressionParser();
        TripleExpression t;
        try {
             t = p.parse("1000000*x*x*x*x*x/(x-1)");
        } catch (ParsingException e) {
            System.out.println(e.getMessage());
            return;
        }
        int x = 10;
        for (int i = 0; i < x; i++) {
            try {
                System.out.println(i + " " + t.evaluate(i, 0, 0));
            } catch (EvaluatingException e) {
                System.out.println(i + " " + e.getMessage());
            }
        }
        //System.out.println(p.parse("x*y+(z-1   )/10").evaluate(1,1,1));
    }
}
