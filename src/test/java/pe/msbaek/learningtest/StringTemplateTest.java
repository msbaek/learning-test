package pe.msbaek.learningtest;

import org.junit.jupiter.api.Test;

import static java.lang.StringTemplate.STR;

public class StringTemplateTest {
    @Test
    void string_template_test() {
        String name = "msbaek";
        int age = 30;
        String result = STR."My name is ${name} and I'm ${age} years old.";
        System.out.println(result);
    }
}
