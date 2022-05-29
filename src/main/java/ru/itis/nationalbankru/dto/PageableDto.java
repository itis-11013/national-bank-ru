package ru.itis.nationalbankru.dto;

import lombok.Data;

@Data
public class PageableDto {
    Integer pageNo = 0;
    Integer pageSize = 10;
    String sort = "update_at";
}
