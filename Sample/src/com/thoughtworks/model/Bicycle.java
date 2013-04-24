package com.thoughtworks.model;

public class Bicycle  {
    private Wheel wheel;
    private Integer wheelNum;

    public String shout() {
        StringBuilder sb = new StringBuilder();
        sb.append("I can run!!!!\n");
        sb.append("Because I have " + wheelNum + " wheel\n");
        sb.append("And every wheel's radius is " + wheel.getRadius() + "\n");
        return sb.toString();
    }

    public Bicycle() {

    }

    public Bicycle(Wheel wheel, Integer wheelNum) {
        this.wheel = wheel;
        this.wheelNum = wheelNum;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public int getWheelNum() {
        return wheelNum;
    }

    public void setWheelNum(Integer wheelNum) {
        this.wheelNum = wheelNum;
    }
}
