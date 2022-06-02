package ru.itis.nationalbankru.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //    private Audit audit;

    @PrePersist
    public void onPrePersist() {
        Audit audit = new Audit();
    }

}
