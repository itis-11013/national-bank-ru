package ru.itis.nationalbankru.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.entity.Organization;

import java.util.List;

@Mapper
public interface OrganizationMapper extends EntityMapper<Organization, OrganizationRequestDto, OrganizationResponseDto> {

    OrganizationResponseDto toDto(Organization organization);

    List<OrganizationResponseDto> toDto(List<Organization> organization);

    Organization fromDto(OrganizationRequestDto organizationResponseDto);

    void updateFromDto(OrganizationRequestDto organizationRequestDto, @MappingTarget Organization organization);
}
