package com.basic;

public class SingletonClass {

    int a;
    static int b;

    private static SingletonClass object = null;

    private SingletonClass(){

    }

    public static SingletonClass getInstance(){
        if(object == null){
            object = new SingletonClass();
        }

        return object;
    }

    public static void main(String[] args) {
//        b = 4;
//        System.out.println(add(2, 3));
//        System.out.println(new SingletonClass().subtract(4, 5));
//
//        SingletonClass obj1 = new SingletonClass();
//        SingletonClass obj2 = new SingletonClass();

    }

//    public int subtract(int a, int b){
//        return a - b;
//    }
//
//    public static int add(int a, int b){
//        return a + b;
//    }
}
