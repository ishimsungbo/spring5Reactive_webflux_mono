package com.spring5.demo.reactive.ex05;

public class X_ReactiveDriven {
    public static void main(String[] args) {
        X_Pub pub = new X_Pub();
        X_Sub sub = new X_Sub();

        pub.subscribe(sub);

    }
}
