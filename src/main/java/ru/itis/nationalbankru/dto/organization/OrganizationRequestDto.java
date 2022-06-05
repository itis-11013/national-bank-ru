package ru.itis.nationalbankru.dto.organization;

import lombok.Data;
import ru.itis.nationalbankru.dto.validators.OnCreate;

import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;


@Data
public class OrganizationRequestDto {
    @NotEmpty(groups = {Default.class, OnCreate.class})
    private String name;
    @NotEmpty(groups = {Default.class, OnCreate.class})
    private String address;
    @NotEmpty(groups = {Default.class, OnCreate.class})
    private String password;
}
