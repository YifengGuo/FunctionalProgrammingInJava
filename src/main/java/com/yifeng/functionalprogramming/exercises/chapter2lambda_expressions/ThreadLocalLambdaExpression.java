package com.yifeng.functionalprogramming.exercises.chapter2lambda_expressions;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;

/**
 * Created by guoyifeng on 10/10/18
 */

/**
 * ThreadLocal lambda expressions.
 * Java has a class called ThreadLocal that acts as a container for a value that’s local to your current thread.
 * In Java 8 there is a new factory method for ThreadLocal that takes a lambda expression, letting you create a
 * new ThreadLocal without the syntactic burden of subclassing.
 *
 * The Java DateFormatter class isn’t thread-safe. Use the constructor to create a
 * thread-safe DateFormatter instance that prints dates like this: “01-Jan-1970”.
 */
public class ThreadLocalLambdaExpression implements Runnable {
    private ThreadLocal<DateFormatter> threadLocal = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("yyyy-MM-dd")));
    @Override
    public void run() {
        DateFormatter formatter = threadLocal.get();
        System.out.println(formatter.getFormat().format(0));

    }

    public static void main(String[] args) {
        ThreadLocalLambdaExpression test = new ThreadLocalLambdaExpression();
        Thread thread = new Thread(test);
        thread.start();
    }
}
