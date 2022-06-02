package ru.itis.nationalbankru.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 10:11
 **/
@Data
public class ProductRequestDto {

    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    @JsonProperty(value = "sellerid")
    private UUID sellerId;

    @DecimalMin(value = "1.0")
    private double count;

    @Min(value = 1)
    private Long unit;

    @DecimalMin(value = "1.0")
    private double price;
}
