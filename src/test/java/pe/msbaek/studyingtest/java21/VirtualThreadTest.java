package pe.msbaek.studyingtest.java21;

import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

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

    @Test
    void loom() throws Exception {
        ConcurrentSkipListSet<String> observed = new ConcurrentSkipListSet<>();
        List<Thread> threads = IntStream
                .range(0, 100)
                .mapToObj(index -> Thread.ofVirtual() // .ofPlatform()
                        .unstarted(() -> {
                            boolean isFirst = index == 0;
                            if(isFirst) {
                                observed.add(Thread.currentThread().toString());
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(isFirst) {
                                observed.add(Thread.currentThread().toString());
                            }
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(isFirst) {
                                observed.add(Thread.currentThread().toString());
                            }
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(isFirst) {
                                observed.add(Thread.currentThread().toString());
                            }
                        }))
                .toList();
        for(Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        observed.forEach(System.out::println);

        assertThat(observed.size()).isGreaterThan(1);
    }
}