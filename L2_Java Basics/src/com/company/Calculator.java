package com.company;

public interface Calculator extends Runnable, Calculator2{

    // Default keyword is different from default scope
    // Non abstract methods in interfaces are default methods

    int add(int a, int b);

    int subtract(int a, int b);

    int multiply(int a, int b);

    int divide(int a, int b);

    default int modulo(int a, int b){
        return a % b;
    }

}
