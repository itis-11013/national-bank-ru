package ru.itis.nationalbankru.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.repositories.OrganizationRepository;
import ru.itis.nationalbankru.security.details.UserDetailImpl;


@Component
@RequiredArgsConstructor
public class OrganizationHelper {
    private final OrganizationRepository organizationRepository;

    public Organization getCurrentOrganization() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetailImpl) {
            username = ((UserDetailImpl) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return organizationRepository.getByName(username);
    }
}
