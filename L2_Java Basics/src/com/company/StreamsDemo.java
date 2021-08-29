package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsDemo {

    public static void main(String[] args) throws Exception {
        // Q1. You are given a list of integers
        // arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        // result = [4, 16, 36, 64, 100]
        // result = if num % 2 == 0 ? num * num

        int [] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayList<Integer> al = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            if(arr[i] % 2 == 0){
                al.add(arr[i] * arr[i]);
            }
        }
        System.out.println(al);

         ------------------------------------------------------------------------------

//        List<Integer> result = Arrays.stream(arr)
//                 .boxed()
//                .filter(abc -> abc % 2 == 0)
//                .map(def -> def * def)
//                .collect(Collectors.toList());
//
//
//        List<Integer> al = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//
//        al = al.stream()
//                .filter(a -> a % 2 == 0)
//                .map(x -> x * x)
//                .collect(Collectors.toList());
//
//        System.out.println(al);
//
//        // Given a list of integers, you need to find the first multiple of 6
//
//        getMultipleOf6(Arrays.asList(4, 3, 8, 42, 43, 32, 12));

    }

    public static int getMultipleOf6(List<Integer> al){

        for(int i = 0; i < al.size(); i++){
            System.out.println("In getMultipleOf6 function, val = " + al.get(i));
            if(al.get(i) % 6 == 0){
                return al.get(i);
            }
        }

        return -1;
    }

    public static int getMultipleOf6Streams(List<Integer> al) throws Exception{

        return al.stream().parallel().filter(x -> {
            System.out.println("Got element - " + x);
            return x % 6 == 0;
        }).findFirst()
//                .orElse(-1);
                .orElseThrow(() -> new Exception("No multiple of 6 found"));

    }

    // Optional - which will store the actual value if the value is present, null


}
