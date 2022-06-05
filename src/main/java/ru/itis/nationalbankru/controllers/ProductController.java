package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.product.ProductRequestDto;
import ru.itis.nationalbankru.dto.product.ProductResponseDto;
import ru.itis.nationalbankru.services.product.ProductService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : Escalopa
 * @created : 05.06.2022, Sun
 * @time : 21:11
 **/

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<GeneralResponse<ProductResponseDto>> createProduct(
            @Valid @RequestBody ProductRequestDto productRequestDto) {

        try {
            ProductResponseDto productResponseDto = productService.createProduct(productRequestDto);
            return new GeneralResponse<ProductResponseDto>().successfulDeleteResponse(
                    productResponseDto,
                    GeneralResponse.ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<ProductResponseDto>().failureDeleteResponse(
                    exception,
                    GeneralResponse.ResponseClass.organization);
        }
    }

    @GetMapping("/")
    public ResponseEntity<GeneralResponse<List<ProductResponseDto>>> getOrganizationAllProducts(PageableDto pageableDto) {
        try {
            List<ProductResponseDto> productRequestDtos = productService.getOrganizationProducts(pageableDto);
            return new GeneralResponse<List<ProductResponseDto>>().successfulDeleteResponse(
                    productRequestDtos,
                    GeneralResponse.ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<List<ProductResponseDto>>().failureDeleteResponse(
                    exception,
                    GeneralResponse.ResponseClass.organization);
        }
    }
}
