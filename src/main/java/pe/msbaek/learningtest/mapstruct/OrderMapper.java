package pe.msbaek.learningtest.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

@Mapper
public interface OrderMapper {
    OrderDTO orderToOrderDTO(Order order);

    @ValueMappings({
            @ValueMapping(source = "PROCESSING", target = "PENDING"),
            @ValueMapping(source = "SHIPPED", target = "COMPLETED"),
            @ValueMapping(source = "DELIVERED", target = "CANCELLED")
    })
    OrderStatusDTO mapOrderStatusToOrderStatusDTO(OrderStatus orderStatus);
}