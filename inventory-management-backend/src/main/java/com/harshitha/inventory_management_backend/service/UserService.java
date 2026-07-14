package com.harshitha.inventory_management_backend.service;

import java.util.List;

import com.harshitha.inventory_management_backend.dto.request.UserRequest;
import com.harshitha.inventory_management_backend.dto.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);

}