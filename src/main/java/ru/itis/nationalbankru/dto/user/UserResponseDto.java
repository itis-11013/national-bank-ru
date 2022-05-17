package ru.itis.nationalbankru.dto.user;

import lombok.Builder;

@Builder
public class UserResponseDto {
    private Object data;
    private Long time;
}
