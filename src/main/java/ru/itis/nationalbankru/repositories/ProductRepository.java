package ru.itis.nationalbankru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.nationalbankru.entity.Product;

import java.util.Optional;
import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 19:48
 **/

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByInnerId(UUID uuid);

    Optional<Product> findProductByNameAndSellerId(String name, Long id);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Product p SET " +
            " p.count = p.count - :contractCount ," +
            " p.frozenCount = p.frozenCount + :contractCount " +
            " WHERE p.id = :id ")
    void freezeContractCount(@Param("id") Long id, @Param("contractCount") Double contractCount);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Product p SET " +
            " p.count = p.count - :contractCount ," +
            " p.frozenCount = p.frozenCount + :contractCount " +
            " WHERE p.id = :id ")
    void unFreezeContractCount(@Param("id") Long id, @Param("contractCount") Double contractCount);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Product p SET " +
            " p.frozenCount = p.frozenCount - :contractCount " +
            " WHERE p.id = :id ")
    void payContractCount(@Param("id") Long id, @Param("contractCount") Double contractCount);


}
