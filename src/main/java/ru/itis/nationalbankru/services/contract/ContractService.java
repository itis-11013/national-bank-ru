package ru.itis.nationalbankru.services.contract;

import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.central.contract.CentralContractRequestDto;
import ru.itis.nationalbankru.dto.contract.ContractRequestDto;
import ru.itis.nationalbankru.dto.contract.ContractResponseDto;
import ru.itis.nationalbankru.entity.Contract;
import ru.itis.nationalbankru.exceptions.*;

import java.util.List;
import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 09:09
 **/
public interface ContractService {


    List<ContractResponseDto> getAllContracts(PageableDto pageableDto);

    ContractResponseDto createContract(ContractRequestDto contractRequestDto) throws
            OrganizationNotFoundException,
            NoSufficientFundException,
            ProductCatalogNotFound,
            UnitNotFoundException, CentralResponseException, ProductExceedStockLimitException;

    ContractResponseDto createContractFromCentral(CentralContractRequestDto contractRequestDto) throws ProductExceedStockLimitException;

    ContractResponseDto getContractById(UUID id) throws ContractNotFoundException;

    UUID deleteContractById(UUID id) throws ContractNotFoundException, ContractIsPaidException, CentralResponseException;

    Contract getContractByInnerId(UUID id) throws ContractNotFoundException;
}
