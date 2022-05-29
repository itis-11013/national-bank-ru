package ru.itis.nationalbankru.dto.organization;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrganizationRequestDto {
    String name;
    String address;
}
