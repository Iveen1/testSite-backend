package ru.iveen.testsite.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.iveen.testsite.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iveen.testsite.dto.product.ProductDto;

import java.util.Collections;

/**
 * @author Polyakov Anton
 * @created 11.05.2022 15:53
 * @project testSite
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public Page<ProductDto> getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return productService.findAll(page, size);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) {
        productService.create(productDto);
        return ResponseEntity.ok(Collections.singletonMap("response", "success create product " + productDto.getName()));
    }

    @PostMapping("/remove/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok("success remove product " + id);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.findByIdDto(id);
    }
}
