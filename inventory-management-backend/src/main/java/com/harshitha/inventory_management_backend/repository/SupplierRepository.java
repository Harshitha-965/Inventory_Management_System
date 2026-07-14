package com.harshitha.inventory_management_backend.repository;

import com.harshitha.inventory_management_backend.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

}