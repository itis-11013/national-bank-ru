package ru.itis.nationalbankru.dto;

import lombok.Builder;

@Builder
public class OrganizationResponseDto {
    private Long id;
    private String name;
    private String email;
}
