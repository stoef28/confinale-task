package com.dlizarra.starter.purchase;

import java.util.List;

public interface PurchaseService {

    PurchaseDto createPurchase(PurchaseDto purchaseDto);

    PurchaseDto updatePurchase(PurchaseDto purchaseDto);

    void deletePurchase(Integer id);

    PurchaseDto getPurchase(Integer id);

    List<PurchaseDto> getPurchases();
}
