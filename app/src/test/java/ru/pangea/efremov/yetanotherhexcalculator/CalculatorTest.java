package ru.pangea.efremov.yetanotherhexcalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by efremov on 23/04/17.
 */

public class CalculatorTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Calculator calc = new Calculator();
        calc.setOperand(1);
        calc.setCurrentOperation(Calculator.Operation.PLUS_OP);
        calc.setOperand(2);
        long expectedResult = 3;
        long result = calc.calculate();
        assertEquals(expectedResult, result);
    }

    @Test
    public void not_isCorrect() throws Exception {
        Calculator calc = new Calculator();
        calc.setOperand(1);
        calc.setCurrentOperation(Calculator.Operation.NOT_OP);
        long expectedResult = -2;
        long result = calc.calculate();
        assertEquals(expectedResult, result);
    }
}
