package ru.itis.nationalbankru.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.itis.nationalbankru.dto.product.ProductRequestDto;
import ru.itis.nationalbankru.dto.product.ProductResponseDto;
import ru.itis.nationalbankru.entity.Product;

import java.util.List;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 19:51
 **/
@Mapper
public interface ProductMapper extends EntityMapper<Product, ProductRequestDto, ProductResponseDto> {

    @Mapping(target = "unit", source = "unit.id")
    ProductResponseDto toDto(Product product);

    List<ProductResponseDto> toDto(List<Product> products);

    @Mapping(target = "unit", ignore = true)
    Product fromDto(ProductRequestDto productRequestDto);

    @Mapping(target = "unit", ignore = true)
    Product fromDto(ProductResponseDto productResponseDto);

    @Mapping(target = "unit", ignore = true)
    void updateFromDto(ProductRequestDto productRequestDto, @MappingTarget Product product);

}
