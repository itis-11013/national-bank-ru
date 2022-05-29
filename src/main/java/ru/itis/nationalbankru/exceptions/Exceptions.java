package ru.itis.nationalbankru.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

public class Exceptions {

    public static UsernameNotFoundException usernameNotFoundException(UUID id){
        return new UsernameNotFoundException(String.format("User with id %s was not found",id));
    }
    public static OrganizationNotFoundException organizationNotFoundException(UUID id){
        return new OrganizationNotFoundException(id);
    }
}
