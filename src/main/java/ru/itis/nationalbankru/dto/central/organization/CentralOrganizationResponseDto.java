package ru.itis.nationalbankru.dto.central.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 31.05.2022, Tue
 * @time : 11:59
 **/

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CentralOrganizationResponseDto {
    @JsonProperty("innerid")
    UUID innerId;
    @JsonProperty("country_code")
    String countryCode;
}
