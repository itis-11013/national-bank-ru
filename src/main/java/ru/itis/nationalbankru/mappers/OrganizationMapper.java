package ru.itis.nationalbankru.mappers;

import org.mapstruct.*;
import ru.itis.nationalbankru.dto.central.CentralOrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.entity.Organization;

import javax.persistence.MappedSuperclass;
import java.util.List;

//@Mapper(uses = AuditMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Mapper
@MappedSuperclass
public interface OrganizationMapper extends EntityMapper<Organization, OrganizationRequestDto, OrganizationResponseDto> {

    OrganizationResponseDto toDto(Organization organization);

    CentralOrganizationRequestDto toCentralDto(OrganizationRequestDto organizationRequestDto);

    List<OrganizationResponseDto> toDto(List<Organization> organization);

    Organization fromDto(OrganizationRequestDto organizationResponseDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "passwordHash", ignore = true)
    void updateFromDto(OrganizationRequestDto organizationRequestDto, @MappingTarget Organization organization);
}
