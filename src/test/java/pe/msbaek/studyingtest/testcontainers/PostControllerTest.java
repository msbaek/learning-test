package pe.msbaek.studyingtest.testcontainers;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import pe.msbaek.studyingtest.AbstractTestContainerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testingisdocumenting.webtau.Matchers.equal;
import static org.testingisdocumenting.webtau.WebTauDsl.http;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostControllerTest extends AbstractTestContainerTest {
    @LocalServerPort
    int port;

//    @Autowired
//    TestRestTemplate restTemplate;
//
//    @Test
//    void name() {
//        ResponseEntity<String> forEntity = restTemplate.getForEntity("/api/posts", String.class);
//        System.out.println("forEntity = " + forEntity);
//        System.out.println(forEntity.getBody());
//    }

    @Test
    void getAll() {
        http.get(getUrl() + "/api/posts", (header, body) -> {
            header.statusCode.should(equal(200));
            System.out.println("body = " + body.get(1));
            assertThat(body.numberOfElements()).isEqualTo(100);
       });
    }

    @NotNull
    private String getUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void shouldFindPostWhenValidPostID() {
        http.get(getUrl() + "/api/posts/1", (header, body) -> {
            header.statusCode.should(equal(200));
            body.get("id").should(equal(1));
        });
    }
}