package org.example.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.entity.ProductEntity;
import org.example.entity.SizeEntity;
import org.example.entity.StockEntity;
import org.example.exception.ExceptionType;
import org.example.exception.ServiceException;
import org.example.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service("Files")
public class ReadFilesService {

    @Value("${csv-files.products}")
    private Resource products;
    @Value("${csv-files.sizes}")
    private Resource sizes;
    @Value("${csv-files.stock}")
    private Resource stock;

    @Autowired
    public ReadFilesService() {}

    public List<StockEntity> getStockList() {
        return readFile(stock).stream()
                .map(item -> new StockEntity(ConverterUtil.stringToLong(item[0]),
                        ConverterUtil.stringToInteger(item[1])))
                .collect(Collectors.toList());
    }

    public List<SizeEntity> getSizesList() {
        return readFile(sizes).stream()
                .map(item -> new SizeEntity(ConverterUtil.stringToLong(item[0]),
                        ConverterUtil.stringToLong(item[1]),
                        ConverterUtil.stringToBool(item[2]),
                        ConverterUtil.stringToBool(item[3])))
                .collect(Collectors.toList());
    }

    public List<ProductEntity> getProductsList() {
        return readFile(products).stream()
                .map(item -> new ProductEntity(ConverterUtil.stringToLong(item[0]),
                        ConverterUtil.stringToLong(item[1])))
                .collect(Collectors.toList());
    }

    private List<String[]> readFile(Resource resource) {
        try {
            return new CSVReader(new FileReader(resource.getFile())).readAll();
        } catch (IOException | CsvException e) {
            throw new ServiceException(ExceptionType.ERROR_FILE, " (" + resource.getFilename() + "):" + e.getMessage());
        }
    }
}