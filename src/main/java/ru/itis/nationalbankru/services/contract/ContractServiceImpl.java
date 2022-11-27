package ru.itis.nationalbankru.services.contract;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.central.contract.CentralContractRequestDto;
import ru.itis.nationalbankru.dto.contract.ContractRequestDto;
import ru.itis.nationalbankru.dto.contract.ContractResponseDto;
import ru.itis.nationalbankru.entity.Contract;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.entity.Product;
import ru.itis.nationalbankru.exceptions.*;
import ru.itis.nationalbankru.helpers.OrganizationHelper;
import ru.itis.nationalbankru.helpers.PageHelper;
import ru.itis.nationalbankru.mappers.ContractMapper;
import ru.itis.nationalbankru.repositories.ContractRepository;
import ru.itis.nationalbankru.repositories.OrganizationRepository;
import ru.itis.nationalbankru.repositories.ProductRepository;
import ru.itis.nationalbankru.services.central.CentralService;
import ru.itis.nationalbankru.services.organization.OrganizationService;
import ru.itis.nationalbankru.services.product.ProductService;

import javax.transaction.Transactional;
import java.util.List;
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
    private final ProductRepository productRepository;
    private final OrganizationHelper organizationHelper;
    private final PageHelper pageHelper;
    private final ContractMapper contractMapper;
    private final ProductService productService;
    private final OrganizationService organizationService;
    private final CentralService<ContractRequestDto, ContractRequestDto> centralContractService;
    private final String entityPath = "organization";

    @Override
    public List<ContractResponseDto> getAllContracts(PageableDto pageableDto) {
        Pageable pageable = pageHelper.toPageable(pageableDto);
        List<Contract> contracts = contractRepository.findAll(pageable).getContent();
        return contractMapper.toDto(contracts);
    }

    @Override
    public List<ContractResponseDto> getAllMyContract(PageableDto pageableDto) {
        Organization currentUser = organizationHelper.getCurrentOrganization();
        Pageable pageable = pageHelper.toPageable(pageableDto);
        List<Contract> contracts = contractRepository.findContractByProductSeller(currentUser, pageable).getContent();
        return contractMapper.toDto(contracts);
    }

    @Override
    public ContractResponseDto createContract(ContractRequestDto contractRequestDto) throws
            NoSufficientFundException,
            CentralResponseException,
            ProductExceedStockLimitException {

        Contract contract = contractMapper.fromDto(contractRequestDto);
        Organization buyer = organizationHelper.getCurrentOrganization();
        Product product = productService.getProductOrFetchByInnerId(contractRequestDto.getProductInnerId());
        contract.setProduct(product);

        // Validations
        organizationService.validateOrganizationFundsOnPurchase(buyer, contract.getContractAmount());
        productService.validateProductCountOnPurchase(product.getInnerId(), contractRequestDto.getCount());

        // Get InnerId from central bank
        UUID innerId = centralContractService.createEntity(entityPath, contractRequestDto);

        // Freeze funds
        organizationRepository.freezeContractFees(buyer.getId(), contract.getContractAmount());
        productRepository.freezeContractCount(product.getId(), contract.getCount());

        // Insert the new product & contract in database
        productRepository.save(product);
        contract.setInnerId(innerId);
        contract.setBuyer(buyer);
        contract.setProduct(product);
        contractRepository.save(contract);
        return contractMapper.toDto(contract);
    }

    @Override
    public ContractResponseDto createContractFromCentral(CentralContractRequestDto centralContractRequestDto) throws
            ProductExceedStockLimitException {
        Product product = productService.getProductByInnerId(centralContractRequestDto.getProductId());
        Contract contract = contractMapper.fromDto(centralContractRequestDto);
        contract.setProduct(product);

        productService.validateProductCountOnPurchase(product.getInnerId(), centralContractRequestDto.getCount());
        contractRepository.save(contract);
        return contractMapper.toDto(contract);
    }

    @Override
    public ContractResponseDto getContractById(UUID id) throws ContractNotFoundException {
        Contract contract = this.getContractByInnerId(id);
        return contractMapper.toDto(contract);
    }

    @Override
    public UUID deleteContractById(UUID id) throws
            ContractNotFoundException,
            ContractIsPaidException,
            CentralResponseException {
        Contract contract = this.getContractByInnerId(id);

        // Throw error if contract is already paid
        if (contract.getIsPaid()) throw Exceptions.contractIsPaidException(id);

        // Delete contract in central service
        centralContractService.deleteEntity(entityPath, id);

        // Unfreeze product count if organization is from russia
        // Otherwise delete product itself from database
        Product product = contract.getProduct();
        Organization seller = product.getSeller();
        if (seller != null) {
            productRepository.unFreezeContractCount(product.getId(), contract.getContractAmount());
        }

        // Refund money to product buyer (if from russia)
        Organization buyer = contract.getBuyer();
        if (buyer != null) {
            organizationRepository.refundContractFees(buyer.getId(), contract.getContractAmount());
        }

        contractRepository.softDeleteById(contract.getInnerId());
        return id;
    }

    @Override
    public Contract getContractByInnerId(UUID id) throws ContractNotFoundException {
        return contractRepository.findById(id).orElseThrow(() -> Exceptions.contractNotFoundException(id));
    }
}
