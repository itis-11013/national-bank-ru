package ru.itis.nationalbankru.exceptions;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 14:05
 **/
public class ExceedStockLimitException extends Exception {

    protected ExceedStockLimitException(Integer requiredCount, Integer stockCount) {
        super(String.format("Call to buy %s pieces out of %s", requiredCount, stockCount));
    }
}
