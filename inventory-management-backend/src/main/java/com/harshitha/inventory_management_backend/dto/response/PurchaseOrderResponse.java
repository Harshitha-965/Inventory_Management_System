package com.harshitha.inventory_management_backend.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.harshitha.inventory_management_backend.enums.OrderStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrderResponse {

    private Long id;

    private LocalDate orderDate;

    private OrderStatus status;

    private Long supplierId;

    private String supplierName;

    private List<PurchaseOrderItemResponse> items;

}