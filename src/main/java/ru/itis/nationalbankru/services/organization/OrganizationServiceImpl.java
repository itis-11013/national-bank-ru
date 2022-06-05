package ru.itis.nationalbankru.services.organization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.central.CentralOrganizationRequestDto;
import ru.itis.nationalbankru.dto.central.CentralOrganizationResponseDto;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.entity.Role;
import ru.itis.nationalbankru.entity.enums.RoleName;
import ru.itis.nationalbankru.entity.enums.Status;
import ru.itis.nationalbankru.exceptions.CentralResponseException;
import ru.itis.nationalbankru.exceptions.Exceptions;
import ru.itis.nationalbankru.exceptions.OrganizationNotFoundException;
import ru.itis.nationalbankru.helpers.PageHelper;
import ru.itis.nationalbankru.mappers.OrganizationMapper;
import ru.itis.nationalbankru.repositories.OrganizationRepository;
import ru.itis.nationalbankru.repositories.RoleRepository;
import ru.itis.nationalbankru.services.central.CentralService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    private final PasswordEncoder passwordEncoder;
    private final OrganizationRepository organizationRepository;
    private final RoleRepository roleRepository;
    private final OrganizationMapper organizationMapper;
    private final PageHelper pageHelper;
    private final CentralService<CentralOrganizationRequestDto, CentralOrganizationResponseDto> centralService;
    private final String entityPath = "organization";
    @Value("${organizationBalanceInitAmount}")
    private Double organizationBalanceInitAmount;

    @Override
    public List<OrganizationResponseDto> getAllOrganization(PageableDto pageableDto) {
        Pageable pageable = pageHelper.toPageable(pageableDto);
        Page<Organization> pageOrganizations = organizationRepository.findAll(pageable);
        return organizationMapper.toDto(pageOrganizations.getContent());
    }

    @Override
    public OrganizationResponseDto createOrganization(OrganizationRequestDto organizationRequestDto) throws
            CentralResponseException {

        Role role = roleRepository.findByName(RoleName.USER.name());
        UUID innerId = centralService.createEntity(entityPath, organizationMapper.toCentralDto(organizationRequestDto));
        Organization organization = organizationMapper.fromDto(organizationRequestDto);
        organization = organization.toBuilder()
                .innerId(innerId)
                .status(Status.ACTIVE)
                .roles(List.of(role))
                .balance(organizationBalanceInitAmount)
                .passwordHash(passwordEncoder.encode(organizationRequestDto.getPassword()))
                .build();

        organizationRepository.save(organization);
        return organizationMapper.toDto(organization);
    }

    @Override
    public OrganizationResponseDto updateOrganizationById(Long id, OrganizationRequestDto organizationRequestDto) throws
            OrganizationNotFoundException,
            CentralResponseException {
        Organization organization = _getOrganizationWithId(id);

        // Update organizations
        organizationMapper.updateFromDto(organizationRequestDto, organization);
        if (organizationRequestDto.getPassword() != null && !organizationRequestDto.getPassword().isEmpty())
            organization.setPasswordHash(passwordEncoder.encode(organizationRequestDto.getPassword()));

        // Update local & central banks then save
        centralService.updateEntity(entityPath, organization.getInnerId(),
                organizationMapper.toCentralDto(organizationRequestDto));
        organizationRepository.save(organization);
        return organizationMapper.toDto(organization);
    }

    @Override
    public OrganizationResponseDto getOrganizationById(Long id) throws OrganizationNotFoundException {
        return organizationMapper.toDto(this._getOrganizationWithId(id));
    }

    @Override
    public Long deleteOrganizationById(Long id) throws CentralResponseException {
        // Get inner id
        Organization organization = organizationRepository.getById(id);
        UUID uuid = organization.getInnerId();

        // Delete organization everywhere
        centralService.deleteEntity(entityPath, uuid);
        organizationRepository.deleteById(id);
        return id;
    }

    @Override
    public void banOrganizationById(Long id) throws OrganizationNotFoundException {
        Organization organization = this._getOrganizationWithId(id);
        organization.setStatus(Status.BANNED);
        organizationRepository.save(organization);
    }

    @Override
    public Organization getOrganizationByInnerId(UUID uuid) throws OrganizationNotFoundException {
        return organizationRepository.findOrganizationsByInnerId(uuid).orElseThrow(() -> Exceptions.organizationNotFoundException(uuid));
    }

    @Override
    public Organization _getOrganizationWithId(Long id) throws OrganizationNotFoundException {
        return organizationRepository.findById(id).orElseThrow(() -> Exceptions.organizationNotFoundException(id));
    }

    @Override
    public boolean isOrganizationFromRussia(Long id) {
        return organizationRepository.findById(id).isPresent();
    }
}
