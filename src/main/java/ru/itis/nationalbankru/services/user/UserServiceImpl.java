package ru.itis.nationalbankru.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;
import ru.itis.nationalbankru.entity.Role;
import ru.itis.nationalbankru.entity.enums.RoleName;
import ru.itis.nationalbankru.entity.enums.Status;
import ru.itis.nationalbankru.entity.User;
import ru.itis.nationalbankru.exception.Exceptions;
import ru.itis.nationalbankru.repositories.RoleRepository;
import ru.itis.nationalbankru.repositories.UserRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        Role role = roleRepository.findByName(RoleName.USER.name());
        User newUser = User.builder()
                .email(userRequestDto.getEmail())
                .status(Status.ACTIVE)
                .roles(List.of(role))
                .passwordHash(passwordEncoder.encode(userRequestDto.getPassword()))
                .build();
        userRepository.save(newUser);
        return UserResponseDto.from(newUser);
    }

    @Override
    public UserResponseDto updateUserWithId(UUID id, UserRequestDto userRequestDto) {
        User user = userRepository.getById(id);
        user.setEmail(userRequestDto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepository.save(user);
        return UserResponseDto.from(user);
    }

    @Override
    public UUID deleteUserWithId(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> Exceptions.usernameNotFoundException(id));
        userRepository.delete(user);
        log.info(String.format("Deleted user with email {%s}", user.getEmail()));
        return id;
    }

    @Override
    public void banUserWithId(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> Exceptions.usernameNotFoundException(id));
        user.setStatus(Status.BANNED);
        userRepository.save(user);
        log.info(String.format("Banned user with email {%s}", user.getEmail()));
    }
}
