package parser;

import exceptions.EvaluatingException;
import exceptions.ParsingException;
import expression.TripleExpression;
import expression.operation.*;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        return tabulateImpl(getOperationTypeFromMode(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    private <E> Object[][][] tabulateImpl(Operation<E> operationType, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParsingException {
        Object[][][] answer = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        Parser<E> parser = new ExpressionParser<E>(operationType);
        TripleExpression<E> expr;
        try {
             expr = parser.parse(expression);

        } catch (ParsingException e) {
            return answer;
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        answer[i - x1][j - y1][k - z1] = expr.evaluate(operationType.parseNumber(Integer.toString(i)), operationType.parseNumber(Integer.toString(j)), operationType.parseNumber(Integer.toString(k)));
                    } catch (ParsingException | EvaluatingException e) {
                        answer[i - x1][j - y1][k - z1] = null;
                    }
                }   
            }
        }
        return answer;
    }


    private Operation<?> getOperationTypeFromMode(String mode) {
        switch (mode) {
            case "i":
                return new IntegerOperations();
            case "d":
                return new DoubleOperations();
            case "bi":
                return new BigIntegerOperations();
            case "u":
                return new UOperations();
            case "l":
                return new LongOperations();
            case "s":
                return new ShortOperations();
        }
        return null;
    }
}
