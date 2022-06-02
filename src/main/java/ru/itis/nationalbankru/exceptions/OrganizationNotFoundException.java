package ru.itis.nationalbankru.exceptions;

public class OrganizationNotFoundException extends Exception {

    protected OrganizationNotFoundException(Long id) {
        super(String.format("Organization with id %s not found", id));
    }
}
