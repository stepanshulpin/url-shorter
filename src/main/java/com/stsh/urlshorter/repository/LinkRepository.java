package com.stsh.urlshorter.repository;

import com.stsh.urlshorter.model.Link;
import reactor.core.publisher.Mono;

public interface LinkRepository {

    Mono<Link> save(Link link);

    Mono<Link> findByKey(String key);
}
