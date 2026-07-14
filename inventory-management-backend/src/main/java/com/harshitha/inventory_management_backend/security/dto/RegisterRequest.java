package com.harshitha.inventory_management_backend.security.dto;

import com.harshitha.inventory_management_backend.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    private String name;

    private String email;

    private String password;

    private Role role;

}