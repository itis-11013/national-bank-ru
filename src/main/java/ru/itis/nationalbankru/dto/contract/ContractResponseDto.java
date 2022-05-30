package ru.itis.nationalbankru.dto.contract;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.itis.nationalbankru.dto.AbstractResponse;
import ru.itis.nationalbankru.entity.Organization;

import java.util.Date;
import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 09:15
 **/

@Builder
@Getter
@Setter
public class ContractResponseDto extends AbstractResponse {
    private UUID inner_id;
    private Boolean isPaid;
    private Date paymentDate;
    private Integer count;
    private Double unitPrice;
    private UUID productInnerId;
    private Organization seller;
    private Organization buyer;
}
