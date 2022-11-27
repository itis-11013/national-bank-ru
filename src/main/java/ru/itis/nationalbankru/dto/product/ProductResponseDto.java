package ru.itis.nationalbankru.dto.product;

import lombok.Data;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 10:03
 **/

@Data
public class ProductResponseDto {
    private String code;

    private String name;

    private double count;

    private String unit;

    private double price;

}
