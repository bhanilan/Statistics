package com.example.berit.statistics;

public class OperationType implements IEntity {
    private long id;
    private String operand;
    private int lifetimeCounter;

    public OperationType(){}

    public OperationType(String operand, int lifetimeCounter){
        setOperand(operand);
        setLifetimeCounter(lifetimeCounter);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public int getLifetimeCounter() {
        return lifetimeCounter;
    }

    public void setLifetimeCounter(int lifetimeCounter) {
        this.lifetimeCounter = lifetimeCounter;
    }

    @Override
    public String toString(){
        return operand;
    }
}