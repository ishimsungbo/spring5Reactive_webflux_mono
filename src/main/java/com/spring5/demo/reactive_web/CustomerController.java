package com.spring5.demo.reactive_web;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final Sinks.Many<Customer> sink;

    // A요청 -> Flux - > Stream
    // B요청 -> Flux - > Stream
    // -> Flux.merge -> sink

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        sink = Sinks.many().multicast().onBackpressureBuffer();  //배압  모든 클라이언트가 접근 가능하다.
    }

    @GetMapping("/customer/sse")
    public Flux<ServerSentEvent<Customer>> findALLSSE(){  // 생략 MediaType.Text_EVENT....
        return sink.asFlux().map( c-> ServerSentEvent.builder(c).build()).doOnCancel(()->{
            sink.asFlux().blockLast();
        }).log();
    }


    @PostMapping("/customer")
    public Mono<Customer> save(){
        //sink에다가 데이터를 푸시한다.

        return customerRepository.save(new Customer("gilDong","hong"))
                .doOnNext(c -> {
                    sink.tryEmitNext(c);
                })
                .log();
    }



    @GetMapping("/customer/{id}")
    public Mono<Customer> findById(@PathVariable Long id){
        return customerRepository.findById(id).log();
    }

    @GetMapping("/customer/test")
    public Flux<Customer> findAll(){
        return customerRepository.findAll().log();
    }

    @GetMapping("/flux")
    public Flux<Integer> fluxInt(){
        return Flux.just(1,2,3,4,5).delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> fluxstream(){
        return Flux.just(1,2,3,4,5).delayElements(Duration.ofSeconds(1)).log();
    }

}
