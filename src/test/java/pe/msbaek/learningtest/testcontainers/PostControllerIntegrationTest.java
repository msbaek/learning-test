package pe.msbaek.learningtest.testcontainers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import static io.restassured.RestAssured.given;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerIntegrationTest {
    @LocalServerPort
    private int port;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void test() throws JsonProcessingException {
        Post request = new Post()
                .setId(100)
                .setUserId(1)
                .setTitle("title")
                .setBody("body");

        ExtractableResponse<Response> extract = given()
                .contentType("application/json")
                .body(mapper.writeValueAsString(request))
                .when()
                .post("/api/posts/")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract();//                        .as(Post.class)
        System.out.println(extract.response().prettyPrint());
    }
}