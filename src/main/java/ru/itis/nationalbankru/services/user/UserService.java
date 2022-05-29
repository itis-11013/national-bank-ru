package ru.itis.nationalbankru.services.user;

import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {


    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUserWithId(UUID id, UserRequestDto userRequestDto);

    UUID deleteUserWithId(UUID id);

    void banUserWithId(UUID id);

}
