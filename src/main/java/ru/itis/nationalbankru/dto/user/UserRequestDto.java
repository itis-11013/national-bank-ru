package ru.itis.nationalbankru.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UserRequestDto {
    private String username;
    private String email;
    private String password;
}
