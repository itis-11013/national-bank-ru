package ru.itis.nationalbankru.services.user;

import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUserWithId(UserRequestDto userRequestDto, Long id);

    UserResponseDto getUserWithId(UserRequestDto userRequestDto);

    UUID deleteUserWithId(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers(UserRequestDto userRequestDto);

}
