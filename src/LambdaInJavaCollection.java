/**
 * Created by guoyifeng on 8/21/18
 */

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * this is introduction of java 8 update on Collection with java.util.function and lambda expression
 */
public class LambdaInJavaCollection {
    public static void main(String[] args) {
        /**
         * 1. output all the string whose length >= 3 in ArrayList
         *
         * new method in ArrayList: void foreach(new Comsumer<? super E>(void accept(T t)))
         */
        List<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));

        // use enhanced for loop
        for (String s : list) {
            if (s.length() >= 3) {
                System.out.print(s + " ");
            }
        }
        System.out.print("\n");
        // use anonymous class
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                if (s.length() >= 3) {
                    System.out.print(s + " ");
                }
            }
        });
        System.out.print("\n");
        // use lambda
        list.forEach(s -> {
            if (s.length() >= 3) {
                System.out.print(s + " ");
            }
        });
        System.out.print("\n");
        /**
         * 2. removeIf()
         * boolean removeIf(Predicate<? super E> filter)
         * interface Predicate has only one abstract method boolean test(T t)
         * removeIf is to remove all the elements which satisfy filter condition
         *
         * remove all the elements in list whose length >= 3
         */

        // use iterator to delete
        List<String> list_remove1 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        Iterator<String> it = list_remove1.iterator();
        while (it.hasNext()) {
            if (it.next().length() >= 3) {
                it.remove();
            }
        }
        for (String s : list_remove1) {
            System.out.print(s + " ");
        }
        System.out.print("\n");

        // use removeIf() in anonymous class
        List<String> list_remove2 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        list_remove2.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() >= 3;
            }
        });
        for (String s : list_remove2) {
            System.out.print(s + " ");
        }
        System.out.print("\n");

        // use lambda expression
        List<String> list_remove3 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        list_remove3.removeIf(s -> s.length() >= 3);
        for (String s : list_remove3) {
            System.out.print(s + " ");
        }
        System.out.print("\n");

        /**
         * replaceAll()
         * void replaceAll(UnaryOperator<E> operator) is to apply operator on each element in the collection
         * and replace the original element by the operated one
         * Interface UnaryOperator has only one abstract method void apply(T t)
         *
         * replace all the elements in the list whose length >= 3 with upper-case ones
         */
        List<String> list_replace1 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        // use for loop and set()
        for (int i = 0; i < list_replace1.size(); i++) {
            String s = list_replace1.get(i);
            if (s.length() >= 3) {
                list_replace1.set(i, s.toUpperCase());
            }
        }
        for (String s : list_replace1) {
            System.out.print(s + " ");
        }
        System.out.print("\n");

        // use replaceAll() by anonymous class
        List<String> list_replace2 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        list_replace2.replaceAll(new UnaryOperator<String>() {
            @Override
            public String apply(String s) {
                return s.length() >= 3 ? s.toUpperCase() : s;
            }
        });
        for (String s : list_replace2) {
            System.out.print(s + " ");
        }
        System.out.print("\n");

        // use lambda expression
        List<String> list_replace3 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        list_replace3.replaceAll(s -> {
            return s.length() >= 3 ? s.toUpperCase() : s;
        });
        for (String s : list_replace3) {
            System.out.print(s + " ");
        }
        System.out.print("\n");

        /**
         * sort()
         * void sort(Comparator<? super E> c)
         * sort the list based on the rule of Comparator c
         */
        // use anonymous class
        List<String> sort1 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        Collections.sort(sort1, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        for (String s : sort1) {
            System.out.print(s + " ");
        }
        System.out.print("\n");

        // use lambda
        List<String> sort2 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        sort2.sort((s1, s2) -> s1.length() - s2.length());
        for (String s : sort2) {
            System.out.print(s + " ");
        }
        System.out.print("\n");

        // use Comparator method invoking
        List<String> sort3 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        sort3.sort(Comparator.comparingInt(String::length));
        for (String s : sort3) {
            System.out.print(s + " ");
        }
        System.out.print("\n");

        /**
         * spliterator: return a iterator which can be split further
         *        spliterator can iterate element in the collection one by one or in batch
         */
    }
}
