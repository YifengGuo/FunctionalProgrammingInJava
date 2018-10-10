package com.yifeng.functionalprogramming.basics;

import java.awt.event.ActionListener;
import java.net.InterfaceAddress;
import java.util.*;
import java.util.function.BinaryOperator;

/**
 * Created by guoyifeng on 8/20/18.
 */
@SuppressWarnings("Duplicates")
public class TestLambda {
    public static void main(String[] args) throws Exception {
        /**
         * example 1: use lambda for inner anonymous class without parameters
         * create a thread by passing a Runnable() interface
         */

        /***************             JDK 7 Style  Anonymous class               ***************/
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.print("JDK 7 version: ");
                System.out.println("created by inner anonymous class.");
            }
        });

        /***************             JDK 8 Style: Using Lambda                  ***************/
        Thread t2 = new Thread(() -> {  // omit interface name and override method name
            System.out.print("JDK 8 version: ");
            System.out.println("created by lambda.");
        });
        t1.start();
        t2.start();

        /**
         * example 2: use lambda for inner anonymous class with parameters
         * apply Comparator<T>
         */


        List<String> list1 = Arrays.asList(new String[]{"Hello", "World", "Yes", "Fine", "OK"});
        List<String> list2 = Arrays.asList(new String[]{"Hello", "World", "Yes", "Fine", "OK"});
        /***************             JDK 7 Style  Anonymous class               ***************/
        Collections.sort(list1, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1 == null && s2 == null) {
                    return 0;
                } else if (s1 == null) {
                    return 1;
                } else if (s2 == null) {
                    return -1;
                } else {
                    return s1.length() - s2.length();
                }
            }
        });

        /***************             JDK 8 Style: Using Lambda                  ***************/
        Collections.sort(list2, (s1, s2) -> {   // omit parameter list type
            if (s1 == null && s2 == null) {     // feasible because of Java's type inference by context
                return 0;
            } else if (s1 == null) {
                return 1;
            } else if (s2 == null) {
                return -1;
            } else {
                return s1.length() - s2.length();
            }
        });

        for (String s : list1) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (String s : list2) {
            System.out.print(s + " ");
        }

        /**
         * The reason why lambda can omit the parameters and override method name is that
         * 1. This kind of interfaces are so-called Functional Interface which has only one abstract method
         * 2. Type inference from context
         */
        // more examples:
        Runnable run = () -> System.out.println("Hello World");// 1 abstract method without parameters
        ActionListener listener = event -> System.out.println("button clicked");// 2 abstract method with parameters
        Runnable multiLine = () -> {    // 3 code blocks working for abstract method
            System.out.print("Hello");
            System.out.println(" Hoolee");
        };
        BinaryOperator<Long> add = (Long x, Long y) -> x + y;// 4 explicitly show the type of parameters
        BinaryOperator<Long> addImplicit = (x, y) -> x + y;// 5  type inference

        ConsumerInterface<String> consumer = (str) -> System.out.println(str);
        consumer.accept("\nCustomized Functional Interface.");


        /**
         * Differences between lambda expression and anonymous class:
         *     1. Anonymous class is still a class. Although it does not have a name, the compiler will give it a name
         *        when compiling. And it will create a .class file
         *     2. Lambda will NOT produce a .class file at any time. Acutally the lambda expression will become a
         *        private method of main class and being invoked by invokedynamic instruction in JVM. So in fact, when
         *        using "this" in lambda expression, it simply refers to the class itself.
         */


    }
    /**
     * Customized Functional Interface
     */
    @FunctionalInterface
    public interface ConsumerInterface<T> {
        abstract void accept(T t);
    }
}
