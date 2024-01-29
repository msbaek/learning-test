package pe.msbaek.studyingtest.testcontainers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import pe.msbaek.studyingtest.AbstractTestContainerTest;

@Profile("test")
class PostLoaderTest extends AbstractTestContainerTest {
    @Autowired PostDataLoader loader;
    @Autowired PostRepository repository;

    @Test
    void loadFromJson() throws Exception {
        loader.run(null);
        repository.findAll().forEach(System.out::println);
    }
}