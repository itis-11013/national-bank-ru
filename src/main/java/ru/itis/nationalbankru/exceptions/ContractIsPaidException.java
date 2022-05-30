package ru.itis.nationalbankru.exceptions;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 14:22
 **/
public class ContractIsPaidException extends Exception {

    public ContractIsPaidException(UUID uuid) {
        super(String.format(
                "Contract cannot be deleted, Contract with id %s has already been paid, the process is irreversible",
                uuid));
    }
}
