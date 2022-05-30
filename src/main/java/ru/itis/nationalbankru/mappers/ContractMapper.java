package ru.itis.nationalbankru.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itis.nationalbankru.dto.contract.ContractRequestDto;
import ru.itis.nationalbankru.dto.contract.ContractResponseDto;
import ru.itis.nationalbankru.entity.Contract;

import java.util.List;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 09:19
 **/

@Mapper
public interface ContractMapper extends EntityMapper<Contract, ContractRequestDto, ContractResponseDto> {

    ContractResponseDto toDto(Contract user);

    List<ContractResponseDto> toDto(List<Contract> users);

    Contract fromDto(ContractRequestDto userRequestDto);

    void updateFromDto(ContractRequestDto userRequestDto, @MappingTarget Contract user);
}
