package ru.itis.nationalbankru.dto.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 09:11
 **/

@Data
public class ContractRequestDto {
    @JsonProperty("productid")
    private UUID productInnerId;
    private double count;
    @JsonProperty("sellerid")
    private UUID sellerInnerId;
}
