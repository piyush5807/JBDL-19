package com.basic;

public class AccessModifiers {

    private int a; // limited to only the class in which it is defined
    int b; // limited to only the current package
    protected int c; // limited to the current package and available in another package's
    // class which a child class of this class
    public int d; // available everywhere

    public static void main(String[] args) {
        func("Hey!", "Hello!", "Hi!", "How are you!");
        AccessModifiers obj = new AccessModifiers();

    }

    public static void func(String... strings){

        for(String s : strings){
            System.out.println(s);
        }
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
}
