package pe.msbaek.studyingtest.java21;

import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.util.concurrent.Executors;

import static java.lang.Character.isEmoji;
import static org.assertj.core.api.Assertions.assertThat;

public class VirtualThreadTest {
    @Test
    void futureTest() throws InterruptedException {
        try (var executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
            var future = executor.submit(() -> "Hello, World!");
            Thread.sleep(1_000);
            var result = switch (future.state()) {
                case CANCELLED, FAILED ->  throw new IllegalStateException("couldn't fishish the task");
                case SUCCESS -> future.resultNow();
                default -> throw new IllegalStateException("Unexpected value: " + future.state() + " from default");
            };

            System.out.println(result);
        }
    }

    @Test
    void http() throws Exception {
        try(var http = HttpClient.newHttpClient()) {
            var request = java.net.http.HttpRequest.newBuilder()
                    .uri(java.net.URI.create("https://www.google.com"))
                    .build();
            var response = http.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
            assertThat(response.statusCode()).isEqualTo(200);
            System.out.println(response.body());
        }
    }

    @Test
    void emoji() {
        var shockedFaceEmoji = "\uD83D\uDE31";
        var cp = Character.codePointAt(shockedFaceEmoji.toCharArray(), 0);
        assertThat(isEmoji(cp)).isTrue();
        System.out.println(shockedFaceEmoji);
    }

    @Test
    void repeatString() {
        var line = new StringBuilder()
                .repeat("-", 10)
                .toString();
        assertThat(line).isEqualTo("----------");
    }
}