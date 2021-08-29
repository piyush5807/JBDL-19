package com.company;

public class FinalKeyword {

    // 1. Variables - Can be initialized only at 2 places, while declaring, and in the constructor
    // 2. Functions - Final functions cannot be overridden
    // 3. Classes - classes which cannot be extended - i.e no function in that class can be overridden

    private final int a; // non-static final variables can only be assigned while declaring or in the constructor
    private static final int b; // static final variables can only be assigned while declaring or in the static blocks

    static {
        b = 60;
    }

    public FinalKeyword() {
        a = 10;
//        a = 70;
    }

    public static void main(String[] args) {
        FinalKeyword obj = new FinalKeyword();
//        obj.a = 50;
        System.out.println(obj.a);
    }
}
