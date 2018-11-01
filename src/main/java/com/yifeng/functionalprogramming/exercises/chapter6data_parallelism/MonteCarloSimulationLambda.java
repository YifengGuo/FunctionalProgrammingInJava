package com.yifeng.functionalprogramming.exercises.chapter6data_parallelism;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;


public class MonteCarloSimulationLambda {
    private static final int N = 10000;  // rolls count
    public static Map<Integer, Double> parallelDiceRolls() {
        double fraction = 1.0 / N;
        return IntStream.range(0, N)
            .parallel()
            .mapToObj(n -> twoDiceThrows())
            .collect(groupingBy(side -> side, // dice value
                    summingDouble(n -> fraction)));  // probabilities sum
    }

    private static Integer twoDiceThrows() {
        return ThreadLocalRandom.current().nextInt(1, 7) + ThreadLocalRandom.current().nextInt(1, 7);
    }

    public static void main(String[] args) {
        Map<Integer, Double> res = MonteCarloSimulationLambda.parallelDiceRolls();
        res.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });
    }
}
