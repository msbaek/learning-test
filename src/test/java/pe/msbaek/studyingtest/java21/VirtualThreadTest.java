package pe.msbaek.studyingtest.java21;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

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
}
