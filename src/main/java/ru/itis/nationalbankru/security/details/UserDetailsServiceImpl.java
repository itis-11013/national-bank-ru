package ru.itis.nationalbankru.security.details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.entity.Role;
import ru.itis.nationalbankru.repositories.OrganizationRepository;
import ru.itis.nationalbankru.repositories.RoleRepository;

import java.util.List;

@Component("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final OrganizationRepository organizationRepository;

    private final RoleRepository roleRepository;

    public UserDetailsServiceImpl(OrganizationRepository organizationRepository, RoleRepository roleRepository) {
        this.organizationRepository = organizationRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Organization organization = organizationRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException(
                "Organization " +
                        "with this name " +
                        "doesn't exists"));
        List<Role> roles = roleRepository.findByOrganization(organization.getId());
        organization.setRoles(roles);
        return new UserDetailImpl(organization);
    }
}
