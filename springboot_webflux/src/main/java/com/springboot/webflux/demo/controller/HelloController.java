package com.springboot.webflux.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class HelloController {
	@GetMapping(value = { "/", "hello" })
	public Mono<String> sayHelloWorld() {
		return Mono.just("Hello World");
	}
}
