package com.company;

public class MTUsingRunnable {

    public static void main(String[] args) {
        MyThread customThread = new MyThread();



        Thread thread = new Thread(customThread);
        thread.start();
    }

    private static class MyThread implements Runnable{

        @Override
        public void run() {
            System.out.println("I am in thread - " + Thread.currentThread());
        }
    }

//    private static class MyThread2 implements Runnable{
//
//        @Override
//        public void run() {
//            System.out.println("I am in thread - " + Thread.currentThread());
//        }
//    }
}
