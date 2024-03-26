package pe.msbaek.learningtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConfigurationPropertiesTargetTest {
    @Autowired ConfigurationPropertiesTarget target;

    @Test
    void test_configuration() {
        assertThat(target.getClientSecret()).isEqualTo("properteis in yaml");
    }
}