package pe.msbaek.studyingtest.java21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SealedTest {
    sealed class Animal { // permits Cat, Bird, Dog {
    }

    final class Cat extends Animal {
        public String meow() {
            return "Meow";
        }
    }

    final class Bird extends Animal {
        public String tweet() {
            return "Tweet";
        }
    }

    sealed class Dog extends Animal {
        public String bark() {
            return "Woof";
        }
    }

    final class Chihuahua extends Dog {
        public String yip() {
            return "Yip";
        }
    }

    String communicate(Animal animal) {
        return switch (animal) {
            case Cat cat -> cat.meow(); // casting alias
            case Bird bird -> bird.tweet();
            case Chihuahua chihuahua -> chihuahua.yip();
            case Dog dog -> dog.bark();
            default -> throw new IllegalStateException("Unexpected value: " + animal);
        };
    }

    @Test
    void sealed_record() {
        System.out.println(communicate(new Cat()));
        System.out.println(communicate(new Bird()));
        System.out.println(communicate(new Chihuahua()));
        System.out.println(communicate(new Dog()));
    }
}

class RecordTest {
    record User(String name, long accountNumber) {
    }

    record UserDeletedEvent(User user) {
    }

    record UserCreatedEvent(String name) {
    }

    String respond(Object o) {
        return switch (o) {
            case UserCreatedEvent e -> "User created: " + e.name();
            case UserDeletedEvent e -> "User deleted: " + e.user().name();
            default -> throw new IllegalArgumentException("Unexpected value: " + o);
        };
    }

    @Test
    void assertResponse() {
        assertThat(respond(new UserCreatedEvent("msbaek"))).isEqualTo("User created: msbaek");
        assertThat(respond(new UserDeletedEvent(new User("msbaek", 1l)))).isEqualTo("User deleted: msbaek");
    }
    String enhancedRespond(Object o) {
        return switch (o) {
            case UserCreatedEvent(var name) -> "User created: " + name;
            case UserDeletedEvent(var user) -> "User deleted: " + user.name();
            default -> throw new IllegalArgumentException("Unexpected value: " + o);
        };
    }

    @Test
    void assertEnhancedResponse() {
        assertThat(enhancedRespond(new UserCreatedEvent("msbaek"))).isEqualTo("User created: msbaek");
        assertThat(enhancedRespond(new UserDeletedEvent(new User("msbaek", 1l)))).isEqualTo("User deleted: msbaek");
    }
}