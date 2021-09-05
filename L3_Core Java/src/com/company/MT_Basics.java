package com.company;

import java.io.File;

public class MT_Basics {

    public static void main(String[] args) {
//        System.out.println("I am in main!!");
//        System.out.println(Thread.currentThread());


        MyThread thread2 = new MyThread();
        thread2.start();
        thread2.run();
        thread2.run(); // This is not creating a new thread, just invoking the run function of the inner class in the main thread


    }

    private static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("In Thread " + currentThread());
        }
    }
//
//    private static class MyThread2 extends Thread{
//
//        @Override
//        public void run() {
//            System.out.println("In Thread " + currentThread());
//        }
//    }
}
