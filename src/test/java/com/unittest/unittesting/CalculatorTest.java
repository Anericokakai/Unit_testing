package com.unittest.unittesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    Calculator calculator;
//    we need to initialise the  the calculator class

    @BeforeEach
    public void setUp(){
        calculator=new Calculator();
    }

    @Test
    public void TestMultMethod(){
        assertEquals(20,calculator.mult(5,4));
        assertEquals(25,calculator.mult(5,5));
    }

    @Test
    public void TestDivideMethod(){
        assertEquals(2,calculator.divide(4,2));
        assertEquals(2.5,calculator.divide(5,2));
    }




}
