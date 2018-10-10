package com.yifeng.functionalprogramming.exercises.chapter3streams;

/**
 * Created by guoyifeng on 10/10/18
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Write an implementation of the Stream function map using only reduce and lambda expressions.
 * You can return a List instead of a Stream if you want
 */
public class MapUsingReduce {
    /**
     *
     * @param stream input stream
     * @param mapper map logic
     * @param <T> the type of input stream
     * @param <R> the type of output stream
     * @return List<R> after mapping process
     */
    public static <T, R> List<R> map(Stream<T> stream, Function<T, R> mapper) {
        return stream.reduce(new ArrayList<R>(), (acc, x) -> {  // accumulator
            // We are copying data from acc to new list instance. It is very inefficient,
            // but contract of Stream.reduce method requires that accumulator function does
            // not mutate its arguments.
            // Stream.collect method could be used to implement more efficient mutable reduction,
            // but this exercise asks to use reduce method.
            List<R> newAcc = new ArrayList<>(acc);
            newAcc.add(mapper.apply(x));
            return (ArrayList<R>) newAcc;
        }, (left, right) -> {  // combiner
            // We are copying left to new list to avoid mutating it.
            List<R> newLeft = new ArrayList<>(left);
            newLeft.addAll(right);
            return (ArrayList<R>) newLeft;
        });
    }
}
