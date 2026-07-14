package com.harshitha.inventory_management_backend.service;

import java.util.List;

import com.harshitha.inventory_management_backend.dto.request.PurchaseOrderRequest;
import com.harshitha.inventory_management_backend.dto.response.PurchaseOrderResponse;

public interface PurchaseOrderService {

    PurchaseOrderResponse createPurchaseOrder(PurchaseOrderRequest request);

    PurchaseOrderResponse getPurchaseOrderById(Long id);

    List<PurchaseOrderResponse> getAllPurchaseOrders();

    void deletePurchaseOrder(Long id);

}