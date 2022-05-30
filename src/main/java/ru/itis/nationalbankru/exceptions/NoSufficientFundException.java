package ru.itis.nationalbankru.exceptions;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 13:52
 **/

public class NoSufficientFundException extends Exception {

    protected NoSufficientFundException(String organizationName, Double price) {
        super(String.format(
                "Organization { %s } has less than { %s } RUB to buy product",
                organizationName,
                price));
    }

}
