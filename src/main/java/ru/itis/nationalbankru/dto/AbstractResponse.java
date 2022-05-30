package ru.itis.nationalbankru.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 09:29
 **/

@Builder
@Data
public abstract class AbstractResponse {
    private UUID id;
    private Date createdAt;
    private LocalDateTime updatedAt;
}
