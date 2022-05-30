package ru.itis.nationalbankru.dto.contract;

import lombok.Data;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 09:11
 **/

@Data
public class ContractRequestDto {
    private UUID productId;
    private UUID buyerId;
    private Integer count;
}
