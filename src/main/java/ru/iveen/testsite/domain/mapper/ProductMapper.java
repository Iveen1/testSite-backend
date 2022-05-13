package ru.iveen.testsite.domain.mapper;

import ru.iveen.testsite.domain.entity.product.Product;
import ru.iveen.testsite.dto.product.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // to dto
    @Mapping(target = "userFavorites", ignore = true)
    ProductDto toDto(Product entity);
    List<ProductDto> toDto(List<Product> entities);

    // to Entity
    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductDto dto);
    List<Product> toEntities(List<ProductDto> dtos);

    @Mapping(target = "id", ignore = true)
    void update(ProductDto dto, @MappingTarget Product product);
}
