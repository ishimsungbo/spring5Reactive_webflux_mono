package com.spring5.demo.reactive.ex01;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySub implements Subscriber<Integer> {

    private Subscription s;

    @Override
    public void onSubscribe(Subscription s) {
        System.out.println("4.구독자 난 <MySub> : 구독 정보 받기 시작");
        this.s = s;

        int rData = 1;

        //구독 정보에 있는 requst를 호출한다.
        System.out.println("5. 구독자 : 받을 데이터는  "+rData+"  <<< 만큼 받을 거임..");
        s.request(rData);
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println("onNext 구독 데이터 전달 : " + integer);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("구독중 에러");
    }

    @Override
    public void onComplete() {
        System.out.println("구독 완료");
    }
}
