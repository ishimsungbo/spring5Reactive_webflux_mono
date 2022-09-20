package com.spring5.demo.reactive.ex04;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Arrays;

@Slf4j
public class S_Pub implements Publisher {

    Iterable<Integer> its = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

    @Override
    public void subscribe(Subscriber subscriber) {
        log.info("1. 구독자 입니다.");
        S_Subscription subscription = new S_Subscription(subscriber,its);

        subscriber.onSubscribe(subscription);
    }
}
