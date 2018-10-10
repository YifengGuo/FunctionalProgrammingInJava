package com.yifeng.functionalprogramming.exercises.chapter3streams;

import java.util.List;
import java.util.Objects;

/**
 * Created by guoyifeng on 10/10/18
 */
public class Album {
    private List<Track> tracks;

    public Album(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(tracks, album.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tracks);
    }

    @Override
    public String toString() {
        return "Album{" +
                "tracks=" + tracks +
                '}';
    }
}