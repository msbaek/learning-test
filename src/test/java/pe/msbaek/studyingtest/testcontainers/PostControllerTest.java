package pe.msbaek.studyingtest.testcontainers;

import org.eclipse.jetty.http.HttpStatus;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testingisdocumenting.webtau.http.request.HttpRequestBody;
import pe.msbaek.studyingtest.AbstractTestContainerTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testingisdocumenting.webtau.Matchers.equal;
import static org.testingisdocumenting.webtau.WebTauDsl.http;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class PostControllerTest extends AbstractTestContainerTest {
    @LocalServerPort
    int port;

    ObjectMapper objectMapper = new ObjectMapper();

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
        http.get(hostAndPort() + "/api/posts", (header, body) -> {
            header.statusCode.should(equal(200));
            System.out.println("body = " + body.get(1));
            assertThat(body.numberOfElements()).isEqualTo(100);
        });
    }

    @NotNull
    private String hostAndPort() {
        return "http://localhost:" + port;
    }

    @Test
    void shouldFindPostWhenValidPostID() {
        http.get(hostAndPort() + "/api/posts/1", (header, body) -> {
            header.statusCode.should(equal(200));
            body.get("id").should(equal(1));
        });
    }

    @Test
    void shouldThrowNotFoundWhenInvalidPostID() {
        http.get(hostAndPort() + "/api/posts/201", (header, body) -> {
            header.statusCode.should(equal(404));
        });
    }

    @Test
    void shouldCreateNewPostWhenPostIsValid() {
        HttpRequestBody post = http.json(
                "id", "101",
                "userId", "1",
                "body", "101 Body",
                "title", "101 Title"
        );

        http.post(hostAndPort() + "/api/posts", post, ((header, body) -> {
            header.statusCode.should(equal(HttpStatus.CREATED_201));
            body.get("id").should(equal(101));
            System.out.println(body.getTextContent());
        }));
    }

    @Test
    void shouldUpdatePostWhenPostIsValid() {
        Post post = http.get(hostAndPort() + "/api/posts/1", (header, body) -> {
            header.statusCode.should(equal(200));
            return jsonToObject(body.getTextContent(), Post.class);
        });

        Post updated = new Post(post.getId())
                .setUserId(post.getUserId())
                .setBody("NEW POST BODY #1")
                .setTitle("NEW POST TITLE #1");

        http.put(hostAndPort() + "/api/posts/1", http.json(toJsonString(updated)), (header, body) -> {
            header.statusCode.should(equal(200));
            body.get("id").should(equal(1));
            body.get("body").should(equal("NEW POST BODY #1"));
            body.get("title").should(equal("NEW POST TITLE #1"));
        });
    }

    private String toJsonString(Post updated) {
        try {
            return objectMapper.writeValueAsString(updated);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Post jsonToObject(String textContent, Class<Post> valueType) {
        try {
            return objectMapper.readValue(textContent, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}