package ru.itis.nationalbankru.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Organization extends DateAudit{

    @Id
    @Column(name = "id", updatable = false, insertable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "inner_id", nullable = false)
    private UUID inner_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Builder.Default
    private static final String country = "RU";

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
