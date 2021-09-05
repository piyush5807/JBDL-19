package com.company;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Factorial {

    public static void main(String[] args) {
        // Given n numbers, you have to find the factorial of those n numbers

        List<Integer> al = Arrays.asList(40000, 40000, 40000, 90000, 20000, 70000, 80000, 50000);

        long start = System.currentTimeMillis(); // epoch time

        // Streams | Functional
        al.stream()
                .map(i -> calculateBig(i))
                .forEach(i -> System.out.println(i));

        long end = System.currentTimeMillis();

        System.out.println("Time to execute program " + (end - start));

        // Enhanced for loop
//        for(Integer i : al){
//            System.out.println(calculate(i));
//        }
//
//        // For loop
//        for(int i = 0; i < al.size(); i++){
//            System.out.println(calculate(al.get(i)));
//        }

    }

    public static Integer calculate(int num){
        int ans = 1;
        for(int i = 2; i <= num; i++){
            ans = ans * i;
        }

        return ans;
    }

    public static BigInteger calculateBig(int n){

        BigInteger result = BigInteger.ONE;

        for(int i = 2; i <= n; i++){
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }
}
