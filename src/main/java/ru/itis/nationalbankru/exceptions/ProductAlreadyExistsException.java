package ru.itis.nationalbankru.exceptions;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 23:23
 **/
public class ProductAlreadyExistsException extends Exception {

    protected ProductAlreadyExistsException(String productName, String organizationName) {
        super(String.format("Product with name %s already exists for organization %s", productName, organizationName));
    }

}
