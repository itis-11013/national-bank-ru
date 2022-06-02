package ru.itis.nationalbankru.dto.central;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 10:37
 **/

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CentralContractRequestDto {
    @JsonProperty("contractid")
    private UUID contractId;
    @JsonProperty("productid")
    private UUID productId;
    private double count;
    private CentralOrganizationResponseDto buyer;
    private Date createdAt;
    private Boolean isPaid;
    private Date paymentDate;
    private Date deliveryDate;
}
