package ru.itis.nationalbankru.services.central;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import ru.itis.nationalbankru.dto.central.CentralResponse;
import ru.itis.nationalbankru.exceptions.CentralResponseException;
import ru.itis.nationalbankru.exceptions.Exceptions;
import ru.itis.nationalbankru.helpers.CentralClient;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 31.05.2022, Tue
 * @time : 11:13
 **/

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CentralServiceImpl<T, E> implements CentralService<T, E> {

    private final CentralClient centralClient;

    @Override
    public UUID createEntity(String requestPath, T data) throws CentralResponseException {
        CentralResponse<UUID> response = centralClient.getWebClient().post().uri(requestPath).body(BodyInserters.fromValue(data)).retrieve().bodyToMono(new ParameterizedTypeReference<CentralResponse<UUID>>() {
        }).block();
        if (response == null || !response.isSuccess()) {
            throw Exceptions.centralResponseException();
        }
        return response.getInnerId();
    }

    @Override
    public E updateEntity(String requestPath, UUID uuid, T data) throws CentralResponseException {
        CentralResponse<E> response = centralClient.getWebClient().patch().uri(uriBuilder -> uriBuilder.path(requestPath + "/{id}").build(uuid)).body(BodyInserters.fromValue(data)).retrieve().bodyToMono(new ParameterizedTypeReference<CentralResponse<E>>() {
        }).block();
        this.validateResponse(response);
        return this.getResponseBody(response);
    }

    @Override
    public E getEntity(String requestPath, UUID uuid) throws CentralResponseException {
        CentralResponse<E> response = centralClient.getWebClient().get().uri(uriBuilder -> uriBuilder.path(requestPath + "/{id}").build(uuid)).retrieve().bodyToMono(new ParameterizedTypeReference<CentralResponse<E>>() {
        }).block();
        this.validateResponse(response);
        return this.getResponseBody(response);
    }

    @Override
    public void deleteEntity(String requestPath, UUID uuid) throws CentralResponseException {
        CentralResponse<UUID> response = centralClient.getWebClient().delete().uri(uriBuilder -> uriBuilder.path(requestPath + "/{id}").build(uuid)).retrieve().bodyToMono(new ParameterizedTypeReference<CentralResponse<UUID>>() {
        }).block();
        this.validateResponse(response);
    }

    public void validateResponse(CentralResponse<?> response) throws CentralResponseException {
        if (response == null || !response.isSuccess()) {
            throw Exceptions.centralResponseException();
        }
    }

    public E getResponseBody(CentralResponse<E> response) {
        return (response == null || !response.isSuccess()) ? null : response.getData();
    }
}
