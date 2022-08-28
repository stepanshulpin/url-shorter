package com.stsh.urlshorter.controller;

import com.stsh.urlshorter.model.CreateLinkRequest;
import com.stsh.urlshorter.model.CreateLinkResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class LinkController {

    @PostMapping("/link")
    Mono<CreateLinkResponse> create(@RequestBody CreateLinkRequest request) {
        return Mono.just(new CreateLinkResponse("http://localhost:8080/aass2211"));
    }

}
