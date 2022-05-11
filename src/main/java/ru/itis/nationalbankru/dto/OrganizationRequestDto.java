package ru.itis.nationalbankru.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.nationalbankru.entity.Organization;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationRequestDto {
    private String name;
    private String email;
    private String password;
}
