var cnst = function (a) {
    return function (x, y, z) {
        return a;
    }
};
println("adc");
var variable = function (name) {
    return function (x, y, z) {
        switch (name) {
            case("x"):
                return x;
            case("y"):
                return y;
            case("z"):
                return z;
        }
    };
};
var negate = function (a) {
    return function (x, y, z) {
        return -a(x, y, z);
    }
};

var cube = function (a) {
    return function (x, y, z) {
        return a(x, y, z) * a(x, y, z) * a(x, y, z);
    }
};

var cuberoot = function (a) {
    return function (x, y, z) {
        return Math.pow((a(x, y, z)), 1 / 3);
    }
};

var binaryOperation = function func(f) {
    return function (a, b) {
        return function (x, y, z) {
            return f(a(x, y, z), b(x, y, z))
        }
    }
};
var add = binaryOperation(function (a, b) {
    return a + b
});
var subtract = binaryOperation(function (a, b) {
    return a - b
});
var divide = binaryOperation(function (a, b) {
    return a / b
});
var multiply = binaryOperation(function (a, b) {
    return a * b
});

var parse = function (s) {
    var operandStack = [];
    var tokens = s.split(" ").filter(function (t) {
        return t.length > 0;
    });
    ;
    var operations = {
        "+": add,
        "-": subtract,
        "*": multiply,
        "/": divide
    };
    var tokensInOperation = {
        "+": 2,
        "-": 2,
        "*": 2,
        "/": 2
     }
    for (var i = 0; i < tokens.length; i++) {
        if (tokens[i] in operations) {
            var operands = [];
            for (var j = 0; j < tokensInOperation[tokens[i]]; j++) {
                operands.push(operandStack.pop());
            }
            operands.reverse();
            operandStack.push(operations[tokens[i]].apply(null, operands));
        } else if (+tokens[i] == +tokens[i]) {
            operandStack.push(cnst(+tokens[i]));
        } else {
            operandStack.push(variable(tokens[i]));
        }
    }
    return operandStack.pop();
};


var test = [];
for (var i = 0; i <= 10; i++) {
    test.push(add(subtract(multiply(variable("x"), variable("x")), multiply(cnst(2), variable("x"))), cnst(1))(i));
}
//print(test.join(" "));