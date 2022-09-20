package com.spring5.demo.reactive.ex04;

public class ReactiveDriven {
    public static void main(String[] args) {
        S_Pub pub = new S_Pub();
        S_Sub sub = new S_Sub();

        pub.subscribe(sub);

    }
}
