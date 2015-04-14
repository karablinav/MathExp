package com.company;

import java.util.ArrayList;
import java.util.Stack;

public class MyMathExp {

    public MyMathExp() {
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
                return 6;
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

    public String rpn (String string){
        String inputStr = string.replaceAll(" ", "");
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
                            if(stack.empty()){
                                 outputStr +="Ошибка";
                                break;
                            }
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
        while (!stack.empty() && (stack.peek() != ('('))){
            outputStr += String.valueOf(stack.pop()) + ' ';
        }
        String erorrs = new String();
        if (!stack.empty()){
            outputStr +="Ошибка";
        }
        return outputStr;
    }

    public void Calc(String exp){

    }
}
