package ru.itis.nationalbankru.services.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.product.ProductRequestDto;
import ru.itis.nationalbankru.dto.product.ProductResponseDto;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.entity.Product;
import ru.itis.nationalbankru.entity.ProductCatalog;
import ru.itis.nationalbankru.entity.Unit;
import ru.itis.nationalbankru.exceptions.*;
import ru.itis.nationalbankru.helpers.OrganizationHelper;
import ru.itis.nationalbankru.mappers.ProductMapper;
import ru.itis.nationalbankru.repositories.ProductCatalogRepository;
import ru.itis.nationalbankru.repositories.ProductRepository;
import ru.itis.nationalbankru.repositories.UnitRepository;
import ru.itis.nationalbankru.services.central.CentralService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 07:52
 **/
@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final String entityPath = "product";
    private final OrganizationHelper organizationHelper;
    private final ProductRepository productRepository;
    private final ProductCatalogRepository productCatalogRepository;
    private final UnitRepository unitRepository;
    private final CentralService<ProductRequestDto, ProductResponseDto> centralService;
    ProductMapper productMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) throws UnitNotFoundException, ProductCatalogNotFound, ProductAlreadyExistsException, CentralResponseException {

        // Get organization
        Organization organization = organizationHelper.getCurrentOrganization();

        // Check if product already exists for this organization
        boolean productAlreadyExists = productRepository.findProductByNameAndSellerId(productRequestDto.getName(), organization.getId()).isPresent();
        if (productAlreadyExists) {
            throw Exceptions.productAlreadyExistsException(productRequestDto.getName(), organization.getName());
        }

        // Get productCatalog & unit
        ProductCatalog productCatalog = this.getProductCatalogByCode(productRequestDto.getCode());
        Unit unit = this.getUnitByCode(productRequestDto.getUnit());

        // Add product to central bank
        productRequestDto.setSellerId(organization.getInnerId());
        UUID innerId = centralService.createEntity(entityPath, productRequestDto);

        Product product = Product.builder().catalog(productCatalog).unit(unit).seller(organization).innerId(innerId).build();
        productMapper.updateFromDto(productRequestDto, product);

        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductResponseDto> getMyProducts(PageableDto pageable) {
        return null;
    }

    @Override
    public List<ProductResponseDto> getAllProducts(PageableDto pageable) {
        return null;
    }

    @Override
    public Product getProductOrFetchByInnerId(UUID uuid) throws CentralResponseException {
        Product product = this.getProductByInnerId(uuid);
        if (product != null) return product;
        ProductResponseDto productResponseDto = centralService.getEntity(entityPath, uuid);
        product = productMapper.fromDto(productResponseDto);
        product.setUnit(unitRepository.getById(productResponseDto.getUnit()));
        product.setCatalog(productCatalogRepository.getProductCatalogByCode(productResponseDto.getCode()));
        return product;
    }

    @Override
    public Product getProductByInnerId(UUID uuid) {
        return productRepository.findProductByInnerId(uuid);
    }

    @Override
    public void validateProductCountOnPurchase(UUID innerId, Double count) throws ProductExceedStockLimitException {
        Product product = this.getProductByInnerId(innerId);
        if (product.getCount() < count) throw Exceptions.productExceedStockLimitException(product.getName(), count);
    }

    public ProductCatalog getProductCatalogByCode(String code) throws ProductCatalogNotFound {
        return productCatalogRepository.findProductCatalogByCode(code).orElseThrow(() -> Exceptions.productCatalogNotFound(code));
    }

    public Unit getUnitByCode(Long id) throws UnitNotFoundException {
        return unitRepository.findUnitByCode(id).orElseThrow(() -> Exceptions.unitNotFoundException(id));
    }
}
