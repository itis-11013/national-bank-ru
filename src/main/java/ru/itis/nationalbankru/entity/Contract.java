package ru.itis.nationalbankru.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contracts")
public class Contract extends AbstractEntity {

    @Column(name = "count", nullable = false)
    private Integer count;

    @Builder.Default
    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid = false;

    @Column(name = "payment_date", nullable = false, updatable = false)
    private Date paymentDate;

    @Column(name = "delivery_date", nullable = false, updatable = false)
    private Date deliveryDate;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Organization buyer;


    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Organization seller;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
