package pe.msbaek.studyingtest.builder;

import lombok.Builder;

public class Person {
    private String name;
    private int age;
    private int sex;

    public static PersonBuilder builder(String name) {
        return new PersonBuilder().name(name);
    }

    @Builder
    public Person(String name, int age, int sex) {
        this.name = name;
        this.age = age;
        this.age = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}