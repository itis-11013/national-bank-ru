package ru.itis.nationalbankru.services.user;

import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;

import java.util.List;
public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUserWithId(Long id, UserRequestDto userRequestDto);

    Long deleteUserWithId(Long id);

    void banUserWithUsername(String username);

    List<UserResponseDto> getAllUsers();

}
