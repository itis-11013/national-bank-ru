package ru.itis.nationalbankru.dto.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

@Data
@Builder
public class SignInDto {
    String email;
    String password;

    public static SignInDto getSignInDto(HttpServletRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = getBodyFromRequest(request);
        return mapper.readValue(jsonBody, SignInDto.class);
    }

    private static String getBodyFromRequest(HttpServletRequest request) throws IOException {
        return request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    }
}
