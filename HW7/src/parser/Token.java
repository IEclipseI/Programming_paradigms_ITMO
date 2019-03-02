package parser;

public enum Token {
    NUMBER, VARIABLE, BEGIN, END, OPEN_BRACE, CLOSE_BRACE, MINUS, POW, LOG, ADD, SUB, DIV, MUL, LOG10, POW10;
    boolean isOperand() {
        return this.equals(NUMBER) || this.equals(VARIABLE) || this.equals(CLOSE_BRACE);
    }

    boolean isOperation() {
        return !isOperand();
    }
}
