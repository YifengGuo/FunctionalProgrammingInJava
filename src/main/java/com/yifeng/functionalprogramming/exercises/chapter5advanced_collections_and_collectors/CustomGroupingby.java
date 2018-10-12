package com.yifeng.functionalprogramming.exercises.chapter5advanced_collections_and_collectors;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by guoyifeng on 10/11/18
 */
public class CustomGroupingby<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {
    private final static Set<Characteristics> characteristics = new HashSet<>();

    static {
        characteristics.add(Characteristics.IDENTITY_FINISH); // return unchanged result map
    }

    private final Function<T, K> classifier;

    public CustomGroupingby(Function<T, K> classifier) {
        this.classifier = classifier;
    }

    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return HashMap::new; // supplier of result
    }

    /**
     * the logic to accumulate new element
     * @return
     */
    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (map, element) -> {
            K key = classifier.apply(element); // groupBy logic
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(element); // add current element to the list by its key
        };
    }

    /**
     * the logic to combine two partial results (two maps)
     * @return
     */
    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return (leftMap, rightMap) -> {
            rightMap.forEach((key, value) -> { // try to merge each entry in rightMap to leftMap
                leftMap.merge(key, value, (leftValue, rightValue) -> { // merge logic when current key already has a mapping to a list
                    leftValue.addAll(rightValue);  // left addAll right
                    return leftValue;
                });
            });
            return leftMap; // now rightMap has been merged to leftMap
        };
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return map -> map;  // do no change to final result
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }
}
