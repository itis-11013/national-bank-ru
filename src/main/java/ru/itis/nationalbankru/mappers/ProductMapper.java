package ru.itis.nationalbankru.mappers;

import ru.itis.nationalbankru.dto.product.ProductRequestDto;
import ru.itis.nationalbankru.dto.product.ProductResponseDto;
import ru.itis.nationalbankru.entity.Product;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 19:51
 **/
public interface ProductMapper extends EntityMapper<Product, ProductRequestDto, ProductResponseDto> {

    Product fromDto(ProductResponseDto productResponseDto);
}
