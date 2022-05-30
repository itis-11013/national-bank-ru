package ru.itis.nationalbankru.services.contract;

import ru.itis.nationalbankru.dto.contract.ContractRequestDto;
import ru.itis.nationalbankru.dto.contract.ContractResponseDto;
import ru.itis.nationalbankru.entity.Contract;
import ru.itis.nationalbankru.exceptions.ContractIsPaidException;
import ru.itis.nationalbankru.exceptions.ContractNotFoundException;
import ru.itis.nationalbankru.exceptions.NoSufficientFundException;
import ru.itis.nationalbankru.exceptions.OrganizationNotFoundException;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 09:09
 **/
public interface ContractService {

    ContractResponseDto createContract(ContractRequestDto contractRequestDto) throws OrganizationNotFoundException, NoSufficientFundException;

    ContractResponseDto getContractById(UUID id) throws ContractNotFoundException;

    UUID deleteContractById(UUID id) throws ContractNotFoundException, ContractIsPaidException;

    Contract _getContractById(UUID id) throws ContractNotFoundException;
}
