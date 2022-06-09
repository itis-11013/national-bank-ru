package ru.itis.nationalbankru.mappers;

import org.mapstruct.Mapper;
import ru.itis.nationalbankru.dto.product.UnitResponse;
import ru.itis.nationalbankru.entity.Unit;

import java.util.List;

/**
 * @author : Escalopa
 * @created : 09.06.2022, Thu
 * @time : 11:07
 **/

@Mapper
public interface UnitMapper {
    UnitResponse toDto(Unit unit);

    List<UnitResponse> toDto(List<Unit> units);
}
