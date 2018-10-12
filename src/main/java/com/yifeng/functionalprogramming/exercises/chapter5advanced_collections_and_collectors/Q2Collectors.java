package com.yifeng.functionalprogramming.exercises.chapter5advanced_collections_and_collectors;

import com.yifeng.functionalprogramming.exercises.chapter3streams.Artist;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * Created by guoyifeng on 10/11/18
 */
public class Q2Collectors {
    // a. Find the artist with the longest name. You should implement this using a Collector and the reduce higher-order
    // function from Chapter 3. Then compare the differences in your implementation: which was easier to write and which
    // was easier to read? The following example should return "Stuart Sutcliffe":
    private static Comparator<Artist> byNameLength = comparing(artist -> artist.getName().length());

    public static Artist byReduce(List<Artist> artists) {
        return artists.stream()
                .reduce((acc, artist) -> (byNameLength.compare(acc, artist) >= 0) ? acc : artist)
                .orElseThrow(RuntimeException::new);
    }

    public static Artist byCollecting(List<Artist> artists) {
        return artists.stream()
                .collect(Collectors.maxBy(byNameLength))
                .orElseThrow(RuntimeException::new);
    }


    // b. Given a Stream where each element is a word, count the number of times each word appears.
    // So, if you were given the following input, you would return a Map of [John → 3, Paul → 2, George → 1]:
    // Stream<String> names = Stream.of("John", "Paul", "George", "John",
    //                                           "Paul", "John");
    public Map<String, Long> wordCount(Stream<String> stream) {
        return stream.collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()));
    }
}
