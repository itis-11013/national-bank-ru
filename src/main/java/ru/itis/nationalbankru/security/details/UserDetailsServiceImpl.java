package ru.itis.nationalbankru.security.details;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.itis.nationalbankru.entity.Role;
import ru.itis.nationalbankru.entity.User;
import ru.itis.nationalbankru.repositories.RoleRepository;
import ru.itis.nationalbankru.repositories.UserRepository;

import java.util.List;

@Component("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with this email " +
                "doesn't exists"));
        List<Role> roles = roleRepository.findByUser(user.getId());
        user.setRoles(roles);
        return new UserDetailImpl(user);
    }
}
