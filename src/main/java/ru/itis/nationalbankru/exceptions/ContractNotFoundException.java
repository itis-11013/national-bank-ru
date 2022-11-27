package ru.itis.nationalbankru.exceptions;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 12:58
 **/
public class ContractNotFoundException extends Exception {

    protected ContractNotFoundException(UUID uuid) {
        super(String.format("Contract with id %s not found", uuid));
    }

}
