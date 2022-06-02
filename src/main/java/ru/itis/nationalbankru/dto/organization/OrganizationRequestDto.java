package ru.itis.nationalbankru.dto.organization;

import lombok.Data;


@Data
public class OrganizationRequestDto {
    private String name;
    private String address;
    private String password;
}
