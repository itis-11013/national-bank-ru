package ru.itis.nationalbankru.exceptions;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 21:04
 **/
public class UnitNotFoundException extends Exception {

    protected UnitNotFoundException(Long code) {
        super(String.format("Unit with code %s not found", code));
    }
}
