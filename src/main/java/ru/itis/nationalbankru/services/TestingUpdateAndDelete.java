package ru.itis.nationalbankru.services;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 05.06.2022, Sun
 * @time : 07:49
 **/
public class TestingUpdateAndDelete {
    static WebClient webClient = WebClient.create("http://188.93.211.195/central/");

    public static void main(String[] args) {
        UUID uuid = UUID.fromString("43fedbda-12fc-41d0-93f4-b47c33fb04fe");
        String response = webClient.delete().uri(uriBuilder -> uriBuilder.path("organization/{id}").build(uuid)).retrieve().bodyToMono(String.class).block();
        System.out.println(response);
    }
}
