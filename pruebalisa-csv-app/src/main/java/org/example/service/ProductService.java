package org.example.service;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Products")
public class ProductService {

    private final ReadFilesService readFilesService;
    private final MapperFacade mapper;

    @Autowired
    public ProductService(ReadFilesService readFilesService, @Qualifier("appOrika") MapperFacade mapper) {
        this.readFilesService = readFilesService;
        this.mapper = mapper;
    }

    public List<Long> findPrices() {
        return null; // TODO
    }
}
