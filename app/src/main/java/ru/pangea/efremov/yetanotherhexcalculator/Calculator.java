package ru.pangea.efremov.yetanotherhexcalculator;

import java.io.Serializable;

/**
 * Created by efremov on 23/04/17.
 */

public class Calculator implements Serializable {

    public enum Operation {
        EMPTY_OP, PLUS_OP, MINUS_OP, TIMES_OP, NOT_OP, OR_OP, AND_OP
    }

//    private AppCompatActivity context;
    private long op1 = 0L;
    private long op2 = 0L;
    private Operation currentOperation = Operation.EMPTY_OP;

    Calculator() {
        clear();
    }

    public void clear() {
        op1 = 0;
        op2 = 0;
        currentOperation = Operation.EMPTY_OP;
    }

    public void setOperand(long operand) {
        if(currentOperation == Operation.EMPTY_OP)
            op1 = operand;
        else
            op2 = operand;
    }

    public void setCurrentOperation(Operation operation) {
        currentOperation = operation;
    }

    public long calculate() {
        long res = 0;
        switch(currentOperation) {
            case EMPTY_OP:
                break;
            case PLUS_OP:
                res = op1 + op2;
                break;
            case MINUS_OP:
                res = op1 - op2;
                break;
            case TIMES_OP:
                res = op1 * op2;
                break;
            case NOT_OP:
                res = ~op1;
                break;
            case OR_OP:
                res = op1 | op2;
                break;
            case AND_OP:
                res =  op1 & op2;
                break;
            default:
                throw new RuntimeException("Invalid operation!");
        }
        clear();
        return res;
    }
}
