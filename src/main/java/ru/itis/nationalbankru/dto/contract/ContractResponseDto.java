package ru.itis.nationalbankru.dto.contract;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.itis.nationalbankru.dto.AbstractResponse;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.dto.product.ProductResponseDto;

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
    private Boolean isPaid;
    private Date paymentDate;
    private Integer count;
    private OrganizationResponseDto buyer;
    private ProductResponseDto product;
}
