package pe.msbaek.learningtest.mapstruct;

import lombok.Data;

enum OrderStatus {
    PROCESSING,
    SHIPPED,
    DELIVERED
}

@Data
public class Order {
    private OrderStatus status;
    // Getters and setters
}

enum OrderStatusDTO {
    PENDING,
    COMPLETED,
    CANCELLED
}

@Data
class OrderDTO {
    private OrderStatusDTO status;
    // Getters and setters
}