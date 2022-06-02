package ru.itis.nationalbankru.dto.central;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CentralOrganizationResponseDto {
    String name;
    String address;
    @JsonProperty("innerid")
    UUID innerId;
    @JsonProperty("country_code")
    String countryCode;
}
