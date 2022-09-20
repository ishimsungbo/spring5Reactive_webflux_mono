package com.spring5.demo.reactive.ex03;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.Iterator;

//토비 예제
public class PubSub {

    private static int NUMBER_M = 0;

    public static void main(String[] args) {
        Iterable<Integer> itr = Arrays.asList(1,2,3,4,5);

        Publisher p = new Publisher() {
            @Override
            public void subscribe(Subscriber subscriber) {

                Iterator<Integer> it = itr.iterator();

                subscriber.onSubscribe(new Subscription() { // ***
                    @Override
                    public void request(long n) {  //빽프러셔
                        try{
                            NUMBER_M++;
                            System.out.println("Subscription 생성자 호출 " + NUMBER_M);
                            while (n-- > 0) {
                                if (it.hasNext()) {
                                    subscriber.onNext(it.next());
                                } else {
                                    subscriber.onComplete();
                                    break;
                                }
                            }
                        }catch (RuntimeException e){
                            subscriber.onError(e);
                        }

                        /** 이건 한방에 처리하는 것...좀 무식하지
                         while (true) {
                             if (it.hasNext()) {
                                subscriber.onNext(it.next());
                             } else {
                                subscriber.onComplete();
                                break;
                             }
                         }
                         **/

                    }

                    @Override
                    public void cancel() {

                    }
                });  //subscription 콜백 이게 기존과 다름.
            }
        };

        // 소비자 쪽..

        Subscriber<Integer> s = new Subscriber<Integer>() {

            Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                //빽프레셔....기능 속도차가 발생했을 때... Subscription 을 통해...몬가 하는것...
                System.out.println("onSubscribe");

                //나는 데이터를 어떻게 받겠어? 가 필요하다.
                //경우 퍼블리셔는 100만개를 쏠 수 있지만, 소비자는 1개에 1초 처리한다...이런경우... 빽프레셔...기능이 필요하다.

                //subscription.request(Long.MAX_VALUE);   // 있는거 다 주세요..!!

                this.subscription = s;
                this.subscription.request(2);   // 2개 받고 처리...그 다음 나머지를 받을래?는 어디서? onNext에서 처리한다?
            }

            int bufferSize = 2;

            @Override
            public void onNext(Integer item) {
                System.out.println("onNext : " + item);
                //System.out.println("===> 버퍼사이즈 조절" + bufferSize);
                if(--bufferSize <= 0){
                    //System.out.println("버퍼사이즈 조절" + bufferSize);
                    bufferSize = 2;
                    this.subscription.request(2);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError()");  //감당할 수 있는 건가?에 따라 다시 요청?  this.subscription.request(2);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete()");
            }
        };

        p.subscribe(s);

    }
}
