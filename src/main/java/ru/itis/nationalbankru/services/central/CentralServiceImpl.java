package ru.itis.nationalbankru.services.central;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
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

    CentralClient centralClient;

    @Override
    public UUID createEntity(String requestPath, T data) {
        Mono<CentralResponse<UUID>> response = centralClient.getClient().post()
                .uri(requestPath)
                .body(BodyInserters.fromValue(data))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
        return Objects.requireNonNull(response.block()).getInnerId();
    }

    @Override
    public void updateEntity(String requestPath, UUID uuid, T data) {
        centralClient.getClient().patch()
                .uri(uriBuilder -> uriBuilder.path(requestPath + "/").queryParam("id", uuid).build())
                .body(BodyInserters.fromValue(data))
                .retrieve();
    }

    @Override
    public CentralResponse<E> getEntity(String requestPath, UUID uuid) {
        return centralClient.getClient().get()
                .uri(uriBuilder -> uriBuilder.path(requestPath + "/").queryParam("id", uuid).build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CentralResponse<E>>() {
                }).block();
    }

    @Override
    public void deleteEntity(String requestPath, UUID uuid) {
        centralClient.getClient().delete()
                .uri(uriBuilder -> uriBuilder.path(requestPath + "/").queryParam("id", uuid).build())
                .retrieve();
    }
}
