package com.spring5.demo.appTobyEX;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("*3개의 바구니에 데이터들이 존재한다.");
        final List<String> basket1 = Arrays.asList(new String[]{"kiwi", "orange", "lemon", "orange", "lemon", "kiwi"});
        final List<String> basket2 = Arrays.asList(new String[]{"banana", "lemon", "lemon", "kiwi"});
        final List<String> basket3 = Arrays.asList(new String[]{"strawberry", "orange", "lemon", "grape", "strawberry"});

        //바구니를 한곳에 모음
        final List<List<String>> baskets = Arrays.asList(basket1,basket2,basket3);

        System.out.println("바구니 속 과일들 : "+baskets.toString());
        System.out.println("바구니 수: " + baskets.size());

        System.out.println("");

        final Flux<List<String>> basketFlux = Flux.fromIterable(baskets);

        System.out.println("===>  : "+basketFlux.toString());

        basketFlux.subscribe(System.out::println);

        // List<List<String>> name = new ArrayList<>();
        // basketFlux.subscribe(name::add);
        // System.out.println(" : " + name.toString());

    }

}
