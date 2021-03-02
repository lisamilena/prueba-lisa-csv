package org.example.service;

import ma.glasnost.orika.MapperFacade;
import org.example.entity.SizeEntity;
import org.example.entity.StockEntity;
import org.example.model.ProductModel;
import org.example.model.SizeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        List<ProductModel> products = getModelData();
        return products.stream()
                .filter(product -> product.getSizes().stream().anyMatch(this::hasSize))
                .filter(product ->
                        product.getSizes().stream().noneMatch(SizeModel::getSpecial) ||
                            (product.getSizes().stream().anyMatch(size -> size.getSpecial() & hasSize(size)) &&
                            product.getSizes().stream().anyMatch(size -> !size.getSpecial() & hasSize(size)))
                )
                .sorted(Comparator.comparingLong(ProductModel::getSequence))
                .map(ProductModel::getId)
                .collect(Collectors.toList());
    }

    private List<ProductModel> getModelData() {
        List<SizeEntity> sizes = readFilesService.getSizesList();
        List<StockEntity> stockList = readFilesService.getStockList();
        return mapper.mapAsList(readFilesService.getProductsList(), ProductModel.class)
                .stream()
                .peek(product -> product.setSizes(mapper.mapAsList(sizes.stream()
                        .filter(size -> size.getProductId().equals(product.getId()))
                        .collect(Collectors.toList()), SizeModel.class)
                        .stream()
                        .peek(size -> size.setStock(stockList.stream()
                                .filter(stock -> stock.getSizeId().equals(size.getId()))
                                .map(StockEntity::getQuantity)
                                .findFirst().orElse(0)))
                        .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    private boolean hasSize(SizeModel size) {
        return size.getBackSoon() || size.getStock() > 0;
    }
}