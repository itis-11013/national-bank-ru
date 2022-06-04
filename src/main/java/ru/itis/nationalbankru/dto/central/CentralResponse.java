package ru.itis.nationalbankru.dto.central;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 31.05.2022, Tue
 * @time : 08:15
 **/

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CentralResponse<T> {
    @JsonProperty("status")
    private String status;
    @JsonProperty("description")
    private String description;
    @JsonProperty("data")
    private T data;
    @JsonProperty("innerid")
    private UUID innerId;
}
