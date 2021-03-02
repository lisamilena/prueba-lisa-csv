package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeModel {

    private Long id;
    private Boolean backSoon;
    private Boolean special;
    private Integer stock;
}