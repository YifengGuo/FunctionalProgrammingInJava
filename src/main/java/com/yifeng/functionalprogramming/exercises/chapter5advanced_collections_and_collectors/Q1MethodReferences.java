package com.yifeng.functionalprogramming.exercises.chapter5advanced_collections_and_collectors;

/**
 * Created by guoyifeng on 10/11/18
 */

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Method references. Take a look back at the examples in Chapter 3 and try rewriting the following using method references:
 */
public class Q1MethodReferences {
    // a. The map to uppercase
    public static List<String> convert(List<String> list) {
        return list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    // b. The implementation of count using reduce
    public static <T> long count(Stream<T> stream) {
        return stream.reduce(
                0L,
                (counter, x) -> counter.longValue() + 1,
                (a, b) -> a.longValue() + b.longValue()
        );
    }

    // c. The flatMap approach to concatenating lists
    public static <T> List<T> concat(Stream<List<T>> stream) {
        return stream.flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
