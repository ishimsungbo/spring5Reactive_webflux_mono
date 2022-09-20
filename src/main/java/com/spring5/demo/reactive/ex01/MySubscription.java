package com.spring5.demo.reactive.ex01;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;

// 구독정보<구독자, 어떤 데이터 구독할지>
public class MySubscription implements Subscription {

    private Subscriber s;
    private Iterator<Integer> it;

    public MySubscription(Subscriber s, Iterable<Integer> its) {
        this.s = s;
        this.it = its.iterator();
    }

    @Override
    public void request(long n) {
        System.out.println("MySubscription 호출 : " + n);
        while (n>0){
            if(it.hasNext()){
                s.onNext(it.next());
            }else{
                System.out.println("MySubscription 에서 request 그리고 Subscriber.onComplete 호출");
                s.onComplete();
                break;
            }

            n--;
        }

        System.out.println("MySubscription 호출 마지막");
    }

    @Override
    public void cancel() {

    }
}
