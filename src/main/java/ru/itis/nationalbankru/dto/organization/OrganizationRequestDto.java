package ru.itis.nationalbankru.dto.organization;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizationRequestDto {
    String name;
    String address;
}
