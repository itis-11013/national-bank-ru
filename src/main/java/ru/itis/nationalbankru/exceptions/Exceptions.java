package ru.itis.nationalbankru.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

public class Exceptions {

    public static UsernameNotFoundException usernameNotFoundException(UUID id) {
        return new UsernameNotFoundException(String.format("User with id %s was not found", id));
    }

    public static OrganizationNotFoundException organizationNotFoundException(UUID id) {
        return new OrganizationNotFoundException(id);
    }

    public static ContractNotFoundException contractNotFoundException(UUID id) {
        return new ContractNotFoundException(id);
    }
    
    public static NoSufficientFundException noSufficientFundException(
            String organization,
            String product,
            Double price
    ) {
        return new NoSufficientFundException(organization, product, price);
    }

    public static ExceedStockLimitException exceedStockLimitException(Integer requiredCount, Integer stockCount) {
        return new ExceedStockLimitException(requiredCount, stockCount);
    }

    public static ContractIsPaidException contractIsPaidException(UUID uuid) {
        return new ContractIsPaidException(uuid);
    }
}
