package ru.itis.nationalbankru.services.organization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.central.CentralOrganizationDto;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.entity.User;
import ru.itis.nationalbankru.entity.enums.Status;
import ru.itis.nationalbankru.exceptions.Exceptions;
import ru.itis.nationalbankru.exceptions.OrganizationNotFoundException;
import ru.itis.nationalbankru.helpers.PageHelper;
import ru.itis.nationalbankru.helpers.UserHelper;
import ru.itis.nationalbankru.mappers.OrganizationMapper;
import ru.itis.nationalbankru.repositories.OrganizationRepository;
import ru.itis.nationalbankru.services.central.CentralService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final UserHelper userHelper;
    private final PageHelper pageHelper;
    private final CentralService<OrganizationRequestDto, CentralOrganizationDto> centralService;

    private final String entityPath = "organization";

    @Override
    public List<OrganizationResponseDto> getAllUserOrganization(PageableDto pageableDto) {
        // Get current_user
        User user = userHelper.getCurrentUser();

        // Return current_user paginated organizations
        Pageable pageable = pageHelper.toPageable(pageableDto);
        Page<Organization> pageOrganizations = organizationRepository.findOrganizationsByUser(user, pageable);
        return organizationMapper.toDto(pageOrganizations.getContent());
    }

    @Override
    public List<OrganizationResponseDto> getAllOrganization(PageableDto pageableDto) {
        // Return all_users paginated organizations
        Pageable pageable = pageHelper.toPageable(pageableDto);
        Page<Organization> pageOrganizations = organizationRepository.findAll(pageable);
        return organizationMapper.toDto(pageOrganizations.getContent());
    }

    @Override
    public OrganizationResponseDto createOrganization(OrganizationRequestDto organizationRequestDto) {
        // Get current user & innerId from central bank
        User currentUser = userHelper.getCurrentUser();
        UUID innerId = centralService.createEntity(entityPath, organizationRequestDto);
        Organization organization = organizationMapper.fromDto(organizationRequestDto);

        // Set organization fields
        organization.toBuilder().inner_id(innerId).user(currentUser).status(Status.ACTIVE).build();
        organizationRepository.save(organization);

        log.info(String.format("Created Organization with name %s", organization.getName()));
        return organizationMapper.toDto(organization);
    }

    @Override
    public OrganizationResponseDto updateOrganizationWithId(UUID id, OrganizationRequestDto organizationRequestDto) throws OrganizationNotFoundException {
        Organization organization = _getOrganizationWithId(id);

        // Update local & central banks then save
        centralService.updateEntity(entityPath, organization.getInner_id(), organizationRequestDto);
        organizationMapper.updateFromDto(organizationRequestDto, organization);
        organizationRepository.save(organization);

        log.info(String.format("Updated Organization with name %s", organization.getName()));
        return organizationMapper.toDto(organization);
    }

    @Override
    public OrganizationResponseDto getOrganizationWithId(UUID id) throws OrganizationNotFoundException {
        return organizationMapper.toDto(_getOrganizationWithId(id));
    }

    @Override
    public UUID deleteOrganizationWithId(UUID id) {
        // Delete organization on local & central banks
        centralService.deleteEntity(entityPath, id);
        organizationRepository.deleteById(id);

        log.info(String.format("Delete Organization with id %s", id));
        return id;
    }

    @Override
    public void banOrganizationWithId(UUID id) throws OrganizationNotFoundException {
        Organization organization = _getOrganizationWithId(id);
        organization.setStatus(Status.BANNED);
        organizationRepository.save(organization);
        log.info(String.format("Banned Organization with name %s", organization.getName()));
    }

    @Override
    public Organization _getOrganizationWithId(UUID id) throws OrganizationNotFoundException {
        return organizationRepository.findById(id).orElseThrow(() -> Exceptions.organizationNotFoundException(id));
    }

    @Override
    public boolean isOrganizationFromRussia(UUID id) {
        return organizationRepository.findById(id).isPresent();
    }
}
