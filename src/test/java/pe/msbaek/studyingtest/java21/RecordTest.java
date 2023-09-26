package pe.msbaek.studyingtest.java21;

import org.junit.jupiter.api.Test;

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