package ru.itis.nationalbankru.services.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.nationalbankru.dto.contract.ContractResponseDto;
import ru.itis.nationalbankru.entity.Contract;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.entity.Product;
import ru.itis.nationalbankru.exceptions.CentralResponseException;
import ru.itis.nationalbankru.exceptions.ContractIsPaidException;
import ru.itis.nationalbankru.exceptions.ContractNotFoundException;
import ru.itis.nationalbankru.exceptions.Exceptions;
import ru.itis.nationalbankru.mappers.ContractMapper;
import ru.itis.nationalbankru.repositories.ContractRepository;
import ru.itis.nationalbankru.repositories.OrganizationRepository;
import ru.itis.nationalbankru.repositories.ProductRepository;
import ru.itis.nationalbankru.services.central.CentralService;
import ru.itis.nationalbankru.services.contract.ContractService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    private final ContractRepository contractRepository;
    private final OrganizationRepository organizationRepository;
    private final ProductRepository productRepository;
    private final ContractMapper contractMapper;

    private final ContractService contractService;
    private final CentralService<UUID, String> centralService;

    @Override
    public ContractResponseDto submitPayment(UUID contractId) throws
            ContractIsPaidException,
            ContractNotFoundException,
            CentralResponseException {
        Contract contract = contractService.getContractByInnerId(contractId);

        // Throw error if contract is paid
        if (contract.getIsPaid()) throw Exceptions.contractIsPaidException(contractId);

        // Set new buyer balance (withdraw money)
        Organization buyer = contract.getBuyer();
        if (buyer != null) {
            centralService.createEntity("payment", contractId);
            organizationRepository.payContractFees(buyer.getId(), contract.getContractAmount());
        }

        Product product = contract.getProduct();
        Organization seller = product.getSeller();
        if (seller != null) {
            // Set new seller balance (deposit money)
            organizationRepository.depositContractFees(seller.getId(), contract.getContractAmount());
            // Release product count
            productRepository.payContractCount(product.getId(), contract.getContractAmount());
        }

        // Set contract as paid
        contract.setPaymentDate(LocalDateTime.now());
        contract.setIsPaid(true);
        contractRepository.save(contract);

        return contractMapper.toDto(contract);
    }
}
