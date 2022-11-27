package ru.itis.nationalbankru.services.organization;

import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.exceptions.CentralResponseException;
import ru.itis.nationalbankru.exceptions.NoSufficientFundException;
import ru.itis.nationalbankru.exceptions.OrganizationNotFoundException;

import java.util.List;
import java.util.UUID;

public interface OrganizationService {

    List<OrganizationResponseDto> getAllOrganization(PageableDto pageableDto);

    OrganizationResponseDto createOrganization(OrganizationRequestDto organizationRequestDto) throws CentralResponseException;

    OrganizationResponseDto updateOrganizationById(Long id, OrganizationRequestDto organizationRequestDto) throws OrganizationNotFoundException, CentralResponseException;

    OrganizationResponseDto getOrganizationById(Long id) throws OrganizationNotFoundException;

    Long deleteOrganizationById(Long id) throws CentralResponseException;

    void banOrganizationById(Long id) throws OrganizationNotFoundException;

    Organization _getOrganizationWithId(Long id) throws OrganizationNotFoundException;

    void validateOrganizationFundsOnPurchase(Organization organization, Double amount) throws NoSufficientFundException;

    Organization getOrganizationByInnerId(UUID uuid) throws OrganizationNotFoundException;

    boolean isOrganizationFromRussia(Long id);

    OrganizationResponseDto getCurrentUser();
}
