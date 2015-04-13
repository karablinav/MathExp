package com.company;

import java.util.*;

public class Main {
    public static int countNumb(String str, int i) {
        while (str.charAt(i) >= '0' && str.charAt(i) <= '9' && Character.getNumericValue(str.charAt(i)) < (str.length() - 1) && i < (str.length()) - 1) {//&& str.charAt(i)<=str.length()
            i++;
        }
        return i;
    }

    public static boolean checkNumb(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public static byte priority(char ch) {
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

    public static void main(String[] args) {
        Stack<Character> stack = new Stack<Character>();
        //String inputStr = new String("5-5");
        //String inputStr = new String("(5-5)");
        //String inputStr = new String("7-2*3");
        //String inputStr = new String("(15*100-(100/2+15))");
        //String inputStr = new String("(5-5)-4");
        String inputStr = new String("5/(123+4*3)");
        //String inputStr = new String("15/(25+4*3)");
        //String inputStr = new String("((5-5)-4");
        //String inputStr = new String("3+4*2/(1-5)^2");

        //15/(25+4*3)
        ArrayList<Character> operList = new ArrayList<Character>();
        operList.add('+');
        operList.add('-');
        operList.add('*');
        operList.add('/');
        operList.add('(');
        operList.add(')');
        operList.add('^');
        String outputStr = new String();

        boolean flag = false;
        int i = 0;

        while (i < inputStr.length()) {
            char c = inputStr.charAt(i);
            if (checkNumb(String.valueOf(c))) {
                while (!operList.contains(c)) {
                    outputStr += String.valueOf(c);
                    i++;

                    if (inputStr.length() == i) {
                        flag = true;
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
                                System.out.println("Ошибка");
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
            System.out.println("Ошибка!");
            erorrs += String.valueOf(stack.pop()) + ' ';
            System.out.println("Элементы в стеке: " + erorrs);
        }
        System.out.print(outputStr + erorrs);

    }
}

