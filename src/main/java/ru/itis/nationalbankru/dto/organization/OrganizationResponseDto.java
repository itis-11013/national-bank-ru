package ru.itis.nationalbankru.dto.organization;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.itis.nationalbankru.dto.AbstractResponse;
import ru.itis.nationalbankru.entity.enums.Status;

@Builder
@Getter
@Setter
public class OrganizationResponseDto extends AbstractResponse {
    String name;
    String address;
    Status status;
}
