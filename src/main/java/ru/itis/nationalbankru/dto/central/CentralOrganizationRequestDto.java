package ru.itis.nationalbankru.dto.central;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Escalopa
 * @created : 04.06.2022, Sat
 * @time : 22:50
 **/
@Getter
@Setter
@Builder
public class CentralOrganizationRequestDto {
    @Builder.Default
    private final String country = "ru";
    private String name;
    private String address;
}
