package com.yifeng.functionalprogramming.exercises.chapter5advanced_collections_and_collectors;

/**
 * Created by guoyifeng on 10/12/18
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Efficiently calculate a Fibonacci sequence using just the computeIfAbsent method on a Map. By “efficiently,”
 * I mean that you don’t repeatedly recalculate the Fibonacci sequence of smaller numbers.
 */
public class MapEnhancement {
    public Map<Integer, Long> map;

    public MapEnhancement() {
        map = new HashMap<>();
        map.put(0, 0L);
        map.put(1, 1L);
    }

    // calculate the nth element of fibonacci sequence
    public long fib(int n) {
        return map.computeIfAbsent(n, k -> fib(n - 1) + fib(n - 2));
    }
}
