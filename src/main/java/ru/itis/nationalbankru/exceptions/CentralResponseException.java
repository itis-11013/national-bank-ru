package ru.itis.nationalbankru.exceptions;

/**
 * @author : Escalopa
 * @created : 05.06.2022, Sun
 * @time : 10:03
 **/
public class CentralResponseException extends Exception {

    protected CentralResponseException() {
        super("Failed to query data to the central bank");
    }
}
