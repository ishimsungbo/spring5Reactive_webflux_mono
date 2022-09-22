package com.spring5.demo.reactive.ex06_pub;

import io.r2dbc.spi.Parameter;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PubSub {
    public static void main(String[] args) {

        // 2022-09-22
        // 토비 리액티브 프로그래밍(2) 공부

        Iterable<Integer> iterator = Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList());

        // 생산자,발행자 정의
        Publisher<Integer> pub = new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> sub) {
                sub.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) {

                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };

        // 소비자, 구독자 정의
        Subscriber<Integer> sub = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        // 구독진행
        pub.subscribe(sub);

    } // main end -----------------------
}
