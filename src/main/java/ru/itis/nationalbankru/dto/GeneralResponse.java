package ru.itis.nationalbankru.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;
import ru.itis.nationalbankru.entity.enums.RequestStatus;

import javax.persistence.MappedSuperclass;
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder(toBuilder = true)
public class GeneralResponse<T> {
    @Builder.Default
    private RequestStatus status = RequestStatus.SUCCESS;
    @Builder.Default
    private String description = "OK";
    @Nullable
    private T data;
}
