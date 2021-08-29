package com.company;

@FunctionalInterface
public interface FI<T, R> {

    R test(T t, R r);

}
