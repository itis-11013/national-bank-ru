package ru.itis.nationalbankru.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Escalopa
 * @created : 09.06.2022, Thu
 * @time : 11:05
 **/
@Getter
@Setter
@Builder
public class ProductCatalogResponse {
    String code;
    String name;
}
