package com.company;

import javax.management.BadBinaryOpValueExpException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    // JVM --> main() --> func()

    public static void main(String[] args) throws Exception {

//        try {
//            int a = 100 / 0;
//            System.out.println("a = " + a);
//        }catch (ArithmeticException a){
//            System.out.println("in the catch block of arithmetic exception");
//            System.out.println(a.getMessage());
////            throw new FileNotFoundException("Error diving by zero");
//        }

//        func3();
        finallyTest();
    }

    public static void test(){
        try {
            File file = getAFile();
            func2(); // print +  modify
        }catch (Exception e){

        }
    }

    // checked exception - you need to handle it
    public static File getAFile() throws Exception{
        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/pa/dump.txt");
            System.out.println("File is there");
        }catch (FileNotFoundException e){
//            e.printStackTrace();
            System.out.println("Some error occurred!!!"); // For your debugging purpose
            throw new Exception("Some error occurred while reading from the file!");
        }

        return null;
    }

    // Deliberately throwing an exception no matter what
    public static void func2() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("/Users/pa/dump.txt");
//        throw new Exception("Unable to find the file");
    }

    public static void func3() throws Exception{
        try{
            // Use case : If any calculation issue, ignore and proceed further
            // if any file related issue then throw exception (don't proceed normally)
            int a = 100 / 0; // Arithmetic exception
            FileInputStream fi = new FileInputStream("/Users/pa/dump.txt"); // IOException
//            throw new BadBinaryOpValueExpException(null);

        }catch (ArithmeticException e){
            System.out.println("Some error occurred");
            e.printStackTrace();
        }catch (Exception e){
            throw e;
        }
    }

    public static void finallyTest() throws Exception{
        FileInputStream fi = null;
        try {
            fi = new FileInputStream("/Users/pa/dumps.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }finally {
            System.out.println("In finally block");
        }
    }

    // CLI messages how to interpret ?
    // 1. If message is starting with exception in that means main function has thrown exception to JVM
    // 2. If message is starting with the stack trace, then it's just a log



    // When to use try catch
    // 1. Suppress any checked exception
    // 2. When you want to add additional logs in case of a checked exception for debugging purpose

}
