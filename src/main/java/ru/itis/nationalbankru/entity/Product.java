package ru.itis.nationalbankru.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product extends AbstractEntity {

    UUID inner_id;
}
