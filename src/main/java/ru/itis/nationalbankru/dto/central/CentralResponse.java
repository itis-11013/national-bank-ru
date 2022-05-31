package ru.itis.nationalbankru.dto.central;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 31.05.2022, Tue
 * @time : 08:15
 **/

@Builder
@Data
public class CentralResponse<T> {
    private String status;
    private String description;
    private T data;
    private UUID innerId;
}
