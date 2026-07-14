package com.harshitha.inventory_management_backend.dto.response;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private Long categoryId;

    private String categoryName;

    private Long supplierId;

    private String supplierName;
}