package ru.itis.nationalbankru.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product extends AbstractEntity {

    @Column(name = "inner_id", nullable = false)
    UUID inner_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "frozen_count", nullable = false)
    @Builder.Default
    private Integer frozenCount = 0;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private ProductCatalog catalog;

    @ManyToOne()
    @JoinColumn(name = "seller_id", nullable = false)
    private Organization seller;

    @OneToMany(mappedBy = "product")
    private List<Contract> contracts;

}
