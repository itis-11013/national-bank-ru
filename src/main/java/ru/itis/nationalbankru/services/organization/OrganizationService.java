package ru.itis.nationalbankru.services.organization;

import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.exceptions.OrganizationNotFoundException;

import java.util.List;
import java.util.UUID;

public interface OrganizationService {

    List<OrganizationResponseDto> getAllUserOrganization(Integer pageNo, Integer pageSize, String sortBy);

    List<OrganizationResponseDto> getAllOrganization(Integer pageNo, Integer pageSize, String sortBy);

    OrganizationResponseDto createOrganization(OrganizationRequestDto organizationRequestDto);

    OrganizationResponseDto updateOrganizationWithId(UUID id, OrganizationRequestDto organizationRequestDto) throws OrganizationNotFoundException;
    OrganizationResponseDto getOrganizationWithId(UUID id) throws OrganizationNotFoundException;

    UUID deleteOrganizationWithId(UUID id);

    void banOrganizationWithId(UUID id) throws OrganizationNotFoundException;

}
