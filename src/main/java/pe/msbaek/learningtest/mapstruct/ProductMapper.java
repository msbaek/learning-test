package pe.msbaek.learningtest.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ProductMapper {
    @Mapping(source = "productName", target = "name")
    // @Mapping(target = "totalPrice", expression = "java(product.getPrice() * 1.08")
    @Mapping(target = "price", source = "price", defaultValue = "0.0") // mapping with null handling
    @Mapping(target = "manufactureDate", source = "manufactureDate", qualifiedByName = "stringToLocalDate")
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    List<ProductDto> toDtos(List<Product> products);

    default String formatPrice(double price) {
        return String.format("$%.2f", price);
    }

    // custom converter
    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date);
    }
}