package ru.itis.nationalbankru.mappers;

import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 11:11
 **/


public interface EntityMapper<EntityClass, RequestDto, ResponseDto> {

    ResponseDto toDto(EntityClass entityClass);

    List<ResponseDto> toDto(List<EntityClass> entityClassList);

    EntityClass fromDto(RequestDto requestDto);

    void updateFromDto(RequestDto requestDto, @MappingTarget EntityClass entityClass);
}
