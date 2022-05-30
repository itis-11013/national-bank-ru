package ru.itis.nationalbankru.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PageableDto {
    @Builder.Default
    Integer pageNo = 0;
    @Builder.Default
    Integer pageSize = 10;
    @Builder.Default
    String sort = "id";
}
