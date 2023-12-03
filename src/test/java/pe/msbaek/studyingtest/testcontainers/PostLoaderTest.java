package pe.msbaek.studyingtest.testcontainers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostLoaderTest {
    @Container
    @ServiceConnection
    static MySQLContainer mysql = new MySQLContainer("mysql:latest");

    @Autowired
    JdbcConnectionDetails jdbcConnectionDetails;

    @Autowired PostDataLoader loader;
    @Autowired PostRepository repository;

    @Test
    void loadFromJson() {
        loader.run();
        repository.findAll().forEach(System.out::println);
    }
}