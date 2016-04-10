package com.example.berit.statistics;

public class Operation implements IEntity {
    private long id;
    private long operandId;
    private double num1;
    private double num2;
    private double res;
    private int timeStamp;

    public Operation(){    }

    public Operation(long operandId, double num1, double num2, double res, int timeStamp){
        setOperandId(operandId);
        setNum1(num1);
        setNum2(num2);
        setRes(res);
        setTimeStamp(timeStamp);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOperandId() {
        return operandId;
    }

    public void setOperandId(long operandId) {
        this.operandId = operandId;
    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public double getRes() {
        return res;
    }

    public void setRes(double res) {
        this.res = res;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }
}