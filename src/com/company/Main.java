package com.company;

public class Main {
    public static void main(String[] args) {

        //String inputStr = new String();
        //String inputStr = new String("(5-5)");
        //String inputStr = new String("7-2*3");
        String inputStr1 = new String("(15*10-(17/17+15))");
        //String inputStr = new String("(5-5)-4");
        //String inputStr = new String("5/(123+4*3))");
        //String inputStr = new String("15/(25+4*3)");
        //String inputStr = new String("((5-5)-4");
        //String inputStr = new String("3+4*2/(1-5)^2");
        MyMathExp myMathExp = new MyMathExp();

/*      double result = myMathExp.calc(inputStr);
        System.out.println("Result: " + result);
*/
        MyMathExp mathExp = new MyMathExp(inputStr1);
        mathExp.calc();
        System.out.println(mathExp);
    }
}

