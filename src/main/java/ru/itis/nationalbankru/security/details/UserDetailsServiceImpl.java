package ru.itis.nationalbankru.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import ru.itis.nationalbankru.entity.Role;
import ru.itis.nationalbankru.entity.User;
import ru.itis.nationalbankru.repositories.RoleRepository;
import ru.itis.nationalbankru.repositories.UserRepository;

import java.util.List;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow();
        List<Role> roles = roleRepository.findByUser(user.getId());
        user.setRoles(roles);
        return new UserDetailImpl(user);
    }
}
