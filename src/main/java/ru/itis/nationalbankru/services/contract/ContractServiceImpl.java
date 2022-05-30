package ru.itis.nationalbankru.services.contract;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.nationalbankru.dto.contract.ContractRequestDto;
import ru.itis.nationalbankru.dto.contract.ContractResponseDto;
import ru.itis.nationalbankru.entity.Contract;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.exceptions.ContractIsPaidException;
import ru.itis.nationalbankru.exceptions.ContractNotFoundException;
import ru.itis.nationalbankru.exceptions.Exceptions;
import ru.itis.nationalbankru.exceptions.NoSufficientFundException;
import ru.itis.nationalbankru.mappers.ContractMapper;
import ru.itis.nationalbankru.repositories.ContractRepository;
import ru.itis.nationalbankru.repositories.OrganizationRepository;
import ru.itis.nationalbankru.services.organization.OrganizationService;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 09:09
 **/

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final OrganizationRepository organizationRepository;
    private final OrganizationService organizationService;
    private final ContractMapper contractMapper;

    @Override
    public ContractResponseDto createContract(ContractRequestDto contractRequestDto) throws NoSufficientFundException {
        Contract contract = contractMapper.fromDto(contractRequestDto);

        // Check if product is in our bank
        Organization seller = contract.getSeller();

        boolean isBuyerFromRussia = organizationService.isOrganizationFromRussia(contractRequestDto.getBuyerId());
        Organization buyer = null;
        if (isBuyerFromRussia) {
            // Check if organization has enough funds to purchase product
            buyer = organizationRepository.getById(contractRequestDto.getBuyerId());
            double newOrganizationBalance = buyer.getBalance() - contract.getContractAmount();
            if (newOrganizationBalance < 0)
                throw Exceptions.noSufficientFundException(buyer.getName(), contract.getContractAmount());
            else
                // Freeze contract amount to buyer
                this.freezeContractAmount(buyer, contract.getContractAmount());
        }

        //TODO create contract on central bank using centralBankService

        // Insert contract in database
        contract.setSeller(seller);
        contract.setBuyer(buyer);
        contractRepository.save(contract);
        return contractMapper.toDto(contract);
    }

    @Override
    public ContractResponseDto getContractById(UUID id) throws ContractNotFoundException {
        Contract contract = this._getContractById(id);
        return contractMapper.toDto(contract);
    }

    @Override
    public UUID deleteContractById(UUID id) throws ContractNotFoundException, ContractIsPaidException {
        Contract contract = this._getContractById(id);

        // Throw error if contract is paid
        if (contract.getIsPaid()) throw Exceptions.contractIsPaidException(id);

        // Refund money to product buyer (if from russia)
        Organization buyer = contract.getBuyer();
        if (buyer != null) {
            this.refundContractAmount(buyer, contract.getContractAmount());
        }

        // TODO delete contract in central bank using centralBankService

        contractRepository.delete(contract);
        return id;
    }

    @Override
    public Contract _getContractById(UUID id) throws ContractNotFoundException {
        return contractRepository.findById(id).orElseThrow(() -> Exceptions.contractNotFoundException(id));
    }

    private void freezeContractAmount(Organization organization, Double contractAmount) {
        double newOrganizationBalance = organization.getBalance() - contractAmount;
        double newOrganizationFrozenBalance = organization.getFrozenBalance() + contractAmount;
        this.setOrganizationBalanceState(organization, newOrganizationBalance, newOrganizationFrozenBalance);
    }

    private void refundContractAmount(Organization organization, Double contractAmount) {
        double newOrganizationBalance = organization.getBalance() + contractAmount;
        double newOrganizationFrozenBalance = organization.getFrozenBalance() - contractAmount;
        this.setOrganizationBalanceState(organization, newOrganizationBalance, newOrganizationFrozenBalance);
    }

    private void setOrganizationBalanceState(Organization organization, Double balance, Double freezeBalance) {
        organization.setBalance(balance);
        organization.setFrozenBalance(freezeBalance);
    }

}
