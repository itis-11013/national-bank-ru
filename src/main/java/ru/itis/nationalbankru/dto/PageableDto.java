package ru.itis.nationalbankru.dto;

import lombok.Data;

@Data
public class PageableDto {
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private String sort = "id";
}
