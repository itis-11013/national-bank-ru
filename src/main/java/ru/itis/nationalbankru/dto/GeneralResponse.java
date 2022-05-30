package ru.itis.nationalbankru.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder(toBuilder = true)
public class GeneralResponse<T> {
    @Builder.Default
    private RequestStatus status = RequestStatus.success;
    @Builder.Default
    private String description = "OK";
    @Nullable
    private T data;

    public ResponseEntity<GeneralResponse<T>> setSuccessResponse(T data, ResponseDescription responseDescription,
                                                                 ResponseClass responseClass) {
        return ResponseEntity.ok(new GeneralResponse<T>()
                .toBuilder()
                .description("Successfully " + responseDescription + " " + responseClass)
                .status(RequestStatus.success)
                .data(data)
                .build());
    }

    public ResponseEntity<GeneralResponse<T>> setFailureResponse(
            RedirectAttributes redirectAttributes,
            Exception exception,
            ResponseDescription responseDescription,
            ResponseClass responseClass) {
        redirectAttributes.addAttribute("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse<T>()
                .toBuilder()
                .description("Failed to " + responseDescription + " " + responseClass)
                .status(RequestStatus.failure)
                .build());
    }

    public enum ResponseDescription {
        create,
        created,
        update,
        updated,
        delete,
        deleted,
        fetch,
        fetched
    }

    public enum ResponseClass {
        organization,
        contract,
        product,
        user,
    }

    public enum RequestStatus {
        success,
        failure
    }
}
