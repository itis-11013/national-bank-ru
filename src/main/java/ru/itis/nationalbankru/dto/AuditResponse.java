package ru.itis.nationalbankru.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : Escalopa
 * @created : 04.06.2022, Sat
 * @time : 20:23
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditResponse {
    private Date createdAt;
    private LocalDateTime updateAt;
    private String createdBy;
    private String updatedBy;
}
