package com.company;

public class Vehicle {

    private int id;
    public String name;
    protected String company;
    int speed;

    public final int calculateDistance(){
        return 10;
    }

    public int calculateSpeed(){
        System.out.println("In Vehicle class: calculateSpeed function");
        return this.speed;
    }


    public Vehicle(int id, String name, String company, int speed) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.speed = speed;
    }

//    public Vehicle(){
//        System.out.println("In Vehicle class constructor");
//    }
}
