package com.yifeng.functionalprogramming.exercises.chapter3streams;

/**
 * Created by guoyifeng on 10/10/18
 */

/**
 * Count the number of lowercase letters in a String (hint: look at the chars method on String)
 */
public class Q6CountLowerCase {
    public static int countLowerCase(String s) {
        return (int) s.chars()  // s.chars() returns a stream of integer values of corresponding character
                .filter(i -> i >= (int)('a') && i <= (int)('z'))
                .count();
    }
}
