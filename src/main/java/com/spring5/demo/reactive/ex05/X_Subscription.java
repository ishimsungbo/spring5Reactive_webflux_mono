package com.spring5.demo.reactive.ex05;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;

@Slf4j
public class X_Subscription implements Subscription {

    private Subscriber subscriber;
    private Iterator<User> it;

    private static int NUMBER_M = 0;

    public X_Subscription(Subscriber subscriber, Iterable<User> itr) {
        this.subscriber = subscriber;
        this.it = itr.iterator();
    }

    @Override
    public void request(long n) {
        NUMBER_M++;
        log.info("S_Subscription.request" + NUMBER_M);

        while (n-- > 0) {
            if (it.hasNext()) {
                subscriber.onNext(it.next());
            } else {
                subscriber.onComplete();
                break;
            }
        }
    }

    @Override
    public void cancel() {

    }
}
