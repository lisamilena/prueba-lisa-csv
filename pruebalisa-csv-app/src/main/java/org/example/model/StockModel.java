package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockModel {

    private Long sizeId;
    private Integer quantity;
}