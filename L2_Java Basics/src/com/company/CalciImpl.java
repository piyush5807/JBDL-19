package com.company;

public class CalciImpl extends Calci{
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        CalciImpl calci = new CalciImpl();
        System.out.println(calci.subtract(6, 2));
        System.out.println(calci.add(3, 67));
    }
}
