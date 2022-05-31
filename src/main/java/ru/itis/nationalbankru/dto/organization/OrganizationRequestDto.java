package ru.itis.nationalbankru.dto.organization;

import lombok.Data;


@Data
public class OrganizationRequestDto {
    private final String country = "ru";
    private String name;
    private String address;
}
