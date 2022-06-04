package ru.itis.nationalbankru.dto.organization;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class OrganizationRequestDto {
    private final String country = "ru";
    @NotNull
    private String name;
    @NotNull
    private String address;
    private String password;
}
