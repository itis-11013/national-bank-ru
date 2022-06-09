package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.product.ProductCatalogResponse;
import ru.itis.nationalbankru.dto.product.ProductRequestDto;
import ru.itis.nationalbankru.dto.product.ProductResponseDto;
import ru.itis.nationalbankru.dto.product.UnitResponse;
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

    @GetMapping
    public String createProductPage(ModelMap map) {
        List<UnitResponse> unitResponses = productService.getUnits();
        List<ProductCatalogResponse> productCatalogResponses = productService.getProductCatalog();
        map.addAttribute("units", unitResponses);
        map.addAttribute("product_catalogs", productCatalogResponses);
        return "create_product_page";
    }

    @PostMapping
    public ResponseEntity<GeneralResponse<ProductResponseDto>> createProduct(
            @Valid @RequestBody ProductRequestDto productRequestDto) {

        try {
            ProductResponseDto productResponseDto = productService.createProduct(productRequestDto);
            return new GeneralResponse<ProductResponseDto>().successfulCreateResponse(
                    productResponseDto,
                    GeneralResponse.ResponseClass.product);
        } catch (Exception exception) {
            return new GeneralResponse<ProductResponseDto>().failureCreateResponse(
                    exception,
                    GeneralResponse.ResponseClass.product);
        }
    }

    @GetMapping("/")
    public String getOrganizationAllProducts(ModelMap map, PageableDto pageableDto) {
        try {
            List<ProductResponseDto> productRequestDtos = productService.getOrganizationProducts(pageableDto);
            map.addAttribute("products", productRequestDtos);
        } catch (Exception ignored) {
        }
        return "organization_products_page";
    }
}
