package ru.itis.nationalbankru.exceptions;

import java.util.UUID;

public class Exceptions {

    public static CentralResponseException centralResponseException() {
        return new CentralResponseException();
    }

    public static ProductExceedStockLimitException productExceedStockLimitException(String productName, Double count) {
        return new ProductExceedStockLimitException(productName, count);
    }

    public static OrganizationNotFoundException organizationNotFoundException(Long id) {
        return new OrganizationNotFoundException(id);
    }

    public static OrganizationNotFoundException organizationNotFoundException(UUID uuid) {
        return new OrganizationNotFoundException(uuid);
    }

    public static ContractNotFoundException contractNotFoundException(UUID id) {
        return new ContractNotFoundException(id);
    }

    public static NoSufficientFundException noSufficientFundException(
            String organization,
            Double price
    ) {
        return new NoSufficientFundException(organization, price);
    }

    public static UnitNotFoundException unitNotFoundException(Long id) {
        return new UnitNotFoundException(id);
    }

    public static ProductCatalogNotFound productCatalogNotFound(String code) {
        return new ProductCatalogNotFound(code);
    }

    public static ProductAlreadyExistsException productAlreadyExistsException(String productName, String organizationName) {
        return new ProductAlreadyExistsException(productName, organizationName);
    }

    public static ContractIsPaidException contractIsPaidException(UUID uuid) {
        return new ContractIsPaidException(uuid);
    }
}
