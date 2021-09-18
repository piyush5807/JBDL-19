package com.example.jbdl19.restapis;

public class Person {

    private String email;
    private String name;
    private double amount;
    private String DNI;

    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", DNI='" + DNI + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Person(String email, String name, double amount, String DNI) {
        this.email = email;
        this.name = name;
        this.amount = amount;
        this.DNI = DNI;
    }
}
