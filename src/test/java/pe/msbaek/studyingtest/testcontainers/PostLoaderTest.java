package pe.msbaek.studyingtest.testcontainers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pe.msbaek.studyingtest.AbstractTestContainerTest;

class PostLoaderTest extends AbstractTestContainerTest {
    @Autowired PostDataLoader loader;
    @Autowired PostRepository repository;

    @Test
    void loadFromJson() throws Exception {
        loader.run(null);
        repository.findAll().forEach(System.out::println);
    }
}