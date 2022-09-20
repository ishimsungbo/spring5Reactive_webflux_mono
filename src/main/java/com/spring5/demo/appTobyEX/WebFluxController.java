package com.spring5.demo.appTobyEX;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

    @GetMapping(value = "/webflux/events2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> events2() {
        //Stream<Event> s = Stream.generate(()->new Event(System.currentTimeMillis(),"value"));  //무한이 됨.
        //return Flux.fromStream(s);
        return Flux.fromStream(Stream.generate(()-> new Event(System.currentTimeMillis(),"value")))
                .delayElements(Duration.ofSeconds(1))
                .take(10);
    }

    @GetMapping(value = "/webflux/events3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> events3() {
        return Flux
                .<Event>generate(sink -> sink.next(new Event(System.currentTimeMillis(),"value")))
                .delayElements(Duration.ofSeconds(1))
                .take(10);
    }

    @GetMapping(value = "/webflux/events4", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> events4() {
        return Flux   //generate 는 생성한다?
                .<Event, Long>generate(()->1L, (id, sink)->{
                    sink.next(new Event(id, "value" + 1));
                    return id +1;
                })
                .delayElements(Duration.ofSeconds(1))
                .take(10);
    }

    @GetMapping(value = "/webflux/events5", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> events5() {
        // 각각 두개 만들어보기
        Flux<Event> es = Flux.<Event, Long>generate( ()->1L, (id, sink)->{
                            sink.next(new Event(id, "value" + id));
                            return id +1;
                        });

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

        // 묶기
        return Flux.zip(es, interval).map(tu -> tu.getT1()).take(10);
    }

    @GetMapping(value = "/webflux/events6", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> events6() {
        // 각각 두개 만들어보기
        Flux<String> es = Flux.just("ABC","MNB");

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

        // 묶기
        return Flux.zip(es, interval).map(tu -> tu.getT1()).take(10);
    }

    @Data
    @AllArgsConstructor
    public static class Event{
        long id;
        String value;
    }

    //요청하면 한방에 다 내보냄...그동안은 블럭킹 상태
    @GetMapping(value = "/webflux/events7")
    public List<String> getListString() throws InterruptedException{

        List<String> list = new ArrayList<>();

        for(int i=0 ;i < 10 ; i++){

            Thread.sleep(1000);
            System.out.println("========" + i);
            String str ="hello" + i;
            list.add(str);
        }

        return list;
    }

}
