package ru.itis.nationalbankru.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;
import ru.itis.nationalbankru.entity.User;

import java.util.List;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 11:10
 **/

@Mapper
public interface UserMapper extends EntityMapper<User, UserRequestDto, UserResponseDto> {

    UserResponseDto toDto(User user);

    List<UserResponseDto> toDto(List<User> users);

    User fromDto(UserRequestDto userRequestDto);

    void updateFromDto(UserRequestDto userRequestDto, @MappingTarget User user);
}
