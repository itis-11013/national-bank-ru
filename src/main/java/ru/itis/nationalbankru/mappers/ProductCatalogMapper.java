package ru.itis.nationalbankru.mappers;

import org.mapstruct.Mapper;
import ru.itis.nationalbankru.dto.product.ProductCatalogResponse;
import ru.itis.nationalbankru.entity.ProductCatalog;

import java.util.List;

/**
 * @author : Escalopa
 * @created : 09.06.2022, Thu
 * @time : 11:08
 **/

@Mapper
public interface ProductCatalogMapper {
    ProductCatalogResponse toDto(ProductCatalog productCatalog);

    List<ProductCatalogResponse> toDto(List<ProductCatalog> productCatalogs);
}
