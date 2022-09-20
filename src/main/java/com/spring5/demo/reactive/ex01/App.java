package com.spring5.demo.reactive.ex01;

public class App {
    public static void main(String[] args) {
        MyPub pub = new MyPub();   //신문사 생성
        MySub sub = new MySub();   //구독자 생성

        pub.subscribe(sub);

        //MySub 는 구독자다...구독자 객체를 여러개 만들어서 구독을 받는다..

    }
}
