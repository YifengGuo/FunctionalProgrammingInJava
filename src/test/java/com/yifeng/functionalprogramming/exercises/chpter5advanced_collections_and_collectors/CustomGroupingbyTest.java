package com.yifeng.functionalprogramming.exercises.chpter5advanced_collections_and_collectors;

import com.yifeng.functionalprogramming.exercises.chapter5advanced_collections_and_collectors.CustomGroupingby;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by guoyifeng on 10/11/18
 */
public class CustomGroupingbyTest {
    @Test
    public void test() {
        Stream<String> names = Stream.of("John", "Paul", "George", "John", "Paul", "John");
        Function<String, String> myFunction = s -> s;
        Map<String, List<String>> res = names.collect(new CustomGroupingby<String, String>(myFunction));
        res.forEach((k, v) -> System.out.println(k + " " + v));
    }
}