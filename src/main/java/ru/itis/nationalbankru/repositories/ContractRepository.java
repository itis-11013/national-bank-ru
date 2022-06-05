package ru.itis.nationalbankru.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.nationalbankru.entity.Contract;
import ru.itis.nationalbankru.entity.Organization;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 09:00
 **/
@Repository
public interface ContractRepository extends JpaRepository<Contract, UUID> {
    @Modifying
    @Query("UPDATE Contract c SET c.deleted = true WHERE c.innerId = :uuid")
    void softDeleteById(@Param("uuid") UUID uuid);

    Page<Contract> findContractByProductSeller(Organization organization, Pageable pageable);

    Page<Contract> findContractByBuyer(Organization organization, Pageable pageable);
}
