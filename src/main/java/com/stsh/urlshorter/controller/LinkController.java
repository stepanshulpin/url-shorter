package com.stsh.urlshorter.controller;

import com.stsh.urlshorter.model.CreateLinkRequest;
import com.stsh.urlshorter.model.CreateLinkResponse;
import com.stsh.urlshorter.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/link")
    Mono<CreateLinkResponse> create(@RequestBody CreateLinkRequest request) {
        return linkService.shortenLink(request.getLink())
                .map(CreateLinkResponse::new);
    }

    @GetMapping("/{key}")
    Mono<ResponseEntity<Object>> getLink(@PathVariable String key) {
        return linkService.getOriginalLink(key)
                .map(link -> ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                        .header("Location", link.getOriginalLink())
                        .build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
