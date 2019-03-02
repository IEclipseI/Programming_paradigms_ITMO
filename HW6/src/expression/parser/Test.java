package expression.parser;

public class Test {
    public static void main(String[] args) {
        System.out.println((Integer.MAX_VALUE +1) == Integer.MIN_VALUE);
        Integer.parseInt("-3");

        ExpressionParser p = new ExpressionParser();
        TripleExpression test = p.parse("(x^1)|(x^2)");
        System.out.println(test.evaluate(0, 0, 0));
    }
}
