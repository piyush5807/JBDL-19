package com.company;

import java.util.Random;

public class CalculatorImpl implements Calculator, Runnable, Calculator2{
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return Math.abs(a - b);
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public int divide(int a, int b) {
        return a / b;
    }

    @Override
    public int modulo(int a, int b) {
        return a % b + 4;
    }

    public int random(){
        return new Random().nextInt();
    }

    public static void main(String[] args) {
        CalculatorImpl calculatorImpl = new CalculatorImpl();
        calculatorImpl.random();

        Calculator c = new CalculatorImpl();
//        c.random() // Not possible

        Calculator calculator = new Calculator() {
            @Override
            public void run() {

            }

            @Override
            public int add(int a, int b) {
                return 1;
            }

            @Override
            public int subtract(int a, int b) {
                return 1;
            }

            @Override
            public int multiply(int a, int b) {
                return 1;
            }

            @Override
            public int divide(int a, int b) {
                return 1;
            }

            public int modulo(int a, int b){
                return a % b + 2;
            }
        };

//        System.out.println(calculator.add(2, 3));
//        System.out.println(calculatorImpl.add(2, 3));
        System.out.println(calculatorImpl.modulo(5, 3));
        System.out.println(calculator.modulo(5, 3));

    }


    @Override
    public void run() {

    }
}
