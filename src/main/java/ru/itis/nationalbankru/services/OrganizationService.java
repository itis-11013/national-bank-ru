package ru.itis.nationalbankru.services;

import ru.itis.nationalbankru.dto.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.OrganizationResponseDto;
import ru.itis.nationalbankru.entity.Organization;

public interface OrganizationService {

    OrganizationResponseDto createOrganization(OrganizationRequestDto organizationRequestDto);

}
