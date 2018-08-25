/**
 * Created by guoyifeng on 8/23/18.
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 1. To create a stream: (Collection).stream(), (Collection).parallelStream(), Arrays.stream(T[] arr)
 *
 * 2. Inheritance relationship:                           BaseStream
 *                                              /       |          |              \
 *                                        IntStream    LongStream  DoubleStream   Stream (All the other kind of streams)
 *
 * 3. Differences between Stream and Collection class
 *
 *  a. No storage: Stream is not data structure, it is like a view of some datasource. Stream could be an array, a
 *                 Java container or a I/O channel
 *
 *  b. For functional programming: Stream will change nothing of that datasource. e.g: Stream.filter() will not filter
 *     out any element, instead, it just creates a new Stream which contains chosen elements
 *
 *  c. Lazy execution: Stream will not execute the operation until the user really needs it.
 *
 *  d. Consumable: Stream can only be consumed once, i.e, once running iteration on a stream, you cannot do it again.
 *     Just like iterator of Collections, you need to create a new Stream to redo it.
 *
 *
 * APIs:
 *     Intermediate Operations:    lazy execution  -> only create a stream which marked the operation
 *                             concat() distinct() filter() flatMap() limit() map() peek()
 *                             skip() sorted() parallel() sequential() unordered()
 *
 *     Terminal Operations:        execute all the intermediate operations in pipeline, after that stream will expire
 *                             allMatch() anyMatch() collect() count() findAny() findFirst()
 *                             forEach() forEachOrdered() max() min() noneMatch() reduce() toArray()
 *
 *     So like Flink, Intermediate Operation apis usually returns another Stream, otherwise it should be
 *     Terminal Operation
 */
public class JavaStream {
    public static void main(String[] args) {
        /**
         * Stream api
         */

        // 1. foreach()  terminal operation
        Stream<String> stringStream = Stream.of("I", "love", "you", "too");
        stringStream.forEach(s -> System.out.print(s + " "));
        System.out.print("\n");

        // 2. filter()
        // method: Stream<T> filter(Predicate<? super T> predicate)
        // which returns a stream in which each element meets predicate condition
        // NOTE: filter() itself is intermediate method which does not return calculate or print anything
        Stream<String> filterStream = Stream.of("I", "love", "you", "too");
        filterStream
                .filter(s -> s.length() >= 3) // filter elements
                .forEach(s -> System.out.print(s + " ")); // print them by foreach()
        System.out.println();

        // 3. distinct()
        // method: Stream<T> distinct
        Stream<String> distinctStream = Stream.of("I", "love", "I", "you", "too", "love", "too", "I");
        distinctStream
                .distinct()
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

        // 4. sorted()
        // method: Stream<T> sorted()     sorted by element's natural order
        //         Stream<T> sorted(Comparatro<T> comparator) sorted by customized order
        Stream<String> sortedStream1 = Stream.of("1", "12", "123", "1234", "12345");
        Stream<String> sortedStream2 = Stream.of("1", "12", "123", "1234", "12345");
        sortedStream1
                .sorted()
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

        sortedStream2
                .sorted((s1, s2) -> s2.length() - s1.length())
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

        // 5. map()
        // method: <R> Stream<R> map(Function<? super T,? extends R> mapper)
        // do certain operation on each element of current stream. map() will not change
        // the count of elements, but may change the type
        Stream<String> mapStream = Stream.of("a", "b", "c", "d", "e");
        mapStream
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

        // 6. flatMap()
        // method: <R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper)
        // the flatMap() is to run map() on each element of each stream, and merge all the elements from
        // each stream to build a new Stream
        Stream<List<String>> flatMapStream = Stream.of(Arrays.asList("1", "2", "3"), Arrays.asList("4", "5"));
        flatMapStream
                .flatMap(list -> list.stream()) // run list.stream() on each list in the stream and merge all elements
                .forEach(s -> System.out.print(s + " ")); // print each element in this new stream
        System.out.println();
    }
}
