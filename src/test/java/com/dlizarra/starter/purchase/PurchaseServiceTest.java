package com.dlizarra.starter.purchase;

import com.dlizarra.starter.support.AbstractUnitTest;
import com.dlizarra.starter.support.orika.OrikaBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@Transactional
public class PurchaseServiceTest extends AbstractUnitTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @Autowired
    @Spy
    private OrikaBeanMapper mapper;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        final Purchase p1 = new Purchase();
        p1.setId(1);
        p1.setProductName("Milk");
        p1.setPrice(1.0f);
        final Purchase p2 = new Purchase();
        p1.setId(2);
        p1.setProductName("Chocolate");
        p1.setPrice(1.5f);
        final List<Purchase> purchases = new ArrayList<>();
        purchases.add(p1);
        purchases.add(p2);
        when(purchaseRepository.findAll()).thenReturn(purchases);
        when(purchaseRepository.findOne(1)).thenReturn(Optional.of(p1));
        when(purchaseRepository.findOne(100)).thenReturn(Optional.empty());
    }

    @Test
    public void testGetPurchaseValid(){
        final PurchaseDto purchaseDto = purchaseService.getPurchase(1);
        assertThat(purchaseDto.getProductName()).isEqualTo("Chocolate");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetPurchaseInvalid(){
        final PurchaseDto purchaseDto = purchaseService.getPurchase(100);
    }

    @Test
    public void testShouldReturnTwoElements(){
        final List<PurchaseDto> purchases = purchaseService.getPurchases();
        assertThat(purchases.size()).isEqualTo(2);
    }



}
