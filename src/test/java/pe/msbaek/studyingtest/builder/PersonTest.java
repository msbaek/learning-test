package pe.msbaek.studyingtest.builder;

import org.junit.jupiter.api.Test;

class PersonTest {
    @Test
    void name() {
        Person person = Person.builder("name for test")
                .sex(1)
                .age(20)
                .age(20)
                .name("modified name")
                .build();

        System.out.println(person);
    }
}