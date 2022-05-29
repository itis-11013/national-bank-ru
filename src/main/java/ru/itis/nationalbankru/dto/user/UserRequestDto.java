package ru.itis.nationalbankru.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
public class UserRequestDto {
    private String email;
    private String password;
}
