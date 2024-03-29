package ru.itis.nationalbankru.services.product;

import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.product.ProductCatalogResponse;
import ru.itis.nationalbankru.dto.product.ProductRequestDto;
import ru.itis.nationalbankru.dto.product.ProductResponseDto;
import ru.itis.nationalbankru.dto.product.UnitResponse;
import ru.itis.nationalbankru.entity.Product;
import ru.itis.nationalbankru.entity.ProductCatalog;
import ru.itis.nationalbankru.entity.Unit;
import ru.itis.nationalbankru.exceptions.CentralResponseException;
import ru.itis.nationalbankru.exceptions.ProductCatalogNotFound;
import ru.itis.nationalbankru.exceptions.ProductExceedStockLimitException;
import ru.itis.nationalbankru.exceptions.UnitNotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 07:51
 **/
public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto) throws Exception;

    List<ProductResponseDto> getOrganizationProducts(PageableDto pageable);

    List<ProductResponseDto> getAllProducts(PageableDto pageable);

    Product getProductOrFetchByInnerId(UUID uuid) throws CentralResponseException;

    Product getProductByInnerId(UUID uuid);

    void validateProductCountOnPurchase(UUID innerId, Double count) throws ProductExceedStockLimitException;

    ProductCatalog getProductCatalogByCode(String code) throws ProductCatalogNotFound;

    List<UnitResponse> getUnits();

    List<ProductCatalogResponse> getProductCatalog();

    Unit getUnitByCode(Long id) throws UnitNotFoundException;
}
