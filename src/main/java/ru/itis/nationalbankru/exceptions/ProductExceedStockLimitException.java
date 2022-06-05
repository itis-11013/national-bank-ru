package ru.itis.nationalbankru.exceptions;

/**
 * @author : Escalopa
 * @created : 05.06.2022, Sun
 * @time : 14:40
 **/
public class ProductExceedStockLimitException extends Exception {

    protected ProductExceedStockLimitException(String productName, Double count) {
        super(String.format("Product %s has less than %s count in stock", productName, count));
    }
}
