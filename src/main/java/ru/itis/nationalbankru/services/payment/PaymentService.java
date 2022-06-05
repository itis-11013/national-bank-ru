package ru.itis.nationalbankru.services.payment;

import ru.itis.nationalbankru.dto.contract.ContractResponseDto;
import ru.itis.nationalbankru.exceptions.CentralResponseException;
import ru.itis.nationalbankru.exceptions.ContractIsPaidException;
import ru.itis.nationalbankru.exceptions.ContractNotFoundException;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 22:10
 **/
public interface PaymentService {

    ContractResponseDto submitPayment(UUID contractId) throws ContractIsPaidException, ContractNotFoundException, CentralResponseException;
}
