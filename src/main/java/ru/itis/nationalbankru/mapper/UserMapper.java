package ru.itis.nationalbankru.mapper;


import org.mapstruct.*;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.entity.User;

@Mapper(componentModel="spring")
public interface UserMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UserRequestDto dto, @MappingTarget User org);
}
