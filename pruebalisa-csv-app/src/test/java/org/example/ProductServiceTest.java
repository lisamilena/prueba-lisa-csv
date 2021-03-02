package org.example;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.example.entity.ProductEntity;
import org.example.entity.SizeEntity;
import org.example.entity.StockEntity;
import org.example.exception.ExceptionType;
import org.example.exception.ServiceException;
import org.example.service.ProductService;
import org.example.service.ReadFilesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ReadFilesService readFilesService;

    @Autowired
    private ProductService productService;

    @Before
    public void setUp() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        productService = new ProductService(readFilesService, mapper);
    }

    @Test
    public void shouldReturnDataInService() {
        List<ProductEntity> products = mockProducts();
        Mockito.when(readFilesService.getStockList()).thenReturn(mockStock());
        Mockito.when(readFilesService.getSizesList()).thenReturn(mockSizes());
        Mockito.when(readFilesService.getProductsList()).thenReturn(products);

        List<Long> response = productService.findPrices();
        Assert.assertEquals(response.get(0), products.get(1).getId());
        Assert.assertEquals(response.get(1), products.get(0).getId());
    }

    @Test
    public void shouldReturnServiceExceptionOnGetStock() {
        Mockito.when(readFilesService.getStockList()).thenThrow(new ServiceException(ExceptionType.ERROR_FILE));
        try {
            productService.findPrices();
        } catch (ServiceException e) {
            Assert.assertEquals(ExceptionType.ERROR_FILE, e.getType());
        }
    }

    @Test
    public void shouldReturnServiceExceptionOnGetSizes() {
        Mockito.when(readFilesService.getSizesList()).thenThrow(new ServiceException(ExceptionType.ERROR_FILE));
        try {
            productService.findPrices();
        } catch (ServiceException e) {
            Assert.assertEquals(ExceptionType.ERROR_FILE, e.getType());
        }
    }

    @Test
    public void shouldReturnServiceExceptionOnGetProducts() {
        Mockito.when(readFilesService.getProductsList()).thenThrow(new ServiceException(ExceptionType.ERROR_FILE));
        try {
            productService.findPrices();
        } catch (ServiceException e) {
            Assert.assertEquals(ExceptionType.ERROR_FILE, e.getType());
        }
    }

    private List<StockEntity> mockStock() {
        return Arrays.asList(new StockEntity(11L, 0),
                new StockEntity(12L, 10),
                new StockEntity(21L, 0),
                new StockEntity(22L, 0),
                new StockEntity(23L, 10),
                new StockEntity(31L, 0),
                new StockEntity(33L, 0));
    }

    private List<SizeEntity> mockSizes() {
        return Arrays.asList(new SizeEntity(11L, 1L, true, false),
                new SizeEntity(12L, 1L, false, false),
                new SizeEntity(13L, 1L, false, false),
                new SizeEntity(21L, 2L, true, true),
                new SizeEntity(22L, 2L, false, false),
                new SizeEntity(23L, 2L, true, false),
                new SizeEntity(31L, 3L, false, false),
                new SizeEntity(32L, 3L, false, false),
                new SizeEntity(33L, 3L, false, false));
    }

    private List<ProductEntity> mockProducts() {
        return Arrays.asList(new ProductEntity(1L, 10L),
                new ProductEntity(2L, 4L),
                new ProductEntity(3L, 8L));
    }
}