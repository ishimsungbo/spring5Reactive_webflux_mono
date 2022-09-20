package com.spring5.demo.reactive.ex04;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;

@Slf4j
public class S_Subscription implements Subscription {

    private Subscriber subscriber;
    private Iterator<Integer> it;

    private static int NUMBER_M = 0;

    public S_Subscription(Subscriber subscriber, Iterable<Integer> itr) {
        this.subscriber = subscriber;
        this.it = itr.iterator();
    }

    @Override
    public void request(long n) {
        NUMBER_M++;
        log.info("S_Subscription.request" + NUMBER_M);
        while (true){
            if(it.hasNext()){
                this.subscriber.onNext(it.next());
            }else{
                this.subscriber.onComplete();
                break;
            }
        }

    }

    @Override
    public void cancel() {

    }
}
