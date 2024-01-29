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

    @Test
    void should_not_throw_exception_when_user_name_contains_no_whitespace() {
        createUser("msbaek");
    }

    private User createUser(String name) {
        if(!isValid(name))
            throw new IllegalArgumentException("Invalid name: [%s]".formatted(name));
        return new User(name);
    }

    private boolean isValid(String name) {
        if(name == null || name.isBlank())
            return false;
        return !name.contains(" ");
    }
}