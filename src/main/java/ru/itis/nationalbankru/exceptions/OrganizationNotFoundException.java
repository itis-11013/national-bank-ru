package ru.itis.nationalbankru.exceptions;

import java.util.UUID;

public class OrganizationNotFoundException extends Exception {

    protected OrganizationNotFoundException(Long id) {
        super(String.format("Organization with id %s not found", id));
    }

    protected OrganizationNotFoundException(UUID id) {
        super(String.format("Organization with id %s not found", id));
    }
}
