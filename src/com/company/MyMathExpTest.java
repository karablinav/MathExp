package com.company;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyMathExpTest {

    @Test
    public void testCalc() throws Exception {
        MyMathExp myMathExp = new MyMathExp("(15*10-(17/17+15))");
        myMathExp.calc();
        assertTrue("True", Double.valueOf(myMathExp.getExpression()) == 134);
    }
}