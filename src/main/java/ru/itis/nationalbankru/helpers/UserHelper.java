package ru.itis.nationalbankru.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.itis.nationalbankru.entity.User;
import ru.itis.nationalbankru.exception.Exceptions;
import ru.itis.nationalbankru.repositories.UserRepository;


@Component
@RequiredArgsConstructor
public class UserHelper {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository
                .findByEmail((String) auth.getPrincipal())
                .orElseThrow(() -> Exceptions.usernameNotFoundException(null));
    }
}
