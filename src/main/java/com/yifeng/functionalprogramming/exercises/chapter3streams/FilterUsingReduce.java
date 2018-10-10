package com.yifeng.functionalprogramming.exercises.chapter3streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by guoyifeng on 10/10/18
 */
public class FilterUsingReduce {
    public static <T> List<T> filter(Stream<T> stream, Predicate<T> filterz) {
        return stream.reduce(new ArrayList<T>(), (acc, x) -> {
            if (filterz.test(x)) {
                List<T> newAcc = new ArrayList<>(acc);
                newAcc.add(x);
                return (ArrayList<T>) newAcc;
            } else {
                return acc;
            }
        }, (left, right) -> {
            List<T> newLeft = new ArrayList<>(left);
            newLeft.addAll(right);
            return (ArrayList<T>) newLeft;
        });
    }
}
