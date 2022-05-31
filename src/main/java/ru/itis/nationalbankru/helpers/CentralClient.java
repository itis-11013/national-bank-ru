package ru.itis.nationalbankru.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author : Escalopa
 * @created : 31.05.2022, Tue
 * @time : 10:49
 **/

@Component
@RequiredArgsConstructor
public class CentralClient {


    @Value("${centralBankEndPoint}")
    private final String centralBankEndPoint;

    private final WebClient webClient = WebClient.create(centralBankEndPoint);

    public WebClient getClient() {
        return this.webClient;
    }
}
