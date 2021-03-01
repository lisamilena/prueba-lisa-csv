package org.example.controller;

import ma.glasnost.orika.MapperFacade;
import org.example.exception.ServiceException;
import org.example.exceptions.ApiException;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductsController {

    private final ProductService productService;
    private final MapperFacade mapper;

    @Autowired
    public ProductsController(ProductService productService, @Qualifier("restOrika") MapperFacade mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping("prices")
    public List<Long> findPrices() {
        try {
            return productService.findPrices();
        } catch (ServiceException e) {
            throw new ApiException(e.getType());
        } catch (Exception e) {
            throw new ApiException("Error", e);
        }
    }
}