package ru.itis.nationalbankru.dto.organization;

import lombok.Builder;
import lombok.Data;
import ru.itis.nationalbankru.entity.enums.Status;

import java.util.UUID;

@Builder
@Data
public class OrganizationResponseDto {
    UUID id;
    String name;
    String address;
    Status status;
}
