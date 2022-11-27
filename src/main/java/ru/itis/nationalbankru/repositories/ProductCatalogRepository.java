package ru.itis.nationalbankru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.nationalbankru.entity.ProductCatalog;

import java.util.Optional;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 20:25
 **/
public interface ProductCatalogRepository extends JpaRepository<ProductCatalog, Long> {
    Optional<ProductCatalog> findProductCatalogByCode(String code);

    ProductCatalog getProductCatalogByCode(String code);
}
