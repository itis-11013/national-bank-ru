package ru.itis.nationalbankru.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.itis.nationalbankru.dto.central.contract.CentralContractRequestDto;
import ru.itis.nationalbankru.dto.contract.ContractRequestDto;
import ru.itis.nationalbankru.dto.contract.ContractResponseDto;
import ru.itis.nationalbankru.entity.Contract;

import java.util.List;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 09:19
 **/


@Mapper(uses = {OrganizationMapper.class, ProductMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContractMapper extends EntityMapper<Contract, ContractRequestDto, ContractResponseDto> {

    @Mapping(target = "amount", expression = "java(contract.getContractAmount())")
    ContractResponseDto toDto(Contract contract);

    List<ContractResponseDto> toDto(List<Contract> contracts);

    Contract fromDto(ContractRequestDto contractRequestDto);

    Contract fromDto(CentralContractRequestDto centralContractRequestDto);

    void updateFromDto(ContractRequestDto contractRequestDto, @MappingTarget Contract contract);
}
