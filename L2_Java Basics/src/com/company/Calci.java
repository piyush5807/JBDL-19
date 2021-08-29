package com.company;

public abstract class Calci {

    public abstract int add(int a, int b);

    public int subtract(int a, int b){ // non-abstract = default functions in interfaces
        return a - b;
    }

    public static void main(String[] args) {
    }
}
