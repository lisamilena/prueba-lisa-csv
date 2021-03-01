package org.example.service;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("Prices")
public class ProductService {

    private final MapperFacade mapper;

    @Autowired
    public ProductService(@Qualifier("appOrika") MapperFacade mapper) {
        this.mapper = mapper;
    }
}
