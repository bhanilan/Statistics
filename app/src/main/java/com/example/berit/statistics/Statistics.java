package com.example.berit.statistics;

public class Statistics implements IEntity {
    private long id;
    private int dayStamp;
    private long operandId;
    private int dayCounter;

    public Statistics(){}

    public Statistics (int dayStamp, long operandId, int dayCounter){
        setDayStamp(dayStamp);
        setOperandId(operandId);
        setDayCounter(dayCounter);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDayStamp() {
        return dayStamp;
    }

    public void setDayStamp(int dayStamp) {
        this.dayStamp = dayStamp;
    }

    public long getOperandId() {
        return operandId;
    }

    public void setOperandId(long operandId) {
        this.operandId = operandId;
    }

    public int getDayCounter() {
        return dayCounter;
    }

    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }
}