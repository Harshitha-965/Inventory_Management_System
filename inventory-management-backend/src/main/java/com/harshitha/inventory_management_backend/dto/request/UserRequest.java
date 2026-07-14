package com.harshitha.inventory_management_backend.dto.request;

import com.harshitha.inventory_management_backend.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotBlank(message="Name is required")
    private String name;

    @NotBlank(message="Email is required")
    @Email(message="Invalid email")
    private String email;

    @NotBlank(message="Password is required")
    @Size(min=6,message="Password must be at least 6 characters")
    private String password;

    @NotNull(message="Role is required")
    private Role role;

}