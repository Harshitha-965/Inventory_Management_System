package com.harshitha.inventory_management_backend.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.harshitha.inventory_management_backend.enums.OrderStatus;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrderRequest {

    @NotNull(message = "Order date is required")
    private LocalDate orderDate;

    @NotNull(message = "Order status is required")
    private OrderStatus status;

    @NotNull(message = "Supplier is required")
    private Long supplierId;

    @Valid
    @NotEmpty(message = "Order must contain at least one item")
    private List<PurchaseOrderItemRequest> items;

}