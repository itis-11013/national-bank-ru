package ru.itis.nationalbankru.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product extends AbstractEntity {

    @Column(name = "inner_id")
    private UUID innerId;

    @Column(name = "name")
    private String name;

    @Min(1)
    @Column(name = "price")
    private double price;

    @Min(1)
    @Column(name = "count")
    private double count;

    @Builder.Default
    private double frozenCount = 0;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Organization seller;

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private ProductCatalog catalog;

    @OneToMany(mappedBy = "product")
    private List<Contract> contracts;
    
}
