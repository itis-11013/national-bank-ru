package ru.itis.nationalbankru.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "code")
    private Long code;

    @Column(name = "name")
    private String name;
}
