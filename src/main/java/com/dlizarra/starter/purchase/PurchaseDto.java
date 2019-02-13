package com.dlizarra.starter.purchase;

import com.dlizarra.starter.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@EqualsAndHashCode(of = { "productName", "buyTime", "user" })
@ToString(of = { "id", "productName" , "user"})
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PurchaseDto {

    private Integer id;

    @Size(max = Purchase.MAX_LENGTH_PRODUCTNAME)
    private String productName;

    private LocalDateTime buyTime;

    private float price;

    private User user;
}
