package ru.itis.nationalbankru.services.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.nationalbankru.dto.contract.ContractResponseDto;
import ru.itis.nationalbankru.entity.Contract;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.exceptions.ContractIsPaidException;
import ru.itis.nationalbankru.exceptions.ContractNotFoundException;
import ru.itis.nationalbankru.exceptions.Exceptions;
import ru.itis.nationalbankru.mappers.ContractMapper;
import ru.itis.nationalbankru.repositories.ContractRepository;
import ru.itis.nationalbankru.services.contract.ContractService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 22:11
 **/

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    ContractRepository contractRepository;
    ContractMapper contractMapper;

    ContractService contractService;

    @Override
    public ContractResponseDto submitPayment(UUID contractId) throws ContractIsPaidException, ContractNotFoundException {
        Contract contract = contractService._getContractById(contractId);

        // Throw error if contract is paid
        if (contract.getIsPaid()) throw Exceptions.contractIsPaidException(contractId);

        // Set new buyer balance (withdraw money)
        Organization buyer = contract.getBuyer();
        if (buyer != null) {
            double newFreezeBalance = buyer.getFrozenBalance() - contract.getContractAmount();
            buyer.setFrozenBalance(newFreezeBalance);
        }

        // Set new seller balance (deposit money)
        Organization seller = contract.getSeller();
        if (seller != null) {
            double newBalance = seller.getBalance() + contract.getContractAmount();
            seller.setBalance(newBalance);
        }

        // Set contract as paid
        contract.setPaymentDate(new Date());
        contract.setIsPaid(true);
        contractRepository.save(contract);

        return contractMapper.toDto(contract);
    }
}
