package org.example;

import org.example.controller.ProductsController;
import org.example.exception.ExceptionType;
import org.example.exception.ServiceException;
import org.example.exceptions.ApiException;
import org.example.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductsControllerTest {

    @Mock
    ProductService productService;

    @Autowired
    ProductsController productsController;

    @Before
    public void setUp() {
        productsController = new ProductsController(productService);
    }

    @Test
    public void shouldReturnProductsResponse() {
        List<Long> data = mockedData();
        Mockito.when(productService.findPrices()).thenReturn(data);
        String response = productsController.findPrices();
        Assert.assertEquals(response.split(", ")[0], data.get(0).toString());
        Assert.assertEquals(response.split(", ")[1], data.get(1).toString());
    }

    @Test
    public void shouldReturnException() {
        Mockito.when(productService.findPrices()).thenThrow(new ServiceException(ExceptionType.ERROR_FILE));
        try {
            productsController.findPrices();
        } catch (ApiException e) {
            Assert.assertEquals(ExceptionType.ERROR_FILE.getCode(), e.getCode());
        }
    }

    private List<Long> mockedData() {
        return Arrays.asList(1L, 2L, 3L);
    }
}
