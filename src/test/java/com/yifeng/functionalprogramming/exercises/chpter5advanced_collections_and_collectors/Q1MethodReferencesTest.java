package com.yifeng.functionalprogramming.exercises.chpter5advanced_collections_and_collectors;

import com.yifeng.functionalprogramming.exercises.chapter5advanced_collections_and_collectors.Q1MethodReferences;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by guoyifeng on 10/11/18
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q1MethodReferencesTest {
    @Test
    public void test1() {
        List<String> list = Arrays.asList("sjijs", "j2ksdk", "ai2mksSss");
        Q1MethodReferences.convert(list).stream().forEach(s -> System.out.println(s + " "));
    }

    @Test
    public void test2() {
        Stream<String> stream = Stream.of("smosd", "2mmd", "211");
        Number res = Q1MethodReferences.count(stream);
        Assert.assertEquals(3L, res);
    }

    @Test
    public void test3() {
        List<Integer> list1 = Arrays.asList(1, 3, 5);
        List<Integer> list2 = Arrays.asList(2, 4, 6);
        List<Integer> list3 = Arrays.asList(7, 8, 9);
        Stream<List<Integer>> stream = Stream.of(list1, list2, list3);
        List<Integer> res = Q1MethodReferences.concat(stream);
        List<Integer> expected = Arrays.asList(1, 3, 5, 2, 4, 6, 7, 8, 9);
        Assert.assertEquals(expected, res);
    }
}
