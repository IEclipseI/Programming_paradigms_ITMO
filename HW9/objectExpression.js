"use strict";

function includePackageIntoGlobal(pack) {
    for (var i in pack) {
        global[i] = pack[i];
    }
}

var exception = (function () {
    function CustomError(message) {
        this.message = message;
    }

    CustomError.prototype = Object.create(Error.prototype);
    CustomError.prototype.name = "CustomError";

    function CreateException(name) {
        this.name = name;
    }

    CreateException.prototype = CustomError.prototype;

    function exceptionFactory(name, functionMakingMessage) {
        var result = function () {
            var args = arguments;
            CustomError.call(this, functionMakingMessage.apply(null, args));
        };
        result.prototype = new CreateException(name);
        return result;
    }

    var MissingOpeningBraceError = exceptionFactory("MissingOpeningBraceError", function (expr, ind) {
        return ("MissingOpeningBraceError: Missed opening brace for brace at expression: " + expr + ": position " + ind);
    });

    var MissingOperatorError = exceptionFactory("MissingOperatorError", function (expr, ind) {
        return ("MissingOperatorError: Missed operator at expression: " + expr + ": position " + ind);
    });

    var MissingBraceError = exceptionFactory("MissingBraceError", function (expr, ind) {
        return ("MissingBraceError: Missed brace at expression: " + expr + ": position " + ind);
    });

    var NumberOfArgumentsError = exceptionFactory("NumberOfArgumentsError", function (expr, ind, expected, actual) {
        return ("NumberOfArgumentsError: incorrect number of arguments at expression: " + expr + ": position: " + ind
            + ". Expected: " + expected + ", actual: " + actual);
    });

    var UnknownSymbolError = exceptionFactory("UnknownSymbolError", function (expr, ind) {
        return ("UnknownSymbolError: detected unknown symbol at expression: " + expr + ": position: " + ind);
    });

    var UnknownTokenError = exceptionFactory("UnknownTokenError", function (expr, ind) {
        return ("UnknownTokenError: detected unknown token at expression: " + expr + ": position: " + ind);
    });
    return {
        MissingOpeningBraceError: MissingOpeningBraceError,
        MissingOperatorError: MissingOperatorError,
        MissingBraceError: MissingBraceError,
        NumberOfArgumentsError: NumberOfArgumentsError,
        UnknownSymbolError: UnknownSymbolError,
        UnknownTokenError: UnknownTokenError,
    }
})();
includePackageIntoGlobal(exception);

var expression = (function () {
    function Const(x) {
        this.getValue = function () {
            return x;
        }
    }

    Const.prototype.evaluate = function () {
        return this.getValue()
    };
    Const.prototype.toString = function () {
        return this.getValue().toString();
    };
    Const.prototype.prefix = function () {
        return this.getValue().toString();
    };

    var VARIABLES = {"x": 0, "y": 1, "z": 2};

    function Variable(x) {
        var index = VARIABLES[x];
        this.getName = function () {
            return x;
        };
        this.getIndex = function () {
            return index;
        }
    }

    Variable.prototype.evaluate = function () {
        return arguments[this.getIndex()];
    };
    Variable.prototype.toString = function () {
        return this.getName().toString();
    };
    Variable.prototype.prefix = function () {
        return this.getName().toString();
    };

    function AbstractOperation() {
        var args = arguments;
        var operands = [].slice.call(args);
        this.getOperands = function () {
            return operands;
        }
    }

    AbstractOperation.prototype.toString = function () {
        return this.getOperands().map(function (value) {
            return value.toString()
        }).join(" ") + " " + this.getSymbol();
    };

    AbstractOperation.prototype.evaluate = function () {
        var args = arguments;
        var res = this.getOperands().map(function (value) {
            return value.evaluate.apply(value, args)
        });
        return this.operation.apply(null, res);
    };

    AbstractOperation.prototype.prefix = function () {
        return "(" + this.getSymbol() + " " + this.getOperands().map(function (value) {
            return value.prefix()
        }).join(" ") + ")";
    };

    function CreateOperationPrototype(operation, symbol) {
        this.operation = operation;
        this.getSymbol = function () {
            return symbol;
        }
    }

    CreateOperationPrototype.prototype = Object.create(AbstractOperation.prototype);

    function OperationFactory(operation, symbol) {
        var result = function () {
            var args = arguments;
            AbstractOperation.apply(this, args);
        };
        result.prototype = new CreateOperationPrototype(operation, symbol);
        return result;
    }

    var Sqrt = OperationFactory((function (a) {
        return Math.sqrt(Math.abs(a))
    }), "sqrt");
    var Square = OperationFactory((function (a) {
        return a * a
    }), "square");
    var Negate = OperationFactory(function (a) {
        return -a;
    }, "negate");
    var Add = OperationFactory(function (a, b) {
        return a + b
    }, "+");
    var Subtract = OperationFactory(function (a, b) {
        return a - b
    }, "-");
    var Power = OperationFactory(function (a, b) {
        return Math.pow(a, b)
    }, "pow");
    var Log = OperationFactory(function (a, b) {
        return Math.log(Math.abs(b)) / Math.log(Math.abs(a))
    }, "log");
    var Multiply = OperationFactory(function (a, b) {
        return a * b
    }, "*");
    var Divide = OperationFactory(function (a, b) {
        return a / b
    }, "/");
    var ArcTan = OperationFactory(function (a) {
        return Math.atan(a);
    }, "atan");
    var Exp = OperationFactory(function (a) {
        return Math.exp(a);
    }, "exp");

    var OPERATORS = {
        "exp" : Exp,
        "atan" : ArcTan,
        "negate": Negate,
        "+": Add,
        "-": Subtract,
        "*": Multiply,
        "/": Divide,
        "pow": Power,
        "log": Log
    };

    var OPERANDS_IN_OPERATION = {
        "exp": 1,
        "atan" : 1,
        "negate": 1,
        "+": 2,
        "-": 2,
        "*": 2,
        "/": 2,
        "pow": 2,
        "log": 2
    };

    var expr = "";
    var pos = 0;
    var stack = [];
    var balance = 0;

    function skipWhitespaces() {
        while (/\s/.test(expr.charAt(pos))) {
            pos++;
        }
    }

    function parsNumber() {
        var number = "";
        if (expr.charAt(pos) === "-") {
            number = "-";
            pos++;
        }
        var startPos = pos;
        while (/\d/.test(expr.charAt(pos))) {
            pos++;
        }
        number += expr.substring(startPos, pos);
        if (number === "-") {
            pos--;
            return undefined;
        }
        if (number === "") {
            return undefined;
        }
        return parseInt(number);
    }

    function parseOperation() {
        var op = "";
        if (expr.charAt(pos) in OPERATORS) {
            op = expr.charAt(pos);
            pos++;
        } else {
            if (!/[A-Za-z]/.test(expr.charAt(pos))) {
                throw new UnknownSymbolError(expr, pos);
            }
            var startPos = pos;
            while (/[A-Za-z]/.test(expr.charAt(pos))) {
                pos++;
            }
            op = expr.substring(startPos, pos);
        }
        return op;
    }

    function parsePrefix(s) {
        expr = s + "\0";
        pos = 0;
        stack = [];
        balance = 0;
        while (true) {
            skipWhitespaces();
            if (expr.charAt(pos) === "\0") {
                break;
            }
            if (expr.charAt(pos) === "(") {
                stack.push("(");
                balance++;
                pos++;
                continue;
            }
            if (expr.charAt(pos) === ")") {
                balance--;
                if (balance < 0) {
                    throw new MissingOpeningBraceError(s, pos);
                }
                var operands = [];
                while (!(stack[stack.length - 1] in OPERATORS) && !(stack[stack.length - 1] === "(")) {
                    operands.push(stack.pop());
                }
                if (stack[stack.length - 1] === "(") {
                    throw new MissingOperatorError(s, pos);
                }
                var currentOperation = stack.pop();
                if (stack[stack.length - 1] !== "(") {
                    throw new MissingBraceError(s, pos);
                }
                stack.pop();
                if (operands.length > OPERANDS_IN_OPERATION[currentOperation]
                    || operands.length < OPERANDS_IN_OPERATION[currentOperation]) {
                    throw new NumberOfArgumentsError(s, pos, OPERANDS_IN_OPERATION[currentOperation], operands.length);
                }
                var oper = Object.create(OPERATORS[currentOperation].prototype);
                OPERATORS[currentOperation].apply(oper, operands.reverse())
                stack.push(oper);
                pos++;
                if (balance === 0) {
                    break;
                }
                continue;
            }
            var numb = parsNumber();
            if (numb !== undefined) {
                stack.push(new Const(numb));
                continue;
            }
            var op = parseOperation();
            if (op in OPERATORS) {
                stack.push(op);
                continue;
            }
            if (op in VARIABLES) {
                stack.push(new Variable(op));
                continue;
            }
            throw new UnknownTokenError(expr, pos);
        }
        skipWhitespaces();
        if (stack.length !== 1 ||
            expr.charAt(pos) !== "\0") {
            throw new UnknownTokenError(expr, pos);
        }
        var res = stack.pop();
        if (!(res instanceof Const || res instanceof Variable || res instanceof AbstractOperation)) {
            throw new UnknownTokenError(expr, pos);
        }
        return res;
    }

    return {
        Const: Const,
        Variable: Variable,
        Exp: Exp,
        ArcTan: ArcTan,
        Add: Add,
        Subtract: Subtract,
        Multiply: Multiply,
        Divide: Divide,
        Negate: Negate,
        Square: Square,
        Sqrt: Sqrt,
        Power: Power,
        Log: Log,
        parsePrefix: parsePrefix
    }
})();
includePackageIntoGlobal(expression);

// try {
//     console.log(parsePrefix("())"));
// } catch (e) {
//     console.log(e.message);
// }