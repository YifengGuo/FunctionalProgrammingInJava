package com.yifeng.functionalprogramming.exercises.chapter3streams;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by guoyifeng on 10/10/18
 */
public class Q1StreamImplementationsTest {

    @Test
    public void test1() {
        Stream stream1 = Stream.of(1, 3, 4, 6, 8, 5);
        int res = Q1StreamImplementations.addUp(stream1);
        Assert.assertEquals(27, res);
    }

    @Test
    public void test2() {
        Artist artist1 = new Artist("William", "London");
        Artist artist2 = new Artist("Kelly", "Edison");
        Artist artist3 = new Artist("Paul", "LA");
        Artist artist4 = new Artist("Doug", "New York");

        List<Artist> artists = Arrays.asList(artist1, artist2, artist3, artist4);

        List<String> res = Q1StreamImplementations.getArtistInfo(artists);

        List<String> expected = new ArrayList<>();
        expected.add("William London");
        expected.add("Kelly Edison");
        expected.add("Paul LA");
        expected.add("Doug New York");
        Assert.assertEquals(expected, res);
    }

    @Test
    public void test3() {
        Track track1 = new Track("1");
        Track track2 = new Track("2");
        Track track3 = new Track("3");
        Track track4 = new Track("4");
        Track track5 = new Track("5");
        Track track6 = new Track("6");
        Track track7 = new Track("7");



        List<Track> tracks1 = Arrays.asList(track1, track2);
        List<Track> tracks2 = Arrays.asList(track3, track4, track5, track6, track7);

        Album album1 = new Album(tracks1);
        Album album2 = new Album(tracks2);

        List<Album> albums = Arrays.asList(album1, album2);

        List<Album> res = Q1StreamImplementations.getCertainAlbums(albums);

        List<Album> expected = Arrays.asList(album1);

        Assert.assertEquals(expected, res);
    }
}
