package ru.itis.nationalbankru.services.central;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import ru.itis.nationalbankru.dto.central.CentralResponse;
import ru.itis.nationalbankru.helpers.CentralClient;

import javax.transaction.Transactional;
import java.util.Objects;
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
    public UUID createEntity(String requestPath, T data) {
        CentralResponse<UUID> response = centralClient.getWebClient().post()
                .uri(requestPath)
                .body(BodyInserters.fromValue(data))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CentralResponse<UUID>>() {
                }).block();
        assert response != null;
        return response.getInnerId();
    }

    @Override
    public void updateEntity(String requestPath, UUID uuid, T data) {
        centralClient.getWebClient().patch()
                .uri(uriBuilder -> uriBuilder.path(requestPath + "/").queryParam("id", uuid).build())
                .body(BodyInserters.fromValue(data))
                .retrieve();
    }

    @Override
    public E getEntity(String requestPath, UUID uuid) {
        return Objects.requireNonNull(centralClient.getWebClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path(requestPath + "/{id}")
                        .build(uuid))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CentralResponse<E>>() {
                }).block()).getData();
    }

    @Override
    public void deleteEntity(String requestPath, UUID uuid) {
        centralClient.getWebClient().delete()
                .uri(uriBuilder -> uriBuilder
                        .path(requestPath + "/{id}")
                        .build(uuid))
                .retrieve();
    }
}
