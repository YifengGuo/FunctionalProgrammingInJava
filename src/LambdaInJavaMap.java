/**
 * Created by guoyifeng on 8/21/18.
 */

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * To illustrate lambda expression in Java Map
 */
public class LambdaInJavaMap {
    public static void main(String[] args) {
        /**
         * example 1: Map.foreach()
         * void forEach(BiConsumer<? super K,? super V> action) is to
         * take action on each entry in the map based on apply()   <-  void accept(T t, U u)
         *
         * fold all the elements
         */
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        /***************             JDK 7 Style               ***************/
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        /***************             JDK 8 Style               ***************/
        map.forEach((k, v) -> System.out.println(k + "=" + v));


        /**
         * getOrDefault()
         * V getOrDefault(Object key, V defaultValue)
         * get the value by the given key, if not found, return default value
         */

        /***************             JDK 7 Style               ***************/
        if (map.containsKey(4)) {
            System.out.println(map.get(4));
        } else {
            System.out.println("NoValue");
        }

        /***************             JDK 8 Style               ***************/
        System.out.println(map.getOrDefault(4, "NoValue"));

        /**
         * putIfAbsent()
         * V putIfAbsent(K key, V value)
         * put value mapping to key only when key does not exist or key's mapping is null
         */
        map.putIfAbsent(4, "four");
        System.out.println("put 4 into map " + map.get(4));
        map.put(5, null);
        System.out.println("when 5 maps to null: " + map.get(5));
        map.putIfAbsent(5, "five");
        System.out.println("after put 5: " + map.get(5));

        /**
         * Java 8 replace()
         * replace(K key, V value)，only when key-value mapping exists in the map, then replace it with new value,
         * or do nothing
         *
         * replace(K key, V oldValue, V newValue)，only when key-value mapping exists in the map, and the mapping value
         * and the value exactly equals to the oldValue, then replace it with new value, or do nothing
         */
        map.replace(6, "newSix");
        System.out.println("when 6 is no in the map: " + map.get(6) + " replace() does not work");
        map.put(6, "six");
        map.replace(6, "newSix");
        System.out.println("when 6 is in the map: " + map.get(6) + " replace() changes its value");


        /**
         * replaceAll()
         * replaceAll(BiFunction<? super K,? super V,? extends V> function)
         * BiFunction ->    only one abstract method :  R apply(T t, U u)
         *
         * try to replace all the entry's value in this map to upper(lower) case
         */

        /***************             JDK 7 Style               ***************/
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            map.put(entry.getKey(), entry.getValue().toUpperCase());
        }
        map.forEach((k, v) -> System.out.println(k + " -> " + v));

        /***************             JDK 8 Style               ***************/
        map.replaceAll((k, v) -> v.toLowerCase());
        map.forEach((k, v) -> System.out.println(k + " -> " + v));

        /**
         * merge()
         * merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction)
         * the meaning of merge() is:
         *     1. if key does not exist or key's value is null, then map given value (@NotNull) to the key
         *     2. if key has value, then invoke remappingFunction(), and map the result value (if not null) to the
         *        key. If the value is null, then remove this key from the map
         *
         * use case: connect new log info to the existed log info
         */
        Map<String, String> logMap = new HashMap<>();
        logMap.put("error", "first time RuntimeError");
        logMap.merge("error", "second time OutOfMemoryError", (v1, v2) -> v1 + " " + v2);
        System.out.print("merge new error to exited error: " + logMap.get("error") + "\n");

        logMap.merge("exception", "NullPointerException", (v1, v2) -> v1 + v2);
        System.out.println("merge NullPointerException to exception which does not have mapping value: " +
        logMap.get("exception") + "\n");

        logMap.put("testNullValue", "hasValue");
        System.out.println("At this moment, logMap has key testNullValue: " + logMap.get("testNullValue"));
        // merge null to testNullValue would make map remove this key
        // line below would cause NPE
        // logMap.merge("testNullValue", null, (v1, v2) -> null);
        // System.out.println("merge null to testNullValue: " + logMap.get("testNullValue") + "\n");


        /**
         * compute()
         * compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)
         * connect result returned by remappingFunction() to the key if result is not null
         * or remove key from the map
         *
         * computeIfAbsent()
         * V computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction)
         * only when map does not exist this key or mapping of this key is null, will it invoke
         * mappingFunction()
         * Function: -> R apply(T t)
         */
        Map<String, LinkedHashSet<Integer>> testCompute = new HashMap<>();
        int primeNumber1 = 1;
        int primeNumber2 = 3;
        testCompute.compute("primeNumber", (k, v) -> {
            if (v == null) {
                v = new LinkedHashSet<>();
            }
            v.add(primeNumber1);
            return v;
        });
        // when k is null, create a new linkedhashset, return this set and then add value
        testCompute.computeIfAbsent("primeNumber", k -> new LinkedHashSet<>()).add(primeNumber2);
        System.out.println(testCompute.get("primeNumber"));
    }
}
