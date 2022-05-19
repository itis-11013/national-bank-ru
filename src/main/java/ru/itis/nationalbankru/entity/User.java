package ru.itis.nationalbankru.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class User extends DateAudit {

    @Id
    @Column(name = "id", updatable = false, insertable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "status", unique = true, nullable = false)
    private Status status;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    public enum Status {
        BANNED,
        ACTIVE
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    List<Role> roles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    List<Organization> organizations = new ArrayList<>();
}
