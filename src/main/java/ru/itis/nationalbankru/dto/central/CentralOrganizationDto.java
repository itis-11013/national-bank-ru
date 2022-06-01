package ru.itis.nationalbankru.dto.central;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 31.05.2022, Tue
 * @time : 11:59
 **/
@Builder
@Data
public class CentralOrganizationDto {
    String name;
    String address;
    UUID innerId;
    String countryCode;
}
