package com.company;
import java.util.*;

public class Main {
   public static int checkNumb(String str, int i){
        while(str.charAt(i)>= '0'&& str.charAt(i)<='9' && Character.getNumericValue(str.charAt(i))<(str.length()-1) && i < (str.length())-1){//&& str.charAt(i)<=str.length()
            i++;
        }
        return i;
    }
    public static void main(String[] args) {

        Stack stack = new Stack();
        //String inputStr = new String("5-5");
        //String inputStr = new String("(5-5)");
        //String inputStr = new String("7-2*3");
        //String inputStr = new String("( 4+5)*x-(y/2+15))");
        //String inputStr = new String("(5-5)-4");
        //String inputStr = new String("5/(123+4*3)");
         //String inputStr = new String("15/(25+4*3))");
        String inputStr = new String("((5-5)-4");

        //15/(25+4*3)
        ArrayList<Character> operList =  new ArrayList<Character>();
        operList.add('+');
        operList.add('-');
        operList.add('*');
        operList.add('/');
        operList.add('(');
        operList.add(')');
        String outputStr = new String();

       for(int i = 0; i < inputStr.length(); i++){
            char c = inputStr.charAt(i);
            if (operList.contains(c)){
                if (c == operList.get(5)) // ')'
                {
                    if(stack.peek() != operList.get(4)){ //  пока веришина стека не равна '('
                        for (int j =0 ; j < stack.size(); j++){
                            outputStr +=  String.valueOf(stack.pop()) + ' ';
                            if(stack.peek() == operList.get(4)) {// '('
                                stack.pop(); // удаление все до открывающей скобки
                            }
                        }
                    }
                    continue;
                }
                stack.push(c);
            } else{
                int p = checkNumb(inputStr,i);
                if(p-i>1) {
                    outputStr += inputStr.substring(i, p) + ' ';
                    i++;
                }
                else
                    outputStr += String.valueOf(c) + ' ';
            }
       }
        int j = 0;
        while (!stack.empty()&& j<=stack.size()){
            if(stack.peek() != operList.get(4)){
                outputStr += String.valueOf(stack.pop()) + ' ';
            } else {
                System.out.println("несоответствие скобок");
                outputStr += String.valueOf(stack.pop()) + ' ';
            }
            j++;
        }
       // удалить последний пробел в строке
        System.out.print("\n");
        System.out.print(outputStr);
    }
}
