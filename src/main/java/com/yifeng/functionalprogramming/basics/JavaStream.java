package com.yifeng.functionalprogramming.basics; /**
 * Created by guoyifeng on 8/23/18.
 */

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1. To create a stream: (Collection).stream(), (Collection).parallelStream(), Arrays.stream(T[] arr)
 *    The datasource depended by Stream must be container or array and could NOT be map.
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
 *     So like Flink, Intermediate Operation apis usually return another Stream, otherwise it should be
 *     Terminal Operation
 */
public class JavaStream {
    public static void main(String[] args) {
        /**
         * Basic Stream API
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



        /**
         * Stream Reduction Operation API
         */
         
        // reduce()
        // reduce() usually is to merge a couple of elements with some operation and yields only one result at last
        // methods:
        //     Optional<T> reduce(BinaryOperator<T> accumulator)
        //     T reduce(T identity, BinaryOperator<T> accumulator)
        //     <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
        
        // Optional<T> reduce(BinaryOperator<T> accumulator)
        // find the word with longest length
        Stream<String> reduceStream1 = Stream.of("I", "love", "you", "too");
        // Optional<T> is a container may or may not contain a NON-NULL value
        Optional<String> longest = reduceStream1.reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
        System.out.println(longest.get());

        // <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
        // This function has three parameters:
        //         1. U identity can represent initial value of the reusult of this reduce() operation
        //         2. accumulator defines the rule of how new element will be reduced with partial result
        //         3. combiner defines the rule of how several partial results will be reduced. Combiner will
        //            only work under parallel computation.
        
        // Calculate the total length of several words(sentences)
        Stream<String> reduceStream2 = Stream.of("This", "is a demo", "of how reduce function", "works in Java8");
        int length = reduceStream2.reduce(
                0,
                (sum, s) -> sum + s.length(),
                (a, b) -> a + b
                );
        System.out.println(length);


        /**
         * collect()
         *   reduce() is capable of calculating and returning a single value
         *   what if we need to get a map or a list of elements from a stream ?
         *   This is a job done by collect()
         */

        /**
         * Steps to convert a Stream to a container(Map, List...)
         *  1. What is the type of target container? HashSet, ArrayList, HashMap ?
         *  2. How to add new element to the container? map.put(), list.add() ?
         *  3. What if under multi-thread condition, how to merge two partial collectors ?
         *
         *  Based on three cases above, we have method signature of collect():
         *  <R> R collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)
         *  supplier: target container
         *  accumulator: how to add new element
         *  combiner: how to merge partial results
         *
         *  To simplify the process, we can use Collector
         *  collect(Collector<? super T,A,R> collector)
         *  class Collector is designed for stream.collect()
         *  Collectors is utility class for Collector (like Collections to Collection)
         */

        /**
         * example 1: convert a Stream to a List
         */
        Stream<String> streamToList1 = Stream.of("I", "love", "you", "too"); // one stream can only be consumed once
        Stream<String> streamToList2 = Stream.of("I", "love", "you", "too");


        // method 1: use collect()
        List<String> resList1 = streamToList1.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        // method 2: use Collectors
        List<String> resList2 = streamToList2.collect(Collectors.toList());

        printCollection(resList1);
        printCollection(resList2);

        /**
         * example 2: generate Collection via collect()
         */
        // if no requirement on the type, mutability, serializability, or thread-safety of the Collection returned
        Stream<Integer> streamToCollection1 = Stream.of(1, 2, 3, 4);
        Stream<Integer> streamToCollection2 = Stream.of(1, 2, 3, 4, 4, 2, 3, 6, 5, 1);
        List<Integer> noTypeList1 = streamToCollection1.collect(Collectors.toList());
        Set<Integer> noTypeSet1 = streamToCollection2.collect(Collectors.toSet());

        // if requirement needed on the type, mutability, serializability, or thread-safety of the Collection returned
        // use Collectors.toCollection(? extends Collection)
        Stream<Integer> streamToCollection3 = Stream.of(1, 2, 3, 4);
        Stream<Integer> streamToCollection4 = Stream.of(1, 2, 3, 4, 4, 2, 3, 6, 5, 1);
        List<Integer> typedList1 = streamToCollection3.collect(Collectors.toCollection(LinkedList::new));
        Set<Integer> typedSet = streamToCollection4.collect(Collectors.toCollection(TreeSet::new));
        printCollection(noTypeList1);
        printCollection(noTypeSet1);
        printCollection(typedList1);
        printCollection(typedSet);

        /**
         * example 3: generate Map via collect()
         * Stream cannot be built from a Map but Stream can be converted to a Map by collect()
         * What to need to focus is Key and Value
         * 3 ways to get Map from Steam: collect(Collector)
         *     1. Collector Collectors.toMap()   user to define key and value
         *     2. Collector Collectors.partitioningBy()
         *     3. Collector Collectors.groupingBy()
         */

        // 1. Collector Collectors.toMap()
        // calculate GPA of all the students
        Student s1 = new Student("Bob", 59.0);
        Student s2 = new Student("Paul", 72.0);
        Student s3 = new Student("William", 83.0);
        Student s4 = new Student("Kelly", 65.0);
        Student s5 = new Student("Jerry", 95.0);
        Student s6 = new Student("Andrew", 56.0);
        // Function.identity() is a static method in this interface. It means t -> t (returning the original input)
        Map<Student, Double> studentGPAMap = Stream.of(s1, s2, s3, s4, s5, s6)
                .collect(Collectors.toMap(
                        Function.identity(), // key of map, the original input Student object
                        student -> computeGPA(student) // value of map
                ));
        studentGPAMap.forEach((k, v) -> System.out.println("Student " + k.name + "'s GPA is " + v ));

        // 2. Collector Collectors.partitioningBy() is to partition elements in the stream into two parts by
        //    some condition and store it to a map

        // case 1: partition students by whether they pass the exam
        Map<Boolean, List<Student>> studentPassMap = Stream.of(s1, s2, s3, s4, s5, s6)
                .collect(Collectors.partitioningBy(
                        student -> student.grade >= 60 // predicate condition to partition the elements
                ));
        studentPassMap.forEach((k, v) -> {
            if (k) {
                System.out.println("Students who have passed: ");
                printCollection(v);
            } else {
               System.out.println("Students who have flunked: ");
                printCollection(v);
            }
        });

        // case 2: group employees by their departments
        Employee e1 = new Employee("Bob", "Market");
        Employee e2 = new Employee("Paul", "Tech");
        Employee e3 = new Employee("William", "Management");
        Employee e4 = new Employee("Kelly", "Market");
        Employee e5 = new Employee("Jerry", "Tech");
        Employee e6 = new Employee("Andrew", "Management");
        List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5, e6);
        System.out.print("\n");
        Map<String, List<Employee>> employeeByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment // map(group) elements by a field of it
                ));
        employeeByDepartment.forEach((k, v) -> {
            System.out.println("Department " + k + " has employees: ");
            printCollection(v);
        });

        // case 3: get the employee count of each department
        // the logic is like SQL query
        System.out.print("\n");
        // method signature of groupingBy():
        // static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream)
        Map<String, Long> deptCountMap = employees.stream()
                .collect(Collectors.groupingBy(  // upstream Collector
                        Employee::getDepartment,  // Employee::getDepartment is like a classifier
                        Collectors.counting() // downstream Collector
                ));
        deptCountMap.forEach((k, v) -> {
            System.out.println("Department " + k + " has " + v + " people.");
        });


        // case 4: group the employees by their department and only keep their names
        System.out.print("\n");
        Map<String, List<String>> deptAndNames = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, // upstream collector
                        Collectors.mapping(Employee::getName,  // downstream collector   Employee::getName is the mapper of mapping() method
                                Collectors.toList())));        // further downstream collector    collect employees' names of department into a list
        deptAndNames.forEach((k, v) -> {
            System.out.println("Department " + k + " has employees: ");
            printCollection(v);
        });
        System.out.print("\n");


        // addition: Collectors.joining() to concatenate strings
        Stream<String> joiningStringStream = Stream.of("This", "is", "joining", "demo");
        String joiningRes = joiningStringStream.collect(Collectors.joining(" "));
        System.out.println(joiningRes);

    }

    private static <T> void printCollection(Collection<? extends T> collection) {
        for (T t : collection) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    static class Student {
        String name;
        double grade;

        public Student(String name, double grade) {
            this.name = name;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", grade=" + grade +
                    '}';
        }
    }

    private static double computeGPA(Student student) {
        double grade = student.grade;
        if (grade < 60) {
            return 0;
        } else if (grade == 100) {
            return 4.0;
        } else {
            return (int) ((grade - 60) / 10) + 1.0;
        }
    }

    static class Employee {
        String name;
        String department;

        public Employee(String name, String department) {
            this.name = name;
            this.department = department;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", department='" + department + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }
    }
}
