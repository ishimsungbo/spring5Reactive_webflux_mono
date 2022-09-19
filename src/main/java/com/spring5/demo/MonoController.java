package com.spring5.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;

@RestController
@Slf4j
public class MonoController {

    @GetMapping("/mono03")
    Mono<String> hello3(){
        log.info("mono 3 .. pos1 *********************");
        return Mono.just("123").doOnNext(x -> log.info("모노 안입니다. " + x));
    }


    @GetMapping("/mono02")
    public String monoStatus(){
        log.info("pos1 *********************");

        Mono m = Mono.just(generateHello())
                .log()
                .doOnNext(x -> {System.out.println("2로그 : " + x); });



        log.info("pos2 *********************");

        m.subscribe();

        return "실행완료";
    }

    @GetMapping("/mono01")
    Mono<String> hello(){

        log.info("pos1 *********************");

        Mono m = Mono.just(generateHello())
                .log()
                .doOnNext(x -> {System.out.println("2로그 : " + x); });


        /*
                .flatMap(new Function<String, Mono<? extends String>>() {
            @Override
            public Mono<? extends String> apply(String s) {
                System.out.println("flatMap ====> 원래 데이터에 하이하이를 붙힌다.");
                return Mono.just(s+"  하이하이");
            }) */
        //Publisher ->(Publisher) -> (Publisher) -> Subscriber

        log.info("pos2 *********************");

        String str = (String) m.block();

        System.out.println("블락 : " + str);

        return m;
    }

    /**
     * Mono로 감싸는 것은?
     * 컨테이너라고 봐도 될듯. List<String> List도 컨테이너잔아. == 메모리 공간..
     */

    private String generateHello(){
        log.info("log generateHello");
        return "Hello Mono";
    }
}

