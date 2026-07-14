package com.harshitha.inventory_management_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshitha.inventory_management_backend.entity.PurchaseOrder;


public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}