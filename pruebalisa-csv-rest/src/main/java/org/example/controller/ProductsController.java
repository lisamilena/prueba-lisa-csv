package org.example.controller;

import org.example.exception.ServiceException;
import org.example.exceptions.ApiException;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductsController {

    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public String findPrices() {
        try {
            return productService.findPrices().stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
        } catch (ServiceException e) {
            throw new ApiException(e.getType());
        } catch (Exception e) {
            throw new ApiException("Error", e);
        }
    }
}