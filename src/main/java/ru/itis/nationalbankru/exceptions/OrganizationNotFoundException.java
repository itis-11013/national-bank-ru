package ru.itis.nationalbankru.exceptions;

import java.util.UUID;

public class OrganizationNotFoundException extends Exception{

    protected OrganizationNotFoundException(UUID uuid){
        super(String.format("Organization with id %s not found",uuid));
    }
}
