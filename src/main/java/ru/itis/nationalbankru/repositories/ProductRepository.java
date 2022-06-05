package ru.itis.nationalbankru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
