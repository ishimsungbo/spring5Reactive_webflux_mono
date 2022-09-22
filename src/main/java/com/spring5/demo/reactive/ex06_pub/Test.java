package com.spring5.demo.reactive.ex06_pub;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {

        Iterable<Integer> itr = Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList());

        Iterator iterator = itr.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }
}
