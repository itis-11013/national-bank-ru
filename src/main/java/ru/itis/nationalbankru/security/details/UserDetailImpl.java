package ru.itis.nationalbankru.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.entity.Role;
import ru.itis.nationalbankru.entity.enums.Status;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailImpl implements UserDetails {

    private final Organization organization;

    public UserDetailImpl(Organization organization) {
        this.organization = organization;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
        for (Role role : organization.getRoles()) roles.add(new SimpleGrantedAuthority(role.getName()));
        return roles;
    }

    @Override
    public String getPassword() {
        return this.organization.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return this.organization.getName();
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Status.ACTIVE.equals(this.organization.getStatus());
    }

}
