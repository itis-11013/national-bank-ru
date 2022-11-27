package ru.itis.nationalbankru.services.central;

import ru.itis.nationalbankru.exceptions.CentralResponseException;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 31.05.2022, Tue
 * @time : 11:12
 **/
public interface CentralService<T, E> {

    UUID createEntity(String requestPath, T data) throws CentralResponseException;

    E updateEntity(String requestPath, UUID uuid, T data) throws CentralResponseException;

    E getEntity(String requestPath, UUID uuid) throws CentralResponseException;

    void deleteEntity(String requestPath, UUID uuid) throws CentralResponseException;
}
