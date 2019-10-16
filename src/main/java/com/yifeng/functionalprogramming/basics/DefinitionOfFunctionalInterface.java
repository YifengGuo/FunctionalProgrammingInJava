package com.yifeng.functionalprogramming.basics;

/**
 * Created by guoyifeng on 10/16/19
 */
public class DefinitionOfFunctionalInterface {
    public static void main(String[] args) {
        int a = 5;
        Square s = (x) -> x * x; // a function implementation returns a square interface instance
        System.out.print(s.calculate(a));  // s <=> (x) -> x * x  -> return a * a
    }
}

@FunctionalInterface
interface Square
{
    int calculate(int x);
}
