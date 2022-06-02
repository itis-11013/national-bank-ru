package ru.itis.nationalbankru.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 06:47
 **/

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "products")
public class Product extends AbstractEntity {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "inner_id", nullable = false)
    @JsonProperty(value = "productid")
    private UUID innerId;

    @Column(name = "count", nullable = false)
    private double count;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Organization seller;

}
