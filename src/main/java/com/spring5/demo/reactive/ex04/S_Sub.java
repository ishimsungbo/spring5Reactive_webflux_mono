package com.spring5.demo.reactive.ex04;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class S_Sub implements Subscriber<Integer> {

    private Subscription subscription;
    int bufferSize = 2;

    @Override
    public void onSubscribe(Subscription subscription) {
        log.info("onSubscribe");

        this.subscription = subscription;
        this.subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Integer item) {
        log.info("onNext " + item);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("onError");
    }

    @Override
    public void onComplete() {
        log.info("onComplete");
    }
}
