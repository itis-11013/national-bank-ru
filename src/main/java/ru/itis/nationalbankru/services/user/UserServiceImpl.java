package ru.itis.nationalbankru.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;
import ru.itis.nationalbankru.entity.User;
import ru.itis.nationalbankru.mapper.UserMapper;
import ru.itis.nationalbankru.reposistory.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User newUser = User.builder()
                .username(userRequestDto.getUsername())
                .email(userRequestDto.getEmail())
                .passwordHash(passwordEncoder.encode(userRequestDto.getPassword()))
                .build();
        return UserResponseDto.from(userRepository.save(newUser));
    }

    @Override
    public UserResponseDto updateUserWithId(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.getById(id);
        userMapper.updateFromDto(userRequestDto,user);
        userRepository.save(user);
        return UserResponseDto.from(user);
    }

    @Override
    public Long deleteUserWithId(Long id) {
        User user = userRepository.getById(id);
        userRepository.delete(user);
        return id;
    }

    @Override
    public void banUserWithUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with username {%s} not found", username)));
        user.setStatus(User.Status.BANNED);
        userRepository.save(user);
        log.info(String.format("Banned user with username {%s}", username));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return UserResponseDto.from(userRepository.findAll());
    }

}
