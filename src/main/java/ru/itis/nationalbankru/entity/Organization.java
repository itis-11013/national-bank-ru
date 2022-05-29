package ru.itis.nationalbankru.entity;

import lombok.*;
import ru.itis.nationalbankru.entity.enums.Status;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "organizations")
public class Organization extends AbstractEntity {

    @Column(name = "inner_id", nullable = false)
    private UUID inner_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Builder.Default
    private static final String country = "RU";

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
