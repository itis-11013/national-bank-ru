package ru.itis.nationalbankru.entity;

import lombok.*;
import ru.itis.nationalbankru.entity.enums.Status;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "organizations")
public class Organization extends AbstractEntity {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "organizations_roles")
    List<Role> roles;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "inner_id", unique = true, nullable = false)
    private UUID innerId;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "frozen_balance", nullable = false)
    @Builder.Default
    private Double frozenBalance = 0.0;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
    private List<Contract> buyContract;

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private List<Product> products;

}
