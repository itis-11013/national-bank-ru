package ru.itis.nationalbankru.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.nationalbankru.dto.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.OrganizationResponseDto;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.reposistory.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    private final PasswordEncoder passwordEncoder;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, PasswordEncoder passwordEncoder) {
        this.organizationRepository = organizationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OrganizationResponseDto createOrganization(OrganizationRequestDto organizationRequestDto) {
        Organization newOrganization = Organization.builder()
                .name(organizationRequestDto.getName())
                .email(organizationRequestDto.getEmail())
                .passwordHash(passwordEncoder.encode(organizationRequestDto.getPassword()))
                .build();
        organizationRepository.save(newOrganization);
        // TODO: return token
        return null;
    }
}
