package ru.itis.nationalbankru.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;
import ru.itis.nationalbankru.entity.User;
import ru.itis.nationalbankru.reposistory.UserRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = organizationRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Organization not found"));
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordHash(), authorities) {
        };
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User newUser = User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .passwordHash(passwordEncoder.encode(userRequestDto.getPassword()))
                .build();
        organizationRepository.save(newUser);
        return null;
    }


    @Override
    public UserResponseDto getUserWithId(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public UserResponseDto updateUserWithId(UserRequestDto userRequestDto, Long id) {
        return null;
    }

    @Override
    public UUID deleteUserWithId(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public List<UserResponseDto> getAllUsers(UserRequestDto userRequestDto) {
        return null;
    }
}
