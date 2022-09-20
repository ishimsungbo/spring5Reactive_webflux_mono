package com.spring5.demo.reactive.ex05.ex04;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class X_Sub implements Subscriber<User> {

    private Subscription subscription;
    int bufferSize = 2;

    @Override
    public void onSubscribe(Subscription subscription) {
        log.info("onSubscribe");

        this.subscription = subscription;
        //this.subscription.request(Long.MAX_VALUE);
        this.subscription.request(2);
    }

    @Override
    public void onNext(User user) {
        System.out.println("onNext " + user.toString());
        //--bufferSize;

        if(--bufferSize <= 0){

            bufferSize = 2;
            this.subscription.request(2);
        }
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
