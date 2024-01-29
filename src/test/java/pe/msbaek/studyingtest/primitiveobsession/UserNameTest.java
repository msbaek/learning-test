package pe.msbaek.studyingtest.primitiveobsession;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserNameTest {
    @Test
    void should_throw_invalid_argument_exception_when_user_name_contains_whitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            User user = createUser("msbaek ");
        });
    }

    private User createUser(String name) {
        return new User(name);
    }
}