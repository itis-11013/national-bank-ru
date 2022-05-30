package ru.itis.nationalbankru.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.itis.nationalbankru.dto.AbstractResponse;
import ru.itis.nationalbankru.entity.Organization;

import java.util.List;

@Builder
@Getter
@Setter
public class UserResponseDto extends AbstractResponse {
    private String email;
    private List<Organization> organizations;
}
