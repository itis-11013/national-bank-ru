package ru.itis.nationalbankru.helpers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author : Escalopa
 * @created : 31.05.2022, Tue
 * @time : 10:49
 **/

@Component
@Slf4j
public class CentralClient {


    private WebClient webClient;
    @Value("${centralBankEndPoint}")
    private String centralBankEndPoint;

    @Bean
    public WebClient getWebClient() {
        if (this.webClient == null) {
            this.webClient = WebClient.create(centralBankEndPoint);
            log.info(String.format("Using central url : %s ", centralBankEndPoint));
        }
        return webClient;
    }
}
