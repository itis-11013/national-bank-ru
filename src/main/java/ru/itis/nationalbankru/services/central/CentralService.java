package ru.itis.nationalbankru.services.central;

import ru.itis.nationalbankru.dto.central.CentralResponse;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 31.05.2022, Tue
 * @time : 11:12
 **/
public interface CentralService<T, E> {

    UUID createEntity(String requestPath, T data);

    void updateEntity(String requestPath, UUID uuid, T data);

    CentralResponse<E> getEntity(String requestPath, UUID uuid);

    void deleteEntity(String requestPath, UUID uuid);
}
