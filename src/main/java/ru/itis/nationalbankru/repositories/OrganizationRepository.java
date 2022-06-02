package ru.itis.nationalbankru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.nationalbankru.entity.Organization;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByName(String name);

    Optional<Organization> findOrganizationsByInnerId(UUID uuid);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Organization o SET " +
            " o.balance = o.balance - :contractFee ," +
            " o.frozenBalance = o.frozenBalance + :contractFee " +
            " WHERE o.id = :id ")
    void freezeContractFees(@Param("id") Long id, @Param("contractFee") Double contractFee);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Organization o SET " +
            " o.balance = o.balance + :contractFee ," +
            " o.frozenBalance = o.frozenBalance - :contractFee " +
            " WHERE o.id = :id ")
    void refundContractFees(@Param("id") Long id, @Param("contractFee") Double contractFee);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Organization o SET o.frozenBalance = o.frozenBalance - :contractFee " +
            " WHERE o.id = :id ")
    void payContractFees(@Param("id") Long id, @Param("contractFee") Double contractFee);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Organization o SET " +
            " o.balance = o.balance + :contractFee  WHERE o.id = :id ")
    void depositContractFees(@Param("id") Long id, @Param("contractFee") Double contractFee);

}
