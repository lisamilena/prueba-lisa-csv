package org.example.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.exception.ExceptionType;
import org.example.exception.ServiceException;
import org.example.model.StockModel;
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

    @Value("${file.product}")
    private List<Resource> resources;

    @Autowired
    public ReadFilesService() {}

    private List<StockModel> getStock() {
        return readFile(resources.get(0)).stream()
                .map(item -> new StockModel(ConverterUtil.stringToLong(item[0]),
                        ConverterUtil.stringToInteger(item[1])))
                .collect(Collectors.toList());
    }

    private List<String[]> readFile(Resource resource) {
        try {
            return new CSVReader(new FileReader(resource.getFile())).readAll();
        } catch (IOException e) {
            throw new ServiceException(ExceptionType.ERROR_FILE, " (IOException):" + e.getMessage());
        } catch (CsvException e) {
            throw new ServiceException(ExceptionType.ERROR_FILE, " (CsvException):" + e.getMessage());
        }
    }
}