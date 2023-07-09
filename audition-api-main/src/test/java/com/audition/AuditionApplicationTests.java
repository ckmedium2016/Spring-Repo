package com.audition;

import static reactor.core.publisher.Mono.when;
import com.audition.model.AuditionPost;
import com.audition.web.AuditionController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebFluxTest(excludeAutoConfiguration = ReactiveSecurityAutoConfiguration.class)
class AuditionApplicationTests {
    @Autowired
    private AuditionController auditionController;
    @Autowired
    private WebTestClient webClient;
    private AuditionPost auditionPost;

    @Test
    void contextLoads() {
    }

    /*   public void stepVerifierTest() {
        Flux<String> fluxCalc = Flux.just(1, 50, 200)
            .map(i -> "10 / " + i + " = " + (10 / i));


          fluxCalc.subscribe(value -> System.out.println("Next: " + value),
            error -> System.err.println("Error: " + error));

        StepVerifier.create(fluxCalc)
            .expectNextCount(1)
            .expectError(ArithmeticException.class)
            .verify();
    }



    @Test
    public void shouldGetPosts() {
        String TEST_POST_ID = "12";
        int id = 12;
        int userId = 2;
        String title = "in quibusdam tempore odit est dolorem";
        String body = "itaque id aut magnam\\npraesentium quia et ea odit et ea voluptas et\\nsapiente quia nihil amet occaecati quia id voluptatem\\nincidunt ea est distinctio odio";
        AuditionPost audition = new AuditionPost.AuditionPostBuilder()
            .setId(id)
            .setUserId(userId)
            .setTitle(title)
            .setBody(body)
            .build();
        when((Publisher<?>) auditionController.getPosts(TEST_POST_ID)).thenReturn(Mono.just(audition));

        webClient
            .post().uri("/posts/12")
            .bodyValue(audition)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(AuditionPost.class);


    }

   */
}
