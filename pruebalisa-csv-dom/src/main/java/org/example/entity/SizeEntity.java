package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeEntity {

    private Long id;
    private Long productId;
    private Boolean backSoon;
    private Boolean special;
}