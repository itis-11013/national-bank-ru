package ru.itis.nationalbankru.dto.organization;

import lombok.Getter;
import lombok.Setter;
import ru.itis.nationalbankru.dto.AbstractResponse;
import ru.itis.nationalbankru.entity.enums.Status;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class OrganizationResponseDto extends AbstractResponse {
    String name;
    String address;
    Status status;
    Double balance;
    Double frozenBalance;
}
