package com.stsh.urlshorter.service;

import com.stsh.urlshorter.model.Link;
import com.stsh.urlshorter.repository.LinkRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LinkServiceTest {

    private final LinkRepository linkRepository = mock(LinkRepository.class);
    private final LinkService linkService = new LinkService("http://some-domain.com/", linkRepository);

    @Before
    public void setup() {
        when(linkRepository.save(any()))
                .thenAnswer((Answer<Mono<Link>>) invocationOnMock -> Mono.just(invocationOnMock.getArgument(0)));
    }

    @Test
    public void shortensLink() {
        StepVerifier.create(linkService.shortenLink("https://spring.io"))
                .expectNextMatches(result -> result != null && result.length() > 0
                        && result.startsWith("http://some-domain.com/"))
                .expectComplete()
                .verify();
    }

}