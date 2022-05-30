package ru.itis.nationalbankru.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "product_catalog")
@Getter
@Setter
public class ProductCatalog {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private String name;

    @OneToMany(mappedBy = "catalog")
    private List<Product> products;
}
