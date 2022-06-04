package ru.itis.nationalbankru.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 09:29
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractResponse {
    private Long id;
    private Date createdAt;
    private LocalDateTime updatedAt;
    //    private AuditResponse audit;
}
