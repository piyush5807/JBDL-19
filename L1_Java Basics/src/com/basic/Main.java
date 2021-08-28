package com.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    // int = 0, boolean = false, float = 0.0, double = 0.0, long = 0
    // String, Boolean, Float, Long, Integer, Array, ArrayList = null

    int a;
    Integer b;
//    long c;
//    Long d;

    Main(int a, int b){
        this.a = a;
        this.b = b;
    }

    Main(int a){
        this.a = a;
        this.b = 0;
    }

    // Overloading - Having the same function name with either
    // diff no of arguments or diff type of arguments

    public static void func(int a, int b){

    }

    public static void func(long a, int b){

    }

    public static void func(long a, int b, int c){

    }

    //TODO: This is not overloading
//    public static Integer func(long a, int b){
//        return a + b;
//    }

    // com.basic.SingletonClass@4554617c

    public static void main(String[] args) {
        AccessModifiers a = new AccessModifiers();
        a.setA(30);

        SingletonClass obj = SingletonClass.getInstance();
        obj.a = 8;
        System.out.println(obj);
        System.out.println(obj.a + " " + SingletonClass.b);

        Main obj1 = new Main(4, 5);
        System.out.println(obj1.a + " " + obj1.b);

        Main obj2 = new Main(6, 7);
        System.out.println(obj2.a + " " + obj2.b);

        Main obj3 = new Main(10);
        System.out.println(obj3.a + " " + obj3.b);


//        obj.a = 10;
//        System.out.println(obj.a + " " + obj.b + " " + obj.c + " " + obj.d);

//        int[]arr = new int[10];
//        for(int i = 0; i < arr.length; i++){
//            arr[i] = i % 2 == 0 ? i * i : i * i + 1;
//        }
//
//        for(int i = 0; i < arr.length; i++){
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();

        int[]arr = new int[15];
        int[]b = {0, 1, 10, 5, 6, 2, 3, 9, 90, 40};

        print(b);
        Arrays.sort(b);
        print(b);

        ArrayList<Integer> str_li = new ArrayList<>();
        ArrayList<Integer> int_li = new ArrayList<>();

        Collections.sort(str_li);
        System.out.println(str_li);


        // HashMaps - key -> value

        HashMap<Integer, String> hashMap = new HashMap<>();
//        hashMap.put(3, "Hey!");
        hashMap.put(3, "Hello!");
        hashMap.put(4, "Hello!");

        System.out.println(hashMap);



        // jar - Java Archive
    }

    public static void print(int[]arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
