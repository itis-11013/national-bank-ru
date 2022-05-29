package ru.itis.nationalbankru.services.organization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.entity.User;
import ru.itis.nationalbankru.entity.enums.Status;
import ru.itis.nationalbankru.exceptions.Exceptions;
import ru.itis.nationalbankru.exceptions.OrganizationNotFoundException;
import ru.itis.nationalbankru.helpers.UserHelper;
import ru.itis.nationalbankru.mappers.OrganizationMapper;
import ru.itis.nationalbankru.repositories.OrganizationRepository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
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


    @Override
    public List<OrganizationResponseDto> getAllUserOrganization(Integer pageNo, Integer pageSize, String sortBy) {
        // Get current_user
        User user = userHelper.getCurrentUser();

        // Return current_user paginated organizations
        Pageable pageable = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Organization> organizations = organizationRepository.findOrganizationsByUser(user, pageable);
        return organizationMapper.toDto(organizations);
    }

    @Override
    public List<OrganizationResponseDto> getAllOrganization(Integer pageNo, Integer pageSize, String sortBy) {
        // Return all_users paginated organizations
        Pageable pageable = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Organization> organizations = organizationRepository.findAll(pageable);
        return organizationMapper.toDto(organizations);
    }

    @Override
    public OrganizationResponseDto createOrganization(OrganizationRequestDto organizationRequestDto) {
        Organization organization = organizationMapper.fromDto(organizationRequestDto);
        //TODO create organization in central bank
        organizationRepository.save(organization);
        return organizationMapper.toDto(organization);
    }

    @Override
    public OrganizationResponseDto updateOrganizationWithId(UUID id, OrganizationRequestDto organizationRequestDto) throws OrganizationNotFoundException {
        Organization organization = _getOrganizationWithId(id);
        organizationMapper.updateFromDto(organizationRequestDto, organization);
        //TODO update organization in central bank
        organizationRepository.save(organization);

        log.info(String.format("Updated Organization with name %s",organization.getName()));
        return organizationMapper.toDto(organization);
    }

    @Override
    public OrganizationResponseDto getOrganizationWithId(UUID id) throws OrganizationNotFoundException {
        return organizationMapper.toDto(_getOrganizationWithId(id));
    }

    @Override
    public UUID deleteOrganizationWithId(UUID id) {
        organizationRepository.deleteById(id);
        log.info(String.format("Delete Organization with id %s", id));
        //TODO: delete organization in the central bank
        return id;
    }

    @Override
    public void banOrganizationWithId(UUID id) throws OrganizationNotFoundException {
        Organization organization = _getOrganizationWithId(id);
        organization.setStatus(Status.BANNED);
        organizationRepository.save(organization);
        log.info(String.format("Banned Organization with name %s", organization.getName()));
    }

    private Organization _getOrganizationWithId(UUID id) throws OrganizationNotFoundException {
        return organizationRepository.findById(id).orElseThrow(() -> Exceptions.organizationNotFoundException(id));
    }
}
