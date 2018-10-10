package com.yifeng.functionalprogramming.exercises.chapter3streams;

import java.util.Objects;

/**
 * Created by guoyifeng on 10/10/18
 */
public  class Track {
    String name;

    public Track(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(name, track.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                '}';
    }
}