package pe.msbaek.learningtest.testcontainers;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validate() {
        Post post = new Post(1);
        assertThat(
                validator.validate(post).size()
        ).isEqualTo(2);
        System.out.println("\n1");
        validator.validate(post).stream()
                .forEach(System.out::println);

        post = new Post(1)
                .setTitle("title");
        assertThat(
                validator.validate(post).size()
        ).isEqualTo(1);
        System.out.println("\n2");
        validator.validate(post).stream()
                        .forEach(System.out::println);

        assertThat(validator.validate(post).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("no violation"))
                .getMessage()).isEqualTo("body should not be empty");
    }

    @Test
    void name() {
        String [ ] arr = {"a", "b", "c"};
        assertThat(arr instanceof String[]).isTrue();
    }
}