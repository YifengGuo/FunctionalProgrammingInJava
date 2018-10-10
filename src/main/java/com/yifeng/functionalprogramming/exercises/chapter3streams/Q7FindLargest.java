package com.yifeng.functionalprogramming.exercises.chapter3streams;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by guoyifeng on 10/10/18
 */

/**
 * Find the String with the largest number of lowercase letters from a List<String>.
 * You can return an Optional<String> to account for the empty list case.
 */
public class Q7FindLargest {
    public static String findLargest(List<String> list) {
        Optional<String> res = list.stream()
                .max(Comparator.comparingLong(s -> s.chars()
                                                   .filter(i -> i >= (int)('a') && i <= (int)('z'))
                                                   .count()));
        return res.isPresent() ? res.get() : null;
    }
}
