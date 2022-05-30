package ru.itis.nationalbankru.dto.contract;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.itis.nationalbankru.dto.AbstractResponse;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.entity.Product;

import java.util.Date;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 09:15
 **/

@Builder
@Getter
@Setter
public class ContractResponseDto extends AbstractResponse {
    private Integer count;
    private Boolean isPaid;
    private Date paymentDate;
    private Date deliveryDate;
    private Organization buyer;
    private Product product;
}
