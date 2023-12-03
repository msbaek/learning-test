package pe.msbaek.studyingtest.testcontainers;

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

        post = new Post(1)
                .setTitle("title");
        assertThat(
                validator.validate(post).size()
        ).isEqualTo(1);

        assertThat(validator.validate(post).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("no violation"))
                .getMessage()).isEqualTo("body should not be empty");
    }
}