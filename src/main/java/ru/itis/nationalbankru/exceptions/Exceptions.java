package ru.itis.nationalbankru.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

public class Exceptions {

    public static UsernameNotFoundException usernameNotFoundException(UUID id) {
        return new UsernameNotFoundException(String.format("User with id %s was not found", id));
    }

    public static OrganizationNotFoundException organizationNotFoundException(Long id) {
        return new OrganizationNotFoundException(id);
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

    public static ContractIsPaidException contractIsPaidException(UUID uuid) {
        return new ContractIsPaidException(uuid);
    }
}
