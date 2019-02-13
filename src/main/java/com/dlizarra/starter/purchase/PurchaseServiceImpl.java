package com.dlizarra.starter.purchase;

import com.dlizarra.starter.support.orika.OrikaBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private OrikaBeanMapper mapper;

    @Override
    public PurchaseDto createPurchase(PurchaseDto purchaseDto) {
        final Purchase purchase = mapper.map(purchaseDto, Purchase.class);
        purchase.setBuyTime(LocalDateTime.now());
        return mapper.map(purchaseRepository.save(purchase),PurchaseDto.class);
    }

    @Override
    public PurchaseDto updatePurchase(PurchaseDto purchaseDto) {
        final Purchase purchase = mapper.map(purchaseDto, Purchase.class);
        return mapper.map(purchaseRepository.save(purchase),PurchaseDto.class);
    }

    @Override
    public void deletePurchase(Integer id) {
        purchaseRepository.delete(id);
    }

    @Override
    public PurchaseDto getPurchase(Integer id) {
        return mapper.map(find(id), PurchaseDto.class);
    }

    @Override
    public List<PurchaseDto> getPurchases() {
        List<Purchase> purchases = purchaseRepository.findAll();
        List<PurchaseDto> purchaseDtos = new ArrayList<>();
        purchases.forEach(x -> purchaseDtos.add(mapper.map(x, PurchaseDto.class)));
        return purchaseDtos;
    }

    private Purchase find(Integer id){
        final Optional<Purchase> userOpt = purchaseRepository.findOne(id);
        return userOpt.orElseThrow(() -> new NoSuchElementException("No Purchase with ID found: " + id));
    }
}
