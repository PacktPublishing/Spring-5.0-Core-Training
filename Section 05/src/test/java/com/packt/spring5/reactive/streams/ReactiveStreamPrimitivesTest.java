package com.packt.spring5.reactive.streams;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class ReactiveStreamPrimitivesTest {

    @Test
    public void shouldStreamSingleValue() throws Exception {
        Mono.just("hello")
                .map(String::toUpperCase)
                .doOnSubscribe(subscription -> System.out.println("==Subscribed"))
                .doOnRequest(request -> System.out.println("==Requested"))
                .doOnSuccess(complete -> System.out.println("==Stream completed"))
                .log()
                .subscribe();
    }

    @Test
    public void shouldStreamFromGenerator() throws Exception {
        Flux.just("a", "b", "c")
                .concatWith(Flux.just("d", "e", "a"))
                .distinct()
                .log()
                .subscribe();
    }

    @Test
    public void shouldStreamFromIterable() throws Exception {
        Flux.fromIterable(Arrays.asList("a", "b", "c", "d"))
                .repeat(3)
                .filter("a"::equals)
                .map(String::toUpperCase)
                .log()
                .subscribe();
    }
}
