package ru.itis.nationalbankru.entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private UUID innerId;

    @Builder.Default
    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid = false;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "count", nullable = false)
    private Double count;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Organization buyer;

    public double getContractAmount() {
        return this.getCount() * this.product.getPrice();
    }
}
