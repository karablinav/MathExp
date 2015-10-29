package com.company;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class MyMathExp {

    private String expression;

    public String getExpression() {
        return expression;
    }

    public MyMathExp() {

    }

    public MyMathExp(String expression) {
        this.expression = expression;
    }

    private byte priority(char ch) {
        switch (ch) {
            case '(':
                return 0;
            case ')':
                return 0;
            case '+':
                return 2;
            case '-':
                return 2;
            case '*':
                return 3;
            case '/':
                return 3;
            case '^':
                return 4;
            default:
                return 5;
        }
    }

    private boolean checkNumb(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    private boolean checkStack(Stack<Character> stack){
        if(stack.empty())
            return true;
        return false;
    }

    private double count(double n1, double n2, String operation){
        switch (operation) {
            case "-":
                return n2-n1;
            case "+":
                return n1 + n2;
            case "*":
                return n1*n2;
            case "/":
                return n2/n1;
            case "^":
                return Math.pow(n2, n1);
        }
        return 0;
    }

    private String rpn(String string) throws EmptyStackException {
        String inputStr = string.trim();
        Stack<Character> stack = new Stack<Character>();
        ArrayList<Character> operList = new ArrayList<Character>();
        operList.add('+');
        operList.add('-');
        operList.add('*');
        operList.add('/');
        operList.add('(');
        operList.add(')');
        operList.add('^');
        String outputStr = new String();

        int i = 0;
        while (i < inputStr.length()) {
            char c = inputStr.charAt(i);
            if (checkNumb(String.valueOf(c))) {
                while (!operList.contains(c)) {
                    outputStr += String.valueOf(c);
                    i++;

                    if (inputStr.length() == i) {
                        break;
                    }
                    c = inputStr.charAt(i);
                }
                outputStr += ' ';
            } else {
                if (i != inputStr.length()) {
                    if (operList.contains(c)) {
                        if (c == ('(')) {
                            stack.push(c);
                            i++;
                        } else if (c == ')') {
                            outputStr += String.valueOf(stack.pop()) + ' ';
                            /*if (stack.empty()) {
                                outputStr += "Ошибка";
                                break;
                            }*/
                            if (stack.isEmpty()) throw  new EmptyStackException();
                            char buf = stack.pop();

                            while ((!stack.empty()) && buf != '(') {
                                outputStr += String.valueOf(buf) + ' ';
                                buf = stack.pop();
                            }
                            i++;
                        } else {
                            while ((!stack.empty()) && priority(c) <= priority(stack.peek())) {
                                outputStr += String.valueOf(stack.pop()) + ' ';
                            }
                            stack.push(c);
                            i++;
                        }
                    }
                }
            }
        }
        while (!stack.empty() && (stack.peek() != ('('))) {
            outputStr += String.valueOf(stack.pop()) + ' ';
        }
        return outputStr;
    }

    public void calc() {
        String exp1 = rpn(expression);
        Stack<Double> stack = new Stack<Double>();
        double result = 0;
        String[] chars = exp1.split(" ");
        ArrayList<String> operList = new ArrayList<String>();
        operList.add("+");
        operList.add("-");
        operList.add("*");
        operList.add("/");
        operList.add("^");

        for (int i = 0; i <= chars.length - 1; i++) {
            if (checkNumb(chars[i])) {
                stack.push(Double.parseDouble(chars[i])); // try catch
            } else if (operList.contains(chars[i])) {
                String oper = chars[i];
                double first = stack.pop();
                double second = stack.pop();
                result = count(first, second, oper);
                stack.push(result);
            }
        }
        result = stack.pop();
        expression = Double.toString(result);
    }

    @Override
    public String toString() {
        return "MyMathExp{" +
                "expression='" + expression + '\'' +
                '}';
    }
}