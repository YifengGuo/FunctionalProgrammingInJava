package com.yifeng.functionalprogramming.exercises.chapter3streams;

import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by guoyifeng on 10/10/18
 */
public class MapUsingReduceTest {
    @Test
    public void test1() {
        Stream<String> stream = Stream.of("s", "a", "b", "g");
        Function<String, String> mapper = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };
        List<String> res = MapUsingReduce.map(stream, (in) -> in.toUpperCase());
        res.forEach(s -> System.out.print(s + " "));
    }
}
