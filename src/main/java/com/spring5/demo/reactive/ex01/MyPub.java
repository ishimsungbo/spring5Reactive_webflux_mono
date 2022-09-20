package com.spring5.demo.reactive.ex01;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Arrays;

public class MyPub implements Publisher<Integer> {

    // 신문사에서 어떤 정보를 들고 있는지 정의를 한다.
    Iterable<Integer> its = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);


    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        System.out.println("1.구독자 : 신문사야 나 너희 신문 볼래");
        System.out.println("2.신문사 : 알겠어 ~~~ 구독 정보를 만들어서 줄테니 기다려!! 데이터를 주께");

        // 발행할 데이터를 만들자 : MySubscription
        MySubscription subscription = new MySubscription(subscriber, its); //구독자와 데이터를 넘긴다.

        System.out.println("3.신문사 : 구독 정보 생성 완료 했어. 잘받아!!");

        subscriber.onSubscribe(subscription);

    }
}
