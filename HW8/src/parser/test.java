package parser;

import exceptions.EvaluatingException;
import exceptions.ParsingException;
import expression.TripleExpression;
import expression.operation.IntegerOperations;
import expression.operation.Operation;
import expression.operation.ShortOperations;

public class test {
    public static <E> void main(String[] args) throws Exception {
        ;
        GenericTabulator g = new GenericTabulator();
        Object[][][] ans = g.tabulate("i", "10", 0, 5, 0, 5, 0, 5);

        //        mode=i, x=[-6, 6] y=[-8, 6] z=[0, 11], p.parse("z / 2");

        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                for (int k = 0; k < ans[i][j].length; k++) {
                    System.out.println(i + " " + j + " " + k + " " +ans[i][j][k]);
                }
            }
        }
        //System.out.println(p.parse("x*y+(z-1   )/10").evaluate(1,1,1));
    }
}
