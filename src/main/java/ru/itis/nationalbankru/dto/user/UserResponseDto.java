package ru.itis.nationalbankru.dto.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.entity.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class UserResponseDto {
    private UUID id;
    private String email;
    private Date createdAt;
    private LocalDateTime updatedAt;
    private List<Organization> organizations;

    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .organizations(user.getOrganizations())
                .build();
    }

    public static List<UserResponseDto> from(List<User> users) {
        return users.stream().map(UserResponseDto::from).collect(Collectors.toList());
    }
}
