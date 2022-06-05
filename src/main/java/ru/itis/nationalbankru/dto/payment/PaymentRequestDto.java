package ru.itis.nationalbankru.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 05.06.2022, Sun
 * @time : 12:53
 **/

@Data
public class PaymentRequestDto {
    @JsonProperty(value = "contractid")
    UUID contractId;
}
