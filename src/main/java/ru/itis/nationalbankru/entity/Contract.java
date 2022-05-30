package ru.itis.nationalbankru.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contracts")
public class Contract extends AbstractEntity {

    @Column(name = "inner_id", nullable = false)
    private UUID inner_id;

    @Builder.Default
    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid = false;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @Column(name = "product_inner_id")
    private UUID productInnerId;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Organization buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Organization seller;

    public double getContractAmount() {
        return this.getCount() * this.getUnitPrice();
    }
}
