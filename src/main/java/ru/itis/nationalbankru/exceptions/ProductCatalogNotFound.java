package ru.itis.nationalbankru.exceptions;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 23:08
 **/
public class ProductCatalogNotFound extends Exception {

    protected ProductCatalogNotFound(String code) {
        super(String.format("Product Catalog with code %s not found", code));
    }
}
