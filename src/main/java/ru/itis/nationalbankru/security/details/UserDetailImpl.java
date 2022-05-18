package ru.itis.nationalbankru.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.nationalbankru.entity.Role;
import ru.itis.nationalbankru.entity.User;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailImpl implements UserDetails {

    private final User user;

    public UserDetailImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
        for (Role role : user.getRoles()) roles.add(new SimpleGrantedAuthority(role.getName()));
        return roles;
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getPasswordHash();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return (User.Status.ACTIVE.equals(user.getStatus()));
    }
}
