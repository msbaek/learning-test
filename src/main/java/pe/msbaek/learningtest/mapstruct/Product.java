package pe.msbaek.learningtest.mapstruct;

import lombok.Data;

import java.time.LocalDate;

/**
 * [MapStruct - A Comprehensive Guide in Spring Boot Context | by Susanta Mondal | Medium](https://medium.com/@susantamon/mapstruct-a-comprehensive-guide-in-spring-boot-context-1e7202da033e)
 */
@Data
public class Product {
    private Long id;
    private String productName;
    private double price;
    private String manufactureDate;
    // Getters and setters
}

@Data
class ProductDto {
    private String name;
    private double totalPrice;
    private String price;
    private LocalDate manufactureDate;
    // Getters and setters
}