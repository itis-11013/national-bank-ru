package ru.itis.nationalbankru.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

    @Column(name = "name", unique = true, nullable = false)
    String name;
}
