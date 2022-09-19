package com.spring5.demo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class WebFluxController {

    @GetMapping("/webflux/ex01/{id}")
    public Mono<Event> ex01(@PathVariable long id){
        return Mono.just(new Event(id, "event " + id)).log();
    }

    @GetMapping("/webflux/event")
    public Mono<List<Event>> ex02(){
        List<Event> list = Arrays.asList(new Event(1L,"첫번째"),new Event(2L,"두번째"));
        return Mono.just(list).log();
    }

    @GetMapping("/webflux/events")
    public Flux<Event> events() {
        List<Event> list = Arrays.asList(new Event(1L,"첫번째"),new Event(2L,"두번째"));
        return Flux.fromIterable(list).log();
    }

    @Data
    @AllArgsConstructor
    public static class Event{
        long id;
        String value;
    }
}
