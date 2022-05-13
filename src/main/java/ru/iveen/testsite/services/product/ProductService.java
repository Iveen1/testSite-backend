package ru.iveen.testsite.services.product;

import ru.iveen.testsite.domain.entity.product.Product;
import ru.iveen.testsite.dto.product.ProductDto;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductDto create(ProductDto productDto);

    ProductDto update(Long id, ProductDto productDto);

    Product findById(Long id);

    ProductDto findByIdDto(Long id);

    void delete(Long id);

    Page<ProductDto> findAll(int page, int size);
}
