package com.spring5.demo.reactive.ex05;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Arrays;

@Slf4j
public class X_Pub implements Publisher {

    //Iterable<Integer> its = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
    Iterable<User> its = Arrays.asList( new User("심성보","00001")
                                       ,new User("김구라","00002")
                                       ,new User("신동엽","00003")
                                       ,new User("현빈","00004")
                                       ,new User("원빈","00005")
                                       ,new User("박진영","00006")
                                       ,new User("김장훈","00007")
                                      );
    @Override
    public void subscribe(Subscriber subscriber) {
        log.info("1. 구독자 입니다.");
        X_Subscription subscription = new X_Subscription(subscriber,its);

        subscriber.onSubscribe(subscription);
    }
}