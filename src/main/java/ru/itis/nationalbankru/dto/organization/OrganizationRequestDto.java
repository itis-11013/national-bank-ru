package ru.itis.nationalbankru.dto.organization;

import lombok.Data;
import ru.itis.nationalbankru.dto.validators.OnCreate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;


@Data
public class OrganizationRequestDto {
    @NotNull(groups = {Default.class, OnCreate.class})
    private String name;
    @NotNull(groups = {Default.class, OnCreate.class})
    private String address;
    @NotEmpty(groups = {Default.class, OnCreate.class})
    private String password;
}
