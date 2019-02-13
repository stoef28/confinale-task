package com.dlizarra.starter.purchase;

import com.dlizarra.starter.support.jpa.CustomJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends CustomJpaRepository<Purchase, Integer> {

}
