package com.dlizarra.starter.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping(value = "/purchases")
    public List<PurchaseDto> getAllPurchases(){
        return purchaseService.getPurchases();
    }

    @PostMapping(value = "/purchases")
    public PurchaseDto addPurchase(@RequestBody PurchaseDto purchase){
        return purchaseService.createPurchase(purchase);
    }

    @PutMapping(value = "/purchases")
    public PurchaseDto updatePurchase(@RequestBody PurchaseDto purchase){
        return purchaseService.updatePurchase(purchase);
    }

    @GetMapping(value = "/purchases/{id}")
    public PurchaseDto getPurchase(@PathVariable int id){
        return purchaseService.getPurchase(id);
    }

    @DeleteMapping(value = "/purchases/{id}")
    public void deletePurchase(@PathVariable int id){
        purchaseService.deletePurchase(id);
    }


}
