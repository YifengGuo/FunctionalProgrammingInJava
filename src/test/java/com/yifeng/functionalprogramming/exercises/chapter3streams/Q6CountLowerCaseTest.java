package com.yifeng.functionalprogramming.exercises.chapter3streams;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by guoyifeng on 10/10/18
 */
public class Q6CountLowerCaseTest {
    @Test
    public void test1() {
        String s = "SJISJIWsjiaji2Das2SaDDDsa";
        int res = Q6CountLowerCase.countLowerCase(s);
        Assert.assertEquals(11, res);
    }
}
