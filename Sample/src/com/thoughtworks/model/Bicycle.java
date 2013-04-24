package com.thoughtworks.model;

public class Bicycle  {
    private Wheel wheel;
    private int wheelNum;

    public String shout() {
        StringBuilder sb = new StringBuilder();
        sb.append("I can run!!!!\n");
        sb.append("Because I have " + wheelNum + " wheel\n");
        sb.append("And every wheel's radius is " + wheel.getRadius() + "\n");
        return sb.toString();
    }

    public Bicycle(Wheel wheel, int wheelNum) {
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

    public void setWheelNum(int wheelNum) {
        this.wheelNum = wheelNum;
    }
}
