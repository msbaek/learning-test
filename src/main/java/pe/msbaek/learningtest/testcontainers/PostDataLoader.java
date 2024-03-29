package pe.msbaek.learningtest.testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Component
@Profile("post")
public class PostDataLoader implements CommandLineRunner {
    private final ObjectMapper objectMapper;
    private final PostRepository repository;

    @Override
    public void run(String... args) throws Exception {
        try (InputStream inputStream = this.getClass().getResourceAsStream("/data/posts.json")) {
            Posts response = objectMapper.readValue(inputStream, Posts.class);
            repository.saveAll(response.posts());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        repository.findAll().forEach(System.out::println);
    }
}