package com.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase Calculator.
 */
public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        int result = calculator.add(5, 3);
        assertEquals("5 + 3 debería ser 8", 8, result);
    }

    @Test
    public void testSubtract() {
        int result = calculator.subtract(5, 3);
        assertEquals("5 - 3 debería ser 2", 2, result);
    }

    @Test
    public void testMultiply() {
        int result = calculator.multiply(5, 3);
        assertEquals("5 * 3 debería ser 15", 15, result);
    }

    @Test
    public void testDivide() {
        int result = calculator.divide(6, 2);
        assertEquals("6 / 2 debería ser 3", 3, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        calculator.divide(5, 0);
    }
}