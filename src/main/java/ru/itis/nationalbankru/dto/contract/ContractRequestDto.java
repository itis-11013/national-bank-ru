package ru.itis.nationalbankru.dto.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 09:11
 **/

@Getter
@Setter
public class ContractRequestDto {
    @JsonProperty(value = "productid")
    private UUID productInnerId;
    private double count;
}
