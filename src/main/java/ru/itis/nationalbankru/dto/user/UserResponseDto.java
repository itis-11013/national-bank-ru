package ru.itis.nationalbankru.dto.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import ru.itis.nationalbankru.entity.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private Timestamp createdAt;
    private Long time;

    public static UserResponseDto from (User user){
        return new ObjectMapper().convertValue(user,UserResponseDto.class);
    }

    public static List<UserResponseDto> from(List<User> users){
        return users.stream().map(UserResponseDto::from).collect(Collectors.toList());
    }
}
