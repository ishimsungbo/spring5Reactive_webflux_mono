package com.spring5.demo.appTobyEX;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

@RestController
public class ReactiveController {

    @RequestMapping("/")
    public String index(){
        System.out.println("index page call");
        return "index page";
    }

    @GetMapping("/mono")
    Flux<String> hello() {
        return Flux.just("Hello", "World");
    }

    @GetMapping("/stream")
    Flux<Map<String, Integer>> stream(){
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1); // 자바 8의 무한 스트림
        System.out.println(stream);
    return Flux.fromStream(stream)   ////stream.limit(100)
            .map(i -> Collections.singletonMap("value",i));
    }

}
