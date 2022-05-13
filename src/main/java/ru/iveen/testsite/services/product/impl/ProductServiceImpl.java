package ru.iveen.testsite.services.product.impl;

import ru.iveen.testsite.domain.entity.product.Product;
import ru.iveen.testsite.domain.mapper.ProductMapper;
import ru.iveen.testsite.domain.repository.ProductRepository;
import ru.iveen.testsite.dto.product.ProductDto;
import ru.iveen.testsite.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Polyakov Anton
 * @created 11.05.2022 1:44
 * @project testSite
 */

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto create(ProductDto productDto) {
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productDto)));
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + id + " not found."));
        productMapper.update(productDto, product);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + id + " not found."));
    }

    @Override
    public ProductDto findByIdDto(Long id) {
        return productMapper.toDto(this.findById(id));
    }

    @Override
    public Page<ProductDto> findAll(int page, int size) {
        Page<Product> entityPage = productRepository.findAll(PageRequest.of(page, size));

        return new PageImpl<>(
                productMapper.toDto(entityPage.toList()),
                PageRequest.of(page, size),
                entityPage.getTotalElements()
        );
    }
}
