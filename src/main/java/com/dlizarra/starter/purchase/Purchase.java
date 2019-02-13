package com.dlizarra.starter.purchase;

import com.dlizarra.starter.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(of = { "productName", "buyTime", "user" })
@ToString(of = { "id", "productName" , "user"})
@Setter
@Getter
@Entity
@Table(name = "purchases")
public class Purchase {

    static final int MAX_LENGTH_PRODUCTNAME = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private LocalDateTime buyTime;

    @Column(nullable = false)
    private float price;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;
}
