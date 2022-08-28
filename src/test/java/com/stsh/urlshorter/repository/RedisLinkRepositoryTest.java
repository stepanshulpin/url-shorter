package com.stsh.urlshorter.repository;

import com.stsh.urlshorter.model.Link;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLinkRepositoryTest {

    @Autowired
    private RedisLinkRepository redisLinkRepository;

    @Test
    public void returnSameLinkAsArgument() {
        Link link = new Link("https://spring.io", "aaaa22");
        StepVerifier.create(redisLinkRepository.save(link))
                .expectNext(link)
                .verifyComplete();
    }

    @Test
    public void savesInRedis() {
        Link link = new Link("https://spring.io", "aaaa22");
        StepVerifier.create(redisLinkRepository.save(link)
                        .flatMap(__ -> redisLinkRepository.findByKey(link.getKey())))
                .expectNext(link)
                .verifyComplete();
    }

}