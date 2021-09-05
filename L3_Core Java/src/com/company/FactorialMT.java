package com.company;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FactorialMT {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> al = Arrays.asList(40000, 40000, 40000, 90000, 20000, 70000, 80000, 50000);

        FactorialThread[] threads = new FactorialThread[al.size()];

        long start = System.currentTimeMillis();

//        Arrays.stream(threads).forEach(factorialThread -> {
//            factorialThread = new FactorialThread();
//            factorialThread.start();
//        });

        for(int i = 0; i < al.size(); i++){
            threads[i] = new FactorialThread(al.get(i));
            threads[i].start();
//            threads[i].join(); // This is not multi threading
        }

        // TODO: Another way of doing it
//        IntStream.range(0, al.size()).forEach(i -> {
//            threads[i] = new FactorialThread(al.get(i));
//            threads[i].start();
//        });

        for(int i = 0; i < al.size(); i++){
            threads[i].join();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time to execute program " + (end - start));

    }

    private static class FactorialThread extends Thread{

        private int input;
        private BigInteger result;

        FactorialThread(int input){
            this.input = input;
            this.result = BigInteger.ONE;
        }

        @Override
        public void run() {
            calculateBig(this.input);
            System.out.println(currentThread().getName() + " " + this.result);
        }

        public void calculateBig(int n){

            for(int i = 2; i <= n; i++){
                this.result = this.result.multiply(BigInteger.valueOf(i));
            }
        }
    }

}
