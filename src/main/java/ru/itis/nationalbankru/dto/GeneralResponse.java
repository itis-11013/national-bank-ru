package ru.itis.nationalbankru.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
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
    @Nullable
    private String error;

    public ResponseEntity<GeneralResponse<T>> successfulCreateResponse(T responseBody, ResponseClass responseClass) {
        return this.setSuccessResponse(responseBody, ResponseDescription.created, responseClass);

    }

    public ResponseEntity<GeneralResponse<T>> successfulFetchResponse(T responseBody, ResponseClass responseClass) {
        return this.setSuccessResponse(responseBody, ResponseDescription.fetch, responseClass);

    }

    public ResponseEntity<GeneralResponse<T>> successfulUpdateResponse(T responseBody, ResponseClass responseClass) {
        return this.setSuccessResponse(responseBody, ResponseDescription.updated, responseClass);

    }

    public ResponseEntity<GeneralResponse<T>> successfulDeleteResponse(T responseBody, ResponseClass responseClass) {
        return this.setSuccessResponse(responseBody, ResponseDescription.deleted, responseClass);

    }

    public ResponseEntity<GeneralResponse<T>> successfulBanResponse(T responseBody, ResponseClass responseClass) {
        return this.setSuccessResponse(responseBody, ResponseDescription.banned, responseClass);

    }

    public ResponseEntity<GeneralResponse<T>> failureCreateResponse(Exception exception, ResponseClass responseClass) {
        return this.setFailureResponse(exception, ResponseDescription.create, responseClass);
    }

    public ResponseEntity<GeneralResponse<T>> failureFetchResponse(Exception exception, ResponseClass responseClass) {
        return this.setFailureResponse(exception, ResponseDescription.fetch, responseClass);
    }

    public ResponseEntity<GeneralResponse<T>> failureUpdateResponse(Exception exception, ResponseClass responseClass) {
        return this.setFailureResponse(exception, ResponseDescription.update, responseClass);
    }

    public ResponseEntity<GeneralResponse<T>> failureDeleteResponse(Exception exception, ResponseClass responseClass) {
        return this.setFailureResponse(exception, ResponseDescription.delete, responseClass);
    }

    public ResponseEntity<GeneralResponse<T>> failureBanResponse(Exception exception, ResponseClass responseClass) {
        return this.setFailureResponse(exception, ResponseDescription.ban, responseClass);
    }

    private ResponseEntity<GeneralResponse<T>> setSuccessResponse(T data,
                                                                  ResponseDescription responseDescription,
                                                                  ResponseClass responseClass) {
        return ResponseEntity.ok(new GeneralResponse<T>()
                .toBuilder()
                .description("Successfully " + responseDescription + " " + responseClass)
                .status(RequestStatus.success)
                .data(data)
                .build());
    }

    private ResponseEntity<GeneralResponse<T>> setFailureResponse(
            Exception exception,
            ResponseDescription responseDescription,
            ResponseClass responseClass) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse<T>()
                .toBuilder()
                .description("Failed to " + responseDescription + " " + responseClass)
                .error(exception.getMessage())
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
        fetched,
        ban,
        banned,
        payed,
        pay
    }

    public enum ResponseClass {
        organization,
        contract,
        user,
        product
    }

    public enum RequestStatus {
        success,
        failure
    }
}
