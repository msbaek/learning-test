package pe.msbaek.learningtest;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "pe.msbaek.learningtest")
public class ConfigurationPropertiesTarget {
    String clientSecret = "default in source";
}
