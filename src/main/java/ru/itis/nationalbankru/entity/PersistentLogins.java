package ru.itis.nationalbankru.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "persistence_logins")
public class PersistentLogins {
    @Id
    @Column(name = "series", nullable = false)
    String series;

    @Column(name = "username", nullable = false)
    String username;

    @Column(name = "token", nullable = false)
    String token;

    @Column(name = "last_used", nullable = false)
    Timestamp lastUsed;
}
