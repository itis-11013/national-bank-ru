package ru.itis.nationalbankru.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.repositories.OrganizationRepository;

import java.nio.file.attribute.UserPrincipalNotFoundException;


@Component
@RequiredArgsConstructor
public class OrganizationHelper {
    private final OrganizationRepository organizationRepository;

    public Organization getCurrentOrganization() throws UserPrincipalNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principal = (String) authentication.getPrincipal();
        return organizationRepository
                .findByName(principal)
                .orElseThrow(() -> new UserPrincipalNotFoundException(principal));
    }
}
