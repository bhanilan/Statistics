package com.example.berit.statistics;

public class CalcEngine {
    //Calculation based on operation sign
    public Double calculate(double n1, double n2, String op) {
        double calc = 0;

        if (op.equals("+")) {
            calc = n1 + n2;
        } else if (op.equals("-")) {
            calc = n1 - n2;
        } else if (op.equals("÷")) {
            if(n2==0){
                return null;
            } else {
                calc = n1 / n2;
            }
        } else if (op.equals("×")) {
            calc = n1 * n2;
        }
        return calc;
    }
}