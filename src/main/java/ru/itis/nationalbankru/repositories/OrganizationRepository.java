package ru.itis.nationalbankru.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.entity.User;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

    Page<Organization> findOrganizationsByUser(User user, Pageable pageable);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Organization o SET " +
            " o.balance = o.balance - :contractFee ," +
            " o.frozenBalance = o.frozenBalance + :contractFee " +
            " WHERE o.id = :id ")
    void freezeContractFees(@Param("id") UUID id, @Param("contractFee") Double contractFee);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Organization o SET " +
            " o.balance = o.balance + :contractFee ," +
            " o.frozenBalance = o.frozenBalance - :contractFee " +
            " WHERE o.id = :id ")
    void refundContractFees(@Param("id") UUID id, @Param("contractFee") Double contractFee);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Organization o SET o.frozenBalance = o.frozenBalance - :contractFee " +
            " WHERE o.id = :id ")
    void payContractFees(@Param("id") UUID id, @Param("contractFee") Double contractFee);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Organization o SET " +
            " o.balance = o.balance + :contractFee  WHERE o.id = :id ")
    void depositContractFees(@Param("id") UUID id, @Param("contractFee") Double contractFee);

}
