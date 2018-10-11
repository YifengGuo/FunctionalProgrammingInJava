package com.yifeng.functionalprogramming.exercises.chapter3streams;

import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by guoyifeng on 10/10/18
 */
public class FilterUsingReduceTest {
    @Test
    public void test1() {
        Stream<Character> stream = Stream.of('S', 's', 'A', 'g', '1');
        List<Character> res = FilterUsingReduce.filter(stream, c -> c.compareTo('a') >= 0 && c.compareTo('z') <= 0);
        res.forEach(c -> System.out.print(c + " "));
    }
}
