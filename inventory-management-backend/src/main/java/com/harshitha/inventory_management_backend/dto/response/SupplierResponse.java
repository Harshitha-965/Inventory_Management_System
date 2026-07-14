package com.harshitha.inventory_management_backend.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponse {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String address;
}