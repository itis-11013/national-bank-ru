package ru.itis.nationalbankru.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 14:54
 **/

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audits")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreatedBy
    @OneToOne
    @JoinColumn(name = "created_by")
    private Organization createdBy;

    @LastModifiedBy
    @OneToOne
    @JoinColumn(name = "updated_by")
    private Organization updatedBy;
}
