package com.yifeng.functionalprogramming.exercises.chapter3streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by guoyifeng on 10/10/18
 */
public class Q1StreamImplementations {
    // A function that adds up numbers, i.e., int addUp(Stream<Integer> numbers)
    public static int addUp(Stream<Integer> nums) {
        return nums.reduce(0, (acc, combine) -> acc + combine);
    }

    // A function that takes in artists and returns a list of strings with their names and
    // places of origin
    public static List<String> getArtistInfo(List<Artist> artists) {
        return artists.stream()
                .map(artist -> artist.getName() + " " + artist.getOrigin())
                .collect(Collectors.toList());
    }

    // A function that takes in albums and returns a list of albums with at most three tracks
    public static List<Album> getCertainAlbums(List<Album> albums) {
        return albums.stream()
                .filter(album -> album.getTracks().size() <= 3)
                .collect(Collectors.toList());
    }
}


