package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SizeModel {

    private Long id;
    private Long productId;
    private Boolean backSoon;
    private Boolean special;
}