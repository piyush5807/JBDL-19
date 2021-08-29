package com.company;

import java.util.function.Consumer;

public class FunctionalInterfaces {

    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {

            @Override
            public void accept(String s) {
                System.out.println(s.toLowerCase());
            }
        };

        consumer.accept("10");


        Consumer consumer1 = consumer;

        // Lambda expression
//        Consumer<String> consumer = (s) -> {
//            System.out.println(s.toLowerCase());
//            int a = 9;
//        };
//
//        consumer.accept("Test");


        FI<Integer, String> fi = (a, b) -> {
//            System.out.println(a + " " + b);
            return b + a; // we don't know where
        };

        System.out.println(fi.test(2, "Hi!"));
    }
}
