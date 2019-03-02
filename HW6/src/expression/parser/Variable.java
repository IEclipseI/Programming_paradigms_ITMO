package expression.parser;

public class Variable implements CommonExpression {
    final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int variable) {
        return variable;
    }

    @Override
    public double evaluate(double variable) {
        return variable;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        char var = name.charAt(0);
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        return 0;
    }
}
