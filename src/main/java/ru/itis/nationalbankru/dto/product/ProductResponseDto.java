package ru.itis.nationalbankru.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 10:03
 **/

@Data
public class ProductResponseDto {
    private String code;

    private String name;

    @JsonProperty(value = "productid")
    private UUID innerId;

    private double count;

    private Long unit;

    private double price;

    @JsonProperty(value = "sellerid")
    private UUID sellerId;
}
